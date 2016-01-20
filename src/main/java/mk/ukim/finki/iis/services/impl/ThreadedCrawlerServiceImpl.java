package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.crawler.CrawlerInterface;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.model.UserListensTrack;
import mk.ukim.finki.iis.services.CrawlerService;
import mk.ukim.finki.iis.services.TrackService;
import mk.ukim.finki.iis.services.UserListensTrackService;
import mk.ukim.finki.iis.services.UserService;
import mk.ukim.finki.iis.services.helper.userCrawling.FriendListCrawlerThread;
import mk.ukim.finki.iis.services.helper.userCrawling.TrackCrawler;
import mk.ukim.finki.iis.services.helper.userCrawling.UserCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * Created by User on 12/7/2015.
 */
@Service
public class ThreadedCrawlerServiceImpl implements CrawlerService, UserCrawler, TrackCrawler {
    /**
     * This constant indicates how many user friend lists will be crawled simultaneously.
     */
    private static final int NUMBER_OF_THREADS = 10;
    Semaphore waitForThreads = new Semaphore(0);
    Semaphore waitForDataAccess = new Semaphore(0);
    @Autowired
    UserListensTrackService userListensTrackService;
    /**
     * This number is read from database and indicates the number of currently crawled users.
     */
    private Long numberOfCrawledUsers;
    /**
     * If no users are crawled this user will be used for starting point.
     */
    private User startUser = new User("RJ");
    private int crawledUsers = 0;
    private int crawledSongs = 0;
    private Set<User> roundUsers = new HashSet<User>();
    private Set<Track> roundTracks = new HashSet<Track>();
    private List<UserListensTrack> roundUserListensTrack = new LinkedList<>();
    private int numberOfActiveThreads = 0;
    @Autowired
    private CrawlerInterface crawler;
    @Autowired
    private UserService userService;
    @Autowired
    private TrackService trackService;

    public ThreadedCrawlerServiceImpl() {
    }

    public void crawlLastFm(int numberOfSongs, int numberOfUsers) {
        numberOfCrawledUsers = userService.getNumberOfUsers();
        List<User> usersForCrawling;
        while (true) {
            usersForCrawling = getUsersForCrawl(3 * NUMBER_OF_THREADS - numberOfActiveThreads);
            numberOfActiveThreads += usersForCrawling.size();
            int numberOfThreadsToWait = Math.min(numberOfActiveThreads, NUMBER_OF_THREADS);
            if (getNumberOfCrawledUsers() > numberOfUsers || usersForCrawling.size() == 0)
                break;
            waitForDataAccess.release(numberOfThreadsToWait);
            runThreadsForUsers(usersForCrawling);
            try {
                waitForThreads.acquire(numberOfThreadsToWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Set<User> crawledUsers = roundUsers;
            Set<Track> crawledTracks = roundTracks;
            List<UserListensTrack> crawledUserListensTracks = roundUserListensTrack;
            resetState();
            numberOfActiveThreads -= numberOfThreadsToWait;
            numberOfCrawledUsers += userService.saveUsers(crawledUsers).size();
            //tuka insertiaj pesni insertTracks
            trackService.saveTracks(crawledTracks);
            userListensTrackService.save(crawledUserListensTracks);
            userService.setUsersCrawled(usersForCrawling);
        }
    }

    public void runThreadsForUsers(List<User> users) {
        for (User user : users) {
            Thread thread = new FriendListCrawlerThread(user, this, this);
            thread.start();
        }
    }

    public void crawlTracks(List<User> users, int numberOfSongs) {
        // TODO crawl tracks
    }

    public void crawlUsers(int numberOfUsers) {

    }

    private void resetState() {
        roundUsers = new HashSet<User>();
        roundTracks = new HashSet<Track>();
        roundUserListensTrack = new LinkedList<>();
        crawledUsers = 0;
    }

    public void crawlFriendsForUser(User user) {
        List<User> friends = crawler.getUserFriends(user);
        addRoundUsers(friends);
    }

    public void crawlTracksForUser(User user) {
        List<Track> tracks = crawler.getTopTracksForUser(user);
        try {
            waitForDataAccess.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addRoundTracks(tracks);
        addRoundUserTracks(user, tracks);
        waitForThreads.release();
    }

    private List<User> getUsersForCrawl(int numberOfUsers) {
        if (getNumberOfCrawledUsers() > 0)
            return userService.getUsersForCrawl(numberOfUsers);
        else {
            User user = saveUser(startUser);
            List<User> usersForCrawling = new ArrayList<>();
            usersForCrawling.add(user);
            return usersForCrawling;
        }

    }


    private User saveUser(User user) {
        User fullUser = crawler.getUserInfo(user.getName());
        return userService.saveUser(fullUser);
    }

    private Long getNumberOfCrawledUsers() {
        return numberOfCrawledUsers;
    }

    public int getCrawledUsers() {
        return crawledUsers;
    }

    synchronized
    public void addCrawledUser() {
        crawledUsers++;
    }

    synchronized
    public void addRoundUsers(List<User> users) {
        for (User user : users)
            roundUsers.add(user);
    }

    synchronized
    public void addRoundTracks(List<Track> tracks) {
        for (Track track : tracks)
            roundTracks.add(track);
    }

    synchronized
    public void addRoundUserTracks(User user, List<Track> tracks) {
        for(Track track : tracks)
            roundUserListensTrack.add(new UserListensTrack(track, user, track.getPlaycount()));
    }
}
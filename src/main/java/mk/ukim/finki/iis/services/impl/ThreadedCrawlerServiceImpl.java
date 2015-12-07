package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.crawler.CrawlerInterface;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.services.CrawlerService;
import mk.ukim.finki.iis.services.UserService;
import mk.ukim.finki.iis.services.helper.userCrawling.FriendListCrawlerThread;
import mk.ukim.finki.iis.services.helper.userCrawling.UserCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12/7/2015.
 */
@Service
public class ThreadedCrawlerServiceImpl implements CrawlerService, UserCrawler {
    /**
     * This constant indicates how many user friend lists will be crawled simultaneously.
     */
    private static final int NUMBER_OF_THREADS = 100;
    /**
     * This number is read from database and indicates the number of currently crawled users.
     */
    private Long numberOfCrawledUsers;
    /**
     * If no users are crawled this user will be used for starting point.
     */
    private User startUser = new User("RJ");

    @Autowired
    private CrawlerInterface crawler;

    @Autowired
    private UserService userService;

    public ThreadedCrawlerServiceImpl() {
    }

    public void crawlLastFm(int numberOfSongs, int numberOfUsers) {

    }

    public void crawlTracks(List<User> users, int numberOfSongs) {

    }

    public void crawlUsers(int numberOfUsers) {
        numberOfCrawledUsers = userService.getNumberOfUsers();
        List<User> usersForCrawling;
        List<Thread> threads;
        while (true) {
            usersForCrawling = getUsersForCrawl();
            if (getNumberOfCrawledUsers() > numberOfUsers || usersForCrawling.size() == 0)
                break;
            threads = new ArrayList<Thread>();
            for (User user : getUsersForCrawl()) {
                // TODO pusti tredovi tuka
                Thread thread = new FriendListCrawlerThread(user, this);
                threads.add(thread);
                thread.start();
            }
            userService.setUsersCrawled(usersForCrawling);
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void crawlFriendsForUser(User user) {
        List<User> friends = crawler.getUserFriends(user);
        userService.insertUsers(friends);
    }

    private List<User> getUsersForCrawl() {
        List<User> usersForCrawling = new ArrayList<User>();
        if (getNumberOfCrawledUsers() > 0)
            usersForCrawling = userService.getUsersForCrawl(NUMBER_OF_THREADS);
        else {
            User user = saveUser(startUser);
            usersForCrawling.add(user);
        }
        return usersForCrawling;
    }

    private User saveUser(User user) {
        User fullUser = crawler.getUserInfo(user.getName());
        return userService.insertUser(fullUser);
    }

    private Long getNumberOfCrawledUsers() {
        return numberOfCrawledUsers;
    }
}
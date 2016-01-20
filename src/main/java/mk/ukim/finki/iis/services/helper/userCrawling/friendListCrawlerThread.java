package mk.ukim.finki.iis.services.helper.userCrawling;

import mk.ukim.finki.iis.model.User;

/**
 * Created by User on 12/7/2015.
 */
public class FriendListCrawlerThread extends Thread {
    private User user;
    private UserCrawler userCrawler;
    private TrackCrawler trackCrawler;


    public FriendListCrawlerThread(User user, UserCrawler userCrawler, TrackCrawler trackCrawler ) {
        this.user = user;
        this.userCrawler = userCrawler;
        this.trackCrawler = trackCrawler;
    }

    @Override
    public void run() {
        super.run();
        userCrawler.crawlFriendsForUser(user);
        trackCrawler.crawlTracksForUser(user);
    }
}
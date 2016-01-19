package mk.ukim.finki.iis.services.helper.userCrawling;

import mk.ukim.finki.iis.model.User;

/**
 * Created by User on 12/7/2015.
 */
public class FriendListCrawlerThread extends Thread {
    private User user;
    private UserCrawler crawler;


    public FriendListCrawlerThread(User user, UserCrawler crawler) {
        this.user = user;
        this.crawler = crawler;
    }

    @Override
    public void run() {
        super.run();
        crawler.crawlFriendsForUser(user);
    }
}
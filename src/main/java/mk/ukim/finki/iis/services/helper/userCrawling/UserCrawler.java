package mk.ukim.finki.iis.services.helper.userCrawling;

import mk.ukim.finki.iis.model.User;

/**
 * Created by User on 12/7/2015.
 */
public interface UserCrawler {
    void crawlFriendsForUser(User user);
}


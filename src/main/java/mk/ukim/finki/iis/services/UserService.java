package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 12/1/2015.
 */
public interface UserService {
    User getUserById(Long id);

    User insertUser(User user);

    public void userListened(User user, Track track, Long playCount);

    User getUserByUsername(String username);

    List<User> getUsersForCrawl(int numberOfThreads);

    void setUsersCrawled(List<User> usersForCrawling);

    Long insertUsers(Collection<User> friends);

    Long getNumberOfUsers();
}

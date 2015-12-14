package mk.ukim.finki.iis.persistance;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.model.UserListensTrack;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 12/1/2015.
 */
public interface UserRepository {
    User getUserById(Long id);

    User insertUser(User user);

    UserListensTrack userListens(User user, Track track, Long playCount);

    User getUserByUsername(String username);

    List<User> getUsersForCrawl(int numberOfUsers);

    Long insertUsers(Collection<User> users);

    void setUsersCrawled(List<User> crawledUsers);

    Long getNumberOfUsers();
}

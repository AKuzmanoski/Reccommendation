package mk.ukim.finki.iis.persistance;

import mk.ukim.finki.iis.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 12/1/2015.
 */
public interface UserRepository {
    User getUserById(Long id);

    User saveUser(User user);

    User getUserByUsername(String username);

    List<User> getUsersForCrawl(int numberOfUsers);

    List<User> saveUsers(Collection<User> users);

    void setUsersCrawled(Collection<User> crawledUsers);

    Long getNumberOfUsers();
}

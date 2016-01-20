package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 12/1/2015.
 */
public interface UserService {
    User getUserById(Long id);

    User saveUser(User user);

    User getUserByUsername(String username);

    List<User> getUsersForCrawl(int numberOfThreads);

    void setUsersCrawled(Collection<User> usersForCrawling);

    List<User> saveUsers(Collection<User> friends);

    Long getNumberOfUsers();


}

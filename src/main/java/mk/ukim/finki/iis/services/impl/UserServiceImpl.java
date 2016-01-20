package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.persistance.UserRepository;
import mk.ukim.finki.iis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 12/1/2015.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userRepositoryJpa")
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public List<User> getUsersForCrawl(int numberOfUsers) {
        return userRepository.getUsersForCrawl(numberOfUsers);
    }

    public void setUsersCrawled(Collection<User> crawledUsers) {
        userRepository.setUsersCrawled(crawledUsers);
    }

    public List<User> saveUsers(Collection<User> users) {
        return userRepository.saveUsers(users);
    }

    public Long getNumberOfUsers() {
        return userRepository.getNumberOfUsers();
    }
}

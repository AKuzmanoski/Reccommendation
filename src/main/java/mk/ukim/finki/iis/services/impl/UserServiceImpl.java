package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.persistance.UserRepository;
import mk.ukim.finki.iis.services.CountryService;
import mk.ukim.finki.iis.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 12/1/2015.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public User insertUser(User user) {
        return userRepository.insertUser(user);
    }

    public void userListened(User user, Track track, Long playCount) {
        userRepository.userListens(user, track, playCount);
    }

	public User getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}

    public List<User> getUsersForCrawl(int numberOfUsers) {
        return userRepository.getUsersForCrawl(numberOfUsers);
    }

    public void setUsersCrawled(List<User> crawledUsers) {
        userRepository.setUsersCrawled(crawledUsers);
    }

    public Long insertUsers(List<User> users) {
        return userRepository.insertUsers(users);
    }

    public Long getNumberOfUsers() {
        return userRepository.getNumberOfUsers();
    }
}

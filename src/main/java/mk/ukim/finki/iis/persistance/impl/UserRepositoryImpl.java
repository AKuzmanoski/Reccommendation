package mk.ukim.finki.iis.persistance.impl;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.model.UserListensTrack;
import mk.ukim.finki.iis.persistance.BaseRepository;
import mk.ukim.finki.iis.persistance.CountryRepository;
import mk.ukim.finki.iis.persistance.UserRepository;
import mk.ukim.finki.iis.persistance.helper.NonCrawledUsersPredicateBuilder;
import mk.ukim.finki.iis.persistance.helper.UserByUsernamePredicateBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 12/1/2015.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    BaseRepository baseRepository;
    @Autowired
    CountryRepository countryRepository;

    public User getUserById(Long id) {
        return baseRepository.getById(User.class, id);
    }

    @Transactional
    public User insertUser(User user) {
        User persistentUser = exists(user);
        if (persistentUser != null)
            return persistentUser;
        Country country = countryRepository.insertCountry(user.getCountry());
        user.setCountry(country);
        return baseRepository.saveOrUpdate(user);
    }

    @Transactional
    public UserListensTrack userListens(User user, Track track, Long playCount) {
        UserListensTrack userListensTrack = new UserListensTrack(track, user, playCount);
        countryRepository.userListens(user.getCountry(), track, playCount);
        return baseRepository.saveOrUpdate(userListensTrack);
    }

    private User exists(User user) {
        return getUserByUsername(user.getName());
    }

	public User getUserByUsername(String username) {
		List<User> users = baseRepository.find(User.class, new UserByUsernamePredicateBuilder<User>(username));
        if (users.size() > 0)
            return users.get(0);
        return null;
	}

    public List<User> getUsersForCrawl(int numberOfUsers) {
        return baseRepository.find(User.class, new NonCrawledUsersPredicateBuilder(), numberOfUsers);
    }

    @Transactional
    public Long insertUsers(List<User> users) {
        // TODO optimize this function (check how long it takes)
        for (User user : users)
            this.insertUser(user);
        return (long)users.size();
    }

    @Transactional
    public void setUsersCrawled(List<User> crawledUsers) {
        // TODO optimize this function to
        for (User user : crawledUsers) {
            user.setFriendListCrawled(true);
            baseRepository.saveOrUpdate(user);
        }
    }

    public Long getNumberOfUsers() {
        return baseRepository.count(User.class, null);
    }
}

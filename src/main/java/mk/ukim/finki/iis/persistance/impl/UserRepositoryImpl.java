package mk.ukim.finki.iis.persistance.impl;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.model.UserListensTrack;
import mk.ukim.finki.iis.persistance.BaseRepository;
import mk.ukim.finki.iis.persistance.UserRepository;
import mk.ukim.finki.iis.persistance.helper.NonCrawledUsersPredicateBuilder;
import mk.ukim.finki.iis.persistance.helper.UserByUsernamePredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 12/1/2015.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    BaseRepository baseRepository;
    @Autowired
    private DataSource dataSource;
    @PersistenceContext
    private EntityManager entityManager;

    public User getUserById(Long id) {
        return baseRepository.getById(User.class, id);
    }

    @Transactional
    public User insertUser(User user) {
        User persistentUser = exists(user);
        if (persistentUser != null)
            return persistentUser;
        return baseRepository.saveOrUpdate(user);
    }

    @Transactional
    public UserListensTrack userListens(User user, Track track, Long playCount) {
        UserListensTrack userListensTrack = new UserListensTrack(track, user, playCount);
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

    public Long insertUsers(Collection<User> users) {
        /*for (User user : users) {
            insertUser(user);
        }*/

        String name = "name";
        String url = "url";
        String gender = "gender";
        String country = "country";
        String lastfm = "lastfm";
        String friends = "friends";
        Map<String, Object> parameters = new HashMap<String, Object>();
        StringBuilder queryString = new StringBuilder("INSERT IGNORE `users` (`name`, `url`, `gender`, `country`, `lastfm_id`, `friendListCrawled`) VALUES ");

        for (User user : users) {
            queryString.append("('");
            queryString.append(user.getName());
            queryString.append("', '");
            queryString.append(user.getUrl());
            queryString.append("', '");
            queryString.append(user.getGender());
            queryString.append("', '");
            queryString.append(user.getCountry());
            queryString.append("', ");
            queryString.append(user.getLastFMId());
            queryString.append(", ");
            queryString.append(0);
            queryString.append("), ");
        }

        if (users.size() > 0) {
            int length = queryString.length();
            queryString.replace(length - 2, length, "");
        }

        int results = 0;

        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();

            results = statement.executeUpdate(queryString.toString());

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (long)results;
    }

    @Transactional
    public void setUsersCrawled(List<User> crawledUsers) {
        for (User user : crawledUsers) {
            user.setFriendListCrawled(true);
            baseRepository.saveOrUpdate(user);
        }
    }

    public Long getNumberOfUsers() {
        return baseRepository.count(User.class, null);
    }
}

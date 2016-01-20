package mk.ukim.finki.iis.persistance.jpa;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.model.UserListensTrack;
import mk.ukim.finki.iis.persistance.UserRepository;
import mk.ukim.finki.iis.persistance.jpa.interfaces.UserListensTrackRepositoryJpaHelper;
import mk.ukim.finki.iis.persistance.jpa.interfaces.UserRepositoryJpaHelper;
import mk.ukim.finki.iis.persistance.jpa.specifications.UserSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by User on 12/1/2015.
 */
@Repository
public class UserRepositoryJpa implements UserRepository {
    @Autowired
    UserRepositoryJpaHelper repository;
    @Autowired
    DataSource dataSource;

    public User getUserById(Long id) {
        return repository.findOne(id);
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public User getUserByUsername(String username) {
        List<User> users = repository.findAll(UserSpecifications.userWithUsername(username));
        if (users.size() > 0)
            return users.get(0);
        return null;
    }

    public List<User> getUsersForCrawl(int numberOfUsers) {
        List<User> users = new LinkedList<>();
        Page<User> page = repository.findAll(UserSpecifications.userWithFriendListCrawled(), new PageRequest(0, numberOfUsers));
        for (User user : page)
            users.add(user);
        return users;
    }

    /*@Transactional
    public List<User> saveUsers(Collection<User> users) {
        return repository.save(users);
    }*/

    public List<User> saveUsers(Collection<User> users) {
        List<User> result = new LinkedList<>();
        List<User> list = new LinkedList<>();
        int i = 0;
        for (User user : users) {
            result.add(user);
            list.add(user);
            i++;
            if (i == 412) {
                insert(list);
                i = 0;
                list = new LinkedList<>();
            }
        }
        insert(list);

        return result;
    }

    public void insert(Collection<User> users) {
        List<User> result = new LinkedList<>();

        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            StringBuilder queryString = new StringBuilder("INSERT IGNORE `users` (`name`, `country`, `gender`, `url`, `friendListCrawled`) VALUES ");

            for (User user : users) {
                result.add(user);
                queryString.append("(?, ?, ?, ?, ?), ");
            }


            if (users.size() > 0) {
                int length = queryString.length();
                queryString.replace(length - 2, length, "");
            }

            PreparedStatement statement = connection.prepareStatement(queryString.toString());

            int i = 0;
            for (User user : users) {
                int index = i * 5;
                statement.setString(index + 1, user.getName());
                statement.setString(index + 2, user.getCountry());
                statement.setString(index + 3, user.getGender());
                //statement.setLong(index + 4, user.getLastFMId());
                statement.setString(index + 4, user.getUrl());
                statement.setBoolean(index + 5, false);
                i++;
            }
            //System.out.println("##################################################" + statement.toString());
            statement.executeUpdate();

            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUsersCrawled(Collection<User> crawledUsers) {
        for (User user : crawledUsers) {
            user.setFriendListCrawled(true);
        }
        repository.save(crawledUsers);
    }

    public Long getNumberOfUsers() {
        return (long) repository.findAll().size();
    }
}

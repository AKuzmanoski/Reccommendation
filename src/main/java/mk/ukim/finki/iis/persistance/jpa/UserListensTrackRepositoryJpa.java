package mk.ukim.finki.iis.persistance.jpa;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.model.UserListensTrack;
import mk.ukim.finki.iis.persistance.UserListensTrackRepository;
import mk.ukim.finki.iis.persistance.jpa.interfaces.UserListensTrackRepositoryJpaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 1/20/2016.
 */
@Repository
public class UserListensTrackRepositoryJpa implements UserListensTrackRepository {
    @Autowired
    UserListensTrackRepositoryJpaHelper repository;
    @Autowired
    DataSource dataSource;

    @Override
    public UserListensTrack save(UserListensTrack userListensTrack) {
        return repository.save(userListensTrack);
    }

    @Override
    public List<UserListensTrack> findAll() {
        return repository.findAll();
    }

    /*@Override
    @Transactional
    public List<UserListensTrack> save(Collection<UserListensTrack> userListensTracks) {
        return repository.save(userListensTracks);
    }*/

    @Override
    public List<UserListensTrack> save(Collection<UserListensTrack> userListensTracks) {
        List<UserListensTrack> list = new LinkedList<>();
        int i = 0;
        for (UserListensTrack userListensTrack : userListensTracks) {
            list.add(userListensTrack);
            i++;
            if (i == 686) {
                insert(list);
                i = 0;
                list = new LinkedList<>();
            }
        }
        insert(list);

        return new LinkedList<>();
    }

    private void insert(Collection<UserListensTrack> userListensTracks) {
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            StringBuilder queryString = new StringBuilder("INSERT IGNORE `users_songs` (`play_count`, `track_url`, `user_name`) VALUES ");

            for (UserListensTrack userListensTrack : userListensTracks)
                queryString.append("(?, ?, ?), ");


            if (userListensTracks.size() > 0) {
                int length = queryString.length();
                queryString.replace(length - 2, length, "");
            }

            PreparedStatement statement = connection.prepareStatement(queryString.toString());

            int i = 0;
            for (UserListensTrack userListensTrack : userListensTracks) {
                int index = i * 3;
                statement.setLong(index + 1, userListensTrack.getPlayCount());
                statement.setString(index + 2, userListensTrack.getTrack().getUrl());
                statement.setString(index + 3, userListensTrack.getUser().getName());
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
}

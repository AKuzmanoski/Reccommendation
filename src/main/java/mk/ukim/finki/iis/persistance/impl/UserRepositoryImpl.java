package mk.ukim.finki.iis.persistance.impl;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.model.UserListensTrack;
import mk.ukim.finki.iis.persistance.BaseRepository;
import mk.ukim.finki.iis.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 12/1/2015.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    BaseRepository baseRepository;

    public User getUserById(Long id) {
        return baseRepository.getById(User.class, id);
    }

    public User insertUser(User user) {
        return baseRepository.saveOrUpdate(user);
    }

    public UserListensTrack userListens(User user, Track track, Long playCount) {
        UserListensTrack userListensTrack = new UserListensTrack(track, user, playCount);
        return baseRepository.saveOrUpdate(userListensTrack);
    }
}

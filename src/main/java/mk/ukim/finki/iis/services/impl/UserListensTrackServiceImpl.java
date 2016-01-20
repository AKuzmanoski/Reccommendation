package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.model.UserListensTrack;
import mk.ukim.finki.iis.persistance.UserListensTrackRepository;
import mk.ukim.finki.iis.services.UserListensTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 1/20/2016.
 */
@Service
public class UserListensTrackServiceImpl implements UserListensTrackService {
    @Autowired
    UserListensTrackRepository userListensTrackRepository;

    @Override
    public UserListensTrack save(UserListensTrack userListensTrack) {
        return userListensTrackRepository.save(userListensTrack);
    }

    @Override
    public List<UserListensTrack> findAll() {
        return userListensTrackRepository.findAll();
    }

    @Override
    public List<UserListensTrack> save(Collection<UserListensTrack> userListensTracks) {
        return userListensTrackRepository.save(userListensTracks);
    }
}

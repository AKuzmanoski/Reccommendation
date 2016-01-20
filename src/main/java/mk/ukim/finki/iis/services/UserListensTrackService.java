package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.UserListensTrack;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 1/20/2016.
 */
public interface UserListensTrackService {
    UserListensTrack save(UserListensTrack userListensTrack);

    List<UserListensTrack> findAll();

    List<UserListensTrack> save(Collection<UserListensTrack> userListensTracks);
}

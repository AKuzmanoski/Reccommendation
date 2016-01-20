package mk.ukim.finki.iis.persistance;

import mk.ukim.finki.iis.model.UserListensTrack;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 1/20/2016.
 */
public interface UserListensTrackRepository {
    UserListensTrack save(UserListensTrack userListensTrack);

    List<UserListensTrack> findAll();

    List<UserListensTrack> save(Collection<UserListensTrack> userListensTracks);
}

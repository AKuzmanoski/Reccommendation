package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;

/**
 * Created by User on 12/1/2015.
 */
public interface UserService {
    User getUserById(Long id);

    User insertUser(User user);

    public void userListened(User user, Track track, Long playCount);
}

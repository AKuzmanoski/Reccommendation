package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.model.UserListensTrack;

import java.util.List;

public interface MainService {
    Track getTrackById(Long id);

    Track getTrackByUrl(String url);

    User getUserById(Long id);

    User getUserByUsername(String username);

    Track saveTrack(Track track);

    User saveUser(User user);

    UserListensTrack userListensTrack(User user, Track track, Long playCount);

    UserListensTrack userListensTrack(User user, Track track);

    UserListensTrack userListensTrack(UserListensTrack userListensTrack);

    void crawlLastFm(int numberOfSongs, int numberOfUsers);

    User login(String username, String password);

    List<String> recommendCountries(Track track);
}

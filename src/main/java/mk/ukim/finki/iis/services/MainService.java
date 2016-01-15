package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;

import java.util.List;

public interface MainService {
    Track getTrackById(Long id);

    Track getTrackByUrl(String url);

    User getUserById(Long id);

    User getUserByUsername(String username);

    Track insertTrack(Track track);

    User insertUser(User user);

    void userListened(User user, Track track, Long playCount);

    void userListened(User user, Track track);

    void crawlLastFm(int numberOfSongs, int numberOfUsers);

    User login(String username, String password);

    List<String> recommendCountries(Track track);
}

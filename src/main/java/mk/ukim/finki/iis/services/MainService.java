package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;

/**
 * Created by User on 12/1/2015.
 */
public interface MainService {
    Track getTrackById(Long id);

    User getUserById(Long id);

    Country getCountryById(Long id);

    Track insertTrack(Track track);

    User insertUser(User user);

    void userListened(User user, Track track, Long playCount);

    void userListened(User user, Track track);

    Country insertCountry(Country country);

    void crawlLastFm(int numberOfSongs, int numberOfUsers);
}

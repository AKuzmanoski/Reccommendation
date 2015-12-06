package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;

import java.util.List;

public interface MainService {
    Track getTrackById(Long id);

    Track getTrackByLastFmId(Long id);

    User getUserById(Long id);

    User getUserByLastFmId(Long id);

    Country getCountryById(Long id);

    Country getCountryByName(String name);

    Track insertTrack(Track track);

    User insertUser(User user);

    void userListened(User user, Track track, Long playCount);

    void userListened(User user, Track track);

    Country insertCountry(Country country);

    void crawlLastFm(int numberOfSongs, int numberOfUsers);

    List<Country> recommendCountries(Track track);

    User login(String username, String password);
}

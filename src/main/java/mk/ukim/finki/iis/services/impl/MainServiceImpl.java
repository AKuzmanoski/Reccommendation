package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.services.CountryService;
import mk.ukim.finki.iis.services.MainService;
import mk.ukim.finki.iis.services.TrackService;
import mk.ukim.finki.iis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 12/1/2015.
 */
@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private TrackService trackService;
    @Autowired
    private UserService userService;
    @Autowired
    private CountryService countryService;

    public Track getTrackById(Long id) {
        return trackService.getTrackById(id);
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    public Country getCountryById(Long id) {
        return countryService.getCountryById(id);
    }

    public Track insertTrack(Track track) {
        return trackService.insertTrack(track);
    }

    public User insertUser(User user) {
        return userService.insertUser(user);
    }

    public Country insertCountry(Country country) {
        return countryService.insertCountry(country);
    }

    public void crawlLastFm(int numberOfSongs, int numberOfUsers) {
        // TODO implementiraj go ovoj metod za krolanje.
        // Mislam deka bi bilo dobro implementacijata da ja izvadis vo drug servis za da ne se usloznuva ovaa klasa.
        // Ovaa klasa ke glumi fasada koja ke gi definira site metodi od celata aplikacija.

    }
}
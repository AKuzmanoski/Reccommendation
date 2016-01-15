package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.services.CrawlerService;
import mk.ukim.finki.iis.services.MainService;
import mk.ukim.finki.iis.services.TrackService;
import mk.ukim.finki.iis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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
    @Qualifier(value = "threadedCrawlerServiceImpl")
    private CrawlerService crawlerService;

    public Track getTrackById(Long id) {
        return trackService.getTrackById(id);
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    public Track insertTrack(Track track) {
        return trackService.insertTrack(track);
    }

    public User insertUser(User user) {
        return userService.insertUser(user);
    }

    public void crawlLastFm(int numberOfSongs, int numberOfUsers) {
        crawlerService.crawlLastFm(numberOfSongs, numberOfUsers);
    }

    public void userListened(User user, Track track) {
        userListened(user, track, 1L);
    }

    public void userListened(User user, Track track, Long playCount) {
        userService.userListened(user, track, playCount);
    }

    public User login(String username, String password) {
        // TODO implementiraj logika za najavuvanje
        return null;
    }

    public Track getTrackByUrl(String url) {
        return trackService.getTrackByUrl(url);
    }


    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    public List<String> recommendCountries(Track track) {
        // TODO implement this method to recommend countries where this song can be popular.
        List<String> countries = new LinkedList<String>();
        countries.add("Macedonia");
        countries.add("France");
        countries.add("Austria");
        return countries;
    }
}
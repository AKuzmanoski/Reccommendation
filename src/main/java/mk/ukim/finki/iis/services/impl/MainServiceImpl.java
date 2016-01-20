package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.model.UserListensTrack;
import mk.ukim.finki.iis.services.*;
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
    @Autowired
    private UserListensTrackService userListensTrackService;

    public Track getTrackById(Long id) {
        return trackService.getTrackById(id);
    }

    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    public Track saveTrack(Track track) {
        return trackService.saveTrack(track);
    }

    public User saveUser(User user) {
        return userService.saveUser(user);
    }

    public UserListensTrack userListensTrack(UserListensTrack userListensTrack) {
        return userListensTrackService.save(userListensTrack);
    }

    public void crawlLastFm(int numberOfSongs, int numberOfUsers) {
        crawlerService.crawlLastFm(numberOfSongs, numberOfUsers);
    }

    public UserListensTrack userListensTrack(User user, Track track) {
        return userListensTrack(new UserListensTrack(track, user, 1L));
    }

    public UserListensTrack userListensTrack(User user, Track track, Long playCount) {
        return userListensTrack(new UserListensTrack(track, user, playCount));
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
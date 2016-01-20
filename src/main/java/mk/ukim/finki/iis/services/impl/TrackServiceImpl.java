package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.persistance.TrackRepository;
import mk.ukim.finki.iis.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 12/1/2015.
 */
@Service
public class TrackServiceImpl implements TrackService {
    @Autowired
    @Qualifier("trackRepositoryJpa")
    TrackRepository trackRepository;

    public Track getTrackById(Long id) {
        return trackRepository.getTrackById(id);
    }

    public Track saveTrack(Track track) {
        return trackRepository.saveTrack(track);
    }

    public Track getTrackByUrl(String url) {
        Track track = new Track();
        return trackRepository.getTrackByUrl(url);
    }

    public List<Track> saveTracks(Set<Track> roundTracks) {
        return trackRepository.saveTracks(roundTracks);
    }
}

package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.persistance.TrackRepository;
import mk.ukim.finki.iis.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 12/1/2015.
 */
@Service
public class TrackServiceImpl implements TrackService {
    @Autowired
    TrackRepository trackRepository;

    public Track getTrackById(Long id) {
        return trackRepository.getTrackById(id);
    }

    public Track insertTrack(Track track) {
        return trackRepository.insertTrack(track);
    }

    public Track getTrackByLastFmId(Long id) {
        return trackRepository.getTrackByLastFmId(id);
    }
}

package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Track;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 12/1/2015.
 */
public interface TrackService {
    Track getTrackById(Long id);

    Track saveTrack(Track track);

    Track getTrackByUrl(String url);

    List<Track> saveTracks(Set<Track> roundTracks);
}

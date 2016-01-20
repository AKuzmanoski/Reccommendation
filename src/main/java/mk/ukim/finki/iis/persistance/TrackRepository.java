package mk.ukim.finki.iis.persistance;

import mk.ukim.finki.iis.model.Track;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 12/1/2015.
 */
public interface TrackRepository {
    Track getTrackById(Long id);

    Track saveTrack(Track track);

    Track getTrackByUrl(String url);

    List<Track> saveTracks(Collection<Track> tracks);
}

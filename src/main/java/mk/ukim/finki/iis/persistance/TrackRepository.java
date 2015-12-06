package mk.ukim.finki.iis.persistance;

import mk.ukim.finki.iis.model.Track;

/**
 * Created by User on 12/1/2015.
 */
public interface TrackRepository {
    Track getTrackById(Long id);

    Track insertTrack(Track track);

    Track getTrackByLastFmId(Long id);
}

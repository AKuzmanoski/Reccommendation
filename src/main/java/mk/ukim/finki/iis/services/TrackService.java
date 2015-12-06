package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Track;

/**
 * Created by User on 12/1/2015.
 */
public interface TrackService {
    Track getTrackById(Long id);

    Track insertTrack(Track track);

    Track getTrackByLastFmId(Long id);
}

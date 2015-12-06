package mk.ukim.finki.iis.persistance.impl;

import com.fasterxml.jackson.databind.deser.Deserializers;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.persistance.BaseRepository;
import mk.ukim.finki.iis.persistance.TrackRepository;
import mk.ukim.finki.iis.persistance.helper.TrackByLastFmIdPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 12/1/2015.
 */
@Repository
public class TrackRepositoryImpl implements TrackRepository {
    @Autowired
    BaseRepository baseRepository;

    public TrackRepositoryImpl() {

    }

    public TrackRepositoryImpl(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public Track getTrackById(Long id) {
        return baseRepository.getById(Track.class, id);
    }

    @Transactional
    public Track insertTrack(Track track) {
        Track persistentTrack = exists(track);
        if (persistentTrack != null)
            return persistentTrack;
        return baseRepository.saveOrUpdate(track);
    }

    private Track exists(Track track) {
        return getTrackByLastFmId(track.getMbid());
    }

    public Track getTrackByLastFmId(Long id) {
        List<Track> tracks = baseRepository.find(Track.class, new TrackByLastFmIdPredicateBuilder<Track>(id));
        if (tracks.size() > 0)
            return tracks.get(0);
        return null;
    }
}

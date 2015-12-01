package mk.ukim.finki.iis.persistance.impl;

import com.fasterxml.jackson.databind.deser.Deserializers;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.persistance.BaseRepository;
import mk.ukim.finki.iis.persistance.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    public BaseRepository getBaseRepository() {
        return baseRepository;
    }

    public void setBaseRepository(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public Track getTrackById(Long id) {
        return baseRepository.getById(Track.class, id);
    }

    public Track insertTrack(Track track) {
        return baseRepository.saveOrUpdate(track);
    }
}

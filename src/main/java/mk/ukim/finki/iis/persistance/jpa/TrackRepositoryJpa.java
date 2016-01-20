package mk.ukim.finki.iis.persistance.jpa;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.persistance.TrackRepository;
import mk.ukim.finki.iis.persistance.jpa.interfaces.TrackRepositoryJpaHelper;
import mk.ukim.finki.iis.persistance.jpa.specifications.TrackSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 12/1/2015.
 */
@Repository
public class TrackRepositoryJpa implements TrackRepository {
    @Autowired
    TrackRepositoryJpaHelper repository;
    @Autowired
    DataSource dataSource;

    public TrackRepositoryJpa() {

    }

    public Track getTrackById(Long id) {
        return repository.getOne(id);
    }

    public Track saveTrack(Track track) {
        return repository.save(track);
    }

    public Track getTrackByUrl(String url) {
        List<Track> tracks = repository.findAll(TrackSpecifications.trackWithUrl(url));
        if (tracks.size() > 0)
            return tracks.get(0);
        return null;
    }

    /*@Transactional
    public List<Track> saveTracks(Collection<Track> tracks) {
        return repository.save(tracks);
    }*/

    public List<Track> saveTracks(Collection<Track> tracks) {
        List<Track> list = new LinkedList<>();
        int i = 0;
        for (Track track : tracks) {
            list.add(track);
            i++;
            if (i == 412) {
                insert(list);
                i = 0;
                list = new LinkedList<>();
            }
        }
        insert(list);

        return new LinkedList<>();
    }

    public void insert(Collection<Track> tracks) {
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            StringBuilder queryString = new StringBuilder("INSERT IGNORE `songs` (`lastfm_id`, `name`, `playcount`, `url`, `artist`) VALUES ");

            for (Track track : tracks)
                queryString.append("(?, ?, ?, ?, ?), ");


            if (tracks.size() > 0) {
                int length = queryString.length();
                queryString.replace(length - 2, length, "");
            }

            PreparedStatement statement = connection.prepareStatement(queryString.toString());

            int i = 0;
            for (Track track : tracks) {
                int index = i * 5;
                statement.setString(index + 1, track.getMbid());
                statement.setString(index + 2, track.getName());
                statement.setLong(index + 3, track.getPlaycount());
                statement.setString(index + 4, track.getUrl());
                statement.setString(index + 5, track.getArtist());
                i++;
            }
//            System.out.println("##################################################" + statement.toString());
            statement.executeUpdate();

            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
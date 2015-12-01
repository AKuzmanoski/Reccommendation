package mk.ukim.finki.iis.model;

import javax.persistence.*;

/**
 * Created by User on 11/30/2015.
 */
@Entity
@Table(name = "countries_songs")
public class CountryHasTack extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Country country;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Track track;
    @Column(name = "play_count")
    private Long playCount;

    public CountryHasTack() {

    }

    public CountryHasTack(Country country, Long playCount, Track track) {
        this.country = country;
        this.playCount = playCount;
        this.track = track;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}

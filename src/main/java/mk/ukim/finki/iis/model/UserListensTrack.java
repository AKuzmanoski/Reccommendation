package mk.ukim.finki.iis.model;

import javax.persistence.*;

/**
 * Created by User on 11/30/2015.
 */
@Entity
@Table(name = "users_songs")
public class UserListensTrack extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Track track;
    @Column(name = "play_count")
    private Long playCount;

    public UserListensTrack() {

    }

    public UserListensTrack(Track track, User user, Long playCount) {
        this.track = track;
        this.user = user;
        this.playCount = playCount;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public void addPlayCount(Long playCount) {
        this.playCount += playCount;
    }
}

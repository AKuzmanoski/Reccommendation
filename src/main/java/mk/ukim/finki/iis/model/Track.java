package mk.ukim.finki.iis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "songs")
public class Track extends BaseEntity {
    @Column(name = "lastfm_id")
    private Long mbid;
    private String name;
    private String url;
    private String artist;

    public Track() {

    }

    public Track(Long mbid, String name, String url, String artist) {
        super();
        this.mbid = mbid;
        this.name = name;
        this.url = url;
        this.artist = artist;
    }

    public Long getMbid() {
        return mbid;
    }

    public void setMbid(Long mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Track [mbid=" + mbid + ", name=" + name + ", url=" + url
                + ", artist=" + artist + "]";
    }
}

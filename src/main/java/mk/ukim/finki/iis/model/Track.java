package mk.ukim.finki.iis.model;
import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Track {
    @Column(name = "lastfm_id")
    private String mbid;
    private String name;
    private Long playcount;
    @Id
    private String url;
	private String artist;

    public Track() {

    }
    //.......

    public Track(String mbid, String name, String url, String artist) {
        super();
        this.mbid = mbid;
        this.name = name;
        this.url = url;
        this.artist = artist;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
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
    
    public Long getPlaycount() {
		return playcount;
	}

	public void setPlaycount(Long playcount) {
		this.playcount = playcount;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        return !(getUrl() != null ? !getUrl().equals(track.getUrl()) : track.getUrl() != null);

    }

    @Override
    public int hashCode() {
        return getUrl() != null ? getUrl().hashCode() : 0;
    }
}

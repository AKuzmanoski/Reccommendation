package mk.ukim.finki.iis.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "songs")
public class Track extends BaseEntity {
	@Column(name = "lastfm_id")
	private String mbid;
	private String name;
	private String url;
	private String artist;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "track")
	private List<CountryHasTack> countries;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "track")
	private List<UserListensTrack> listeners;
	
	public Track(){
		
	}
	public Track(String mbid,String name, String url, String artist) {
		super();
		this.mbid=mbid;
		this.name = name;
		this.url = url;
		this.artist=artist;
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
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Track [mbid=" + mbid + ", name=" + name + ", url=" + url
				+ ", artist=" + artist + "]";
	}


}

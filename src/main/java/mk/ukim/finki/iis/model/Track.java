package mk.ukim.finki.iis.model;

public class Track {
	
	private String mbid;
	private String name;
	private String url;
	
	public Track(){
		
	}
	public Track(String mbid,String name, String url) {
		super();
		this.mbid=mbid;
		this.name = name;
		this.url = url;
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


}

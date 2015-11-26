package mk.ukim.finki.iis.model;

public class Track {
	
	private Long id;
	private String name;
	private String url;
	private String artist;
	
	public Track(){
		
	}
	public Track(Long id,String name, String url, String artist) {
		super();
		this.id=id;
		this.name = name;
		this.url = url;
		this.artist = artist;
	}


}

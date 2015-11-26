package mk.ukim.finki.iis.crawler;
import java.util.List;

import mk.ukim.finki.iis.model.*;

public interface CrawlerInterface {
	
	public List<Track> getStarterTracks(String filename);
	
	public List<User> getUsersForTrack(Track track);
	
	public List<Track> getTrackForUser(User user);
	
	public User getUserInfo(String username);
    
	
	

}

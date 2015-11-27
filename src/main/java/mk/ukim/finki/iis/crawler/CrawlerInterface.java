package mk.ukim.finki.iis.crawler;
import java.util.List;

import mk.ukim.finki.iis.model.*;

public interface CrawlerInterface {
	
	public List<User> getUserFriends(User user);
	
	public void crawlUsers(User user);
	
	public List<Track> getTopTracksForUser(User user);
	
	public List<Track> getLovedTracksForUser(User user);
	
	public User getUserInfo(String username);
	
	public List<Track> getSimilarTracks(Track track);
    
	

}

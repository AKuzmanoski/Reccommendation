package mk.ukim.finki.iis.services;

import java.util.List;

import mk.ukim.finki.iis.model.User;

public interface CrawlerService {
	
	public void crawlLastFm(int numberOfSongs, int numberOfUsers);
	
	public void crawlUsers(int numberOfUsers);
	
	public void crawlTracks(List<User> users, int numberOfSongs);

}

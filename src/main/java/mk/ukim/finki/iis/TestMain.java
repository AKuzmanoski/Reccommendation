package mk.ukim.finki.iis;

import java.util.List;

import com.itextpdf.text.log.SysoCounter;

import mk.ukim.finki.iis.crawler.CrawlerInterface;
import mk.ukim.finki.iis.crawler.impl.Crawler;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;

/**
 * Created by User on 11/26/2015.
 */
public class TestMain {
    public static void main(String[] args) {

    	CrawlerInterface crawler=new Crawler();
    	
    	User user=new User();
    	user.setName("RJ");
    	/*
    	
    	List<Track> tracks=crawler.getTrackForUser(user);
    	for (Track track : tracks) {
			System.out.println(track);
		}
		
    	user=crawler.getUserInfo("RJ");
    	System.out.println(user);
    	
    	Track track=new Track();
    	track.setArtist("Cher");
    	track.setName("Believe");
    	List<User> users=crawler.getUsersForTrack(track);
    	for (User user : users) {
			System.out.println(user.getName()+" "+user.getCountry());
		}
		
    	List<User> friends=crawler.getUserFriends(user);
    	for (User user2 : friends) {
			System.out.println(user2);
		}
		*/
    	crawler.crawlUsers(user);
    }
}

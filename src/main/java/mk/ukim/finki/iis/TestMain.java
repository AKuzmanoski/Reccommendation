package mk.ukim.finki.iis;

import java.util.List;

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
    	List<Track> tracks=crawler.getTrackForUser(user);
    	for (Track track : tracks) {
			System.out.println(track.getName());
		}
    }
}

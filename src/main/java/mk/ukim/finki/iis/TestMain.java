package mk.ukim.finki.iis;

import mk.ukim.finki.iis.crawler.CrawlerInterface;
import mk.ukim.finki.iis.crawler.impl.Crawler;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.services.MainService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by User on 11/26/2015.
 */
public class TestMain {

    public static void main(String[] args) {
        crawl(1000, 1000);
        example();
    }

    /**
     * This method crawls data from last fm.
     * @param numberOfSongs {@link Track}s that would be crawled.
     * @param numberOfUsers {@Link User}s that would be crawled.
     * @see MainService#crawlLastFm(int, int)
     */
    private static void crawl(int numberOfSongs, int numberOfUsers) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/persistance-config.xml");
        MainService mainService = (MainService) context.getBean("mainServiceImpl");
        mainService.crawlLastFm(numberOfSongs, numberOfUsers);
    }

    /**
     * Ova vcituvanje e najdobro da bide vo MainService. Vidi TO DO ti imam napisano
     */
    private static void example() {
        CrawlerInterface crawler = new Crawler();

        User user = new User();
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

    	crawler.crawlUsers(user);

    	List<Track> loved=crawler.getLovedTracksForUser(user);
    	for (Track track : loved) {
			System.out.println(track);
		}*/
        Track track = new Track();
        track.setArtist("Cher");
        track.setName("Believe");
        List<Track> similar = crawler.getSimilarTracks(track);
        for (Track track2 : similar) {
            System.out.println(track2);
        }
    }
}
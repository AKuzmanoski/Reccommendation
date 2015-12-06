package mk.ukim.finki.iis;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.services.MainService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/business-config.xml");
        MainService mainService = (MainService) context.getBean("mainServiceImpl");
        mainService.crawlLastFm(numberOfSongs, numberOfUsers);
    }

    /**
     * Ova vcituvanje e najdobro da bide vo MainService. Vidi TO DO ti imam napisano
     */
    private static void example() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/business-config.xml");
        MainService mainService = (MainService) context.getBean("mainServiceImpl");

        Country country = new Country("Macedonia");
        User user = new User("Aleksandar Kuzmanoski", "https://www.facebook.com/aleksandar.kuzmanoskii", country, 12345L);
        Track track = new Track(115523456654L, "Mello", "https://www.youtube.com/watch?v=YQHsXMglC9A", "Adele");

        user = mainService.insertUser(user);
        track = mainService.insertTrack(track);
        mainService.userListened(user, track, 100L);
    }
}
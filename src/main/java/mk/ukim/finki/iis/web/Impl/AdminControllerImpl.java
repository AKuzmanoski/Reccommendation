package mk.ukim.finki.iis.web.Impl;

import mk.ukim.finki.iis.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by User on 12/1/2015.
 */
@Controller
public class AdminControllerImpl {
    @Autowired
    private MainService service;

    @RequestMapping(value = "/admin")
    public String index() {
        return "adminHome";
    }

    @RequestMapping(value = "/admin/crawl", method = RequestMethod.POST)
    public String crawl(@RequestParam String numberOfSongs, @RequestParam String numberOfUsers) {
        Integer numOfSongs = Integer.parseInt(numberOfSongs);
        Integer numOfUsers = Integer.parseInt(numberOfUsers);
        service.crawlLastFm(numOfSongs, numOfUsers);
        return "adminHome";
    }
}

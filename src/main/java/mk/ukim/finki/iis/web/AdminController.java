package mk.ukim.finki.iis.web;

import mk.ukim.finki.iis.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private MainService service;

    @RequestMapping(value = "/")
    public String index() {
        return "adminHome";
    }

    @RequestMapping(value = "/crawl", method = RequestMethod.POST)
    public String crawl(@RequestParam String numberOfSongs, @RequestParam String numberOfUsers) {
        Integer numOfSongs = Integer.parseInt(numberOfSongs);
        Integer numOfUsers = Integer.parseInt(numberOfUsers);
        service.crawlLastFm(numOfSongs, numOfUsers);
        return "adminHome";
    }
}

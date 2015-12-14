package mk.ukim.finki.iis.web;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/recommendation")
public class RecommendationController {
    @Autowired
    private MainService service;

    @RequestMapping(value = "/countries/track/{trackId}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> recommendCountriesForTrack(@PathVariable(value = "trackId") Long trackId) {
        Track track = new Track();
        return service.recommendCountries(track);
    }
}

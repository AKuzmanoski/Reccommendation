package mk.ukim.finki.iis.web;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/recommendation")
public class RecommendationController {
    @Autowired
    private MainService service;

    @RequestMapping(value = "/countries")
    public ModelAndView recommendCountriesForTrack(@RequestParam Long trackId) {
        Track track = service.getTrackById(trackId);
        List<Country> recommendedCountries = service.recommendCountries(track);

        ModelAndView modelAndView = new ModelAndView("recommendedCountries");
        modelAndView.addObject("track", track);
        modelAndView.addObject("recommendedCountries", recommendedCountries);
        return modelAndView;
    }
}

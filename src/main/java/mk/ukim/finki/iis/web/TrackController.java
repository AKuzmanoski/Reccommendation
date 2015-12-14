package mk.ukim.finki.iis.web;

import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by User on 12/12/2015.
 */
@Controller
@RequestMapping(value = "/tracks")
public class TrackController {
    @Autowired
    private MainService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Track getTrack(@PathVariable(value = "id") Long id) {
        return service.getTrackById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putTrack(@PathVariable("id") Long id, @Valid Track track) {
        service.insertTrack(track);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String addTrack(Model model) {
        model.addAttribute("track", new Track());
        return "tracks/new";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Track createTrack(@Valid Track track, BindingResult result, HttpServletResponse response) throws BindException {
        track = service.insertTrack(track);

        if (result.hasErrors()) {
            throw new BindException(result);
        }
        response.setHeader("Location", "/tracks/" + track.getEntityId());
        return track;
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTrack(@RequestBody Track track) {
        service.insertTrack(track);
    }
}

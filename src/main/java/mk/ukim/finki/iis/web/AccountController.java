package mk.ukim.finki.iis.web;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.User;
import mk.ukim.finki.iis.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private MainService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String username, @RequestParam String password) {
        User user = service.login(username, password);

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/new")
    public String createSpitterProfile(Model model) {
        User user = new User();
        Country country = new Country();
        user.setCountry(country);
        model.addAttribute("user", user);
        return "account/loginForm";
    }

    @RequestMapping(value = "/new", method=RequestMethod.POST)
    @ResponseBody
    public Object addSpitterFromForm(@Valid User user,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return"spitters/edit";
        }
        return user;
    }
}

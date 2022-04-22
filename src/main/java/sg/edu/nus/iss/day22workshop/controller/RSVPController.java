package sg.edu.nus.iss.day22workshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.day22workshop.models.RSVP;
import sg.edu.nus.iss.day22workshop.service.RSVPService;

@Controller
@RequestMapping(path="/api")
public class RSVPController {
    
    @Autowired
    private RSVPService rsvpSvc;

    @PostMapping(path="/rsvp",consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView postRsvp(@RequestBody MultiValueMap<String,String> form) {

        RSVP rsvp = RSVP.formConvert(form);
        // put in rsvp object, method to check if object (maybe email?) is found, update, not found then insert

        ModelAndView mvc = new ModelAndView();

        mvc.setViewName("index");
        boolean count = rsvpSvc.updateOrInsertRsvp(rsvp);
        if (count == true) {
            mvc.setStatus(HttpStatus.CREATED);
            return mvc;
        }
        mvc.setStatus(HttpStatus.BAD_REQUEST);

        return mvc;
    }
}

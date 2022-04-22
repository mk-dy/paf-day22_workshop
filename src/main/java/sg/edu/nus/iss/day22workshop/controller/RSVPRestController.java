package sg.edu.nus.iss.day22workshop.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.day22workshop.models.RSVP;
import sg.edu.nus.iss.day22workshop.service.RSVPService;

@RestController
@RequestMapping(path="/api")
public class RSVPRestController {
    
    @Autowired
    private RSVPService rsvpSvc;

    @GetMapping(path="/rsvps",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRsvp() {

        List<RSVP> listofRsvp = rsvpSvc.getRsvp();

        JsonObject resp;
        JsonObjectBuilder mainBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        JsonObjectBuilder subBuilder = Json.createObjectBuilder();

        for (int i = 0; i < listofRsvp.size(); i++) {
            subBuilder.add("id", listofRsvp.get(i).getId())
                .add("name",listofRsvp.get(i).getName())
                .add("email",listofRsvp.get(i).getEmail())
                .add("phone",listofRsvp.get(i).getPhone())
                .add("confirmation date",String.valueOf(listofRsvp.get(i).getConfirmationDate()))
                .add("comments",listofRsvp.get(i).getComments());

                arrBuilder.add(subBuilder);
        }

        resp = mainBuilder.add("People Who Have Responded",arrBuilder).build();

        return ResponseEntity.ok(resp.toString());
    }

    @GetMapping(path="/rsvp",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRsvpByName(@RequestParam(name="q") String q) {

        Optional<List<RSVP>> rsvpOpt = rsvpSvc.getRsvpByName(q);

        JsonObject resp;
        JsonObjectBuilder mainBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        JsonObjectBuilder subBuilder = Json.createObjectBuilder();

        if (rsvpOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("RSVP not found");
        }
        List<RSVP> listOfRsvp = rsvpOpt.get();

        for (RSVP rsvp: listOfRsvp) {
            subBuilder.add("id", rsvp.getId())
                .add("name",rsvp.getName())
                .add("email",rsvp.getEmail())
                .add("phone",rsvp.getPhone())
                .add("confirmation date",String.valueOf(rsvp.getConfirmationDate()))
                .add("comments",rsvp.getComments());

                arrBuilder.add(subBuilder);
        }

        resp = mainBuilder.add("People Who Have Replied",arrBuilder).build();

        return ResponseEntity.ok(resp.toString());

    }

    @GetMapping(path="/rsvps/count")
    public ResponseEntity<String> getRsvpCount() {
        Integer count = rsvpSvc.getCount();

        return ResponseEntity.ok("No. of People Who Have RSVP-ed: " + count.toString());
    }


}

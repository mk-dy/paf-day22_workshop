package sg.edu.nus.iss.day22workshop.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day22workshop.models.RSVP;
import sg.edu.nus.iss.day22workshop.repository.RSVPRepository;

@Service
public class RSVPService {
    
    @Autowired
    private RSVPRepository rsvpRepo;

    public List<RSVP> getRsvp() {
        final SqlRowSet rs = rsvpRepo.getRs();

        List<RSVP> listofRsvp = new LinkedList<>();

        while(rs.next()) {
            RSVP rsvp = RSVP.create(rs);
            listofRsvp.add(rsvp);
        }
        
        return listofRsvp;
    }

    public Optional<List<RSVP>> getRsvpByName(String input) {
        final SqlRowSet rs = rsvpRepo.getRsByName(input);
        
        List<RSVP> listofRsvp = new LinkedList<>();

        if (!rs.next()) {
            return Optional.empty();
        }

        rs.beforeFirst();
        while(rs.next()) {
            RSVP rsvp = RSVP.create(rs);
            listofRsvp.add(rsvp);
        }
        return Optional.of(listofRsvp);
    }

    public Integer getCount() {
        final SqlRowSet rs = rsvpRepo.getHeadcount();

        rs.next();
        Integer count = rs.getInt("headcount");

        return count;
    }

    // try to update first, if true, return 201 success
    // if update and returns false, then run insert statement. 
    public boolean updateOrInsertRsvp(RSVP rsvp) {
        
        boolean updateCount = rsvpRepo.updateRsvp(rsvp);

        if (updateCount == false) {
            boolean insertCount = rsvpRepo.insertRsvp(rsvp);
            return insertCount;
        }

        return updateCount;
        
    }

    

}

package sg.edu.nus.iss.day22workshop.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day22workshop.models.RSVP;
import static sg.edu.nus.iss.day22workshop.repository.Queries.*;

@Repository
public class RSVPRepository {
    
    @Autowired
    private JdbcTemplate template;

    public SqlRowSet getRs() {
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_RSVP);
        
        return rs;
    }
    public SqlRowSet getRsByName(String input) {
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_RSVP_BY_NAME,input);

        return rs;
    }

    public SqlRowSet getHeadcount() {
        SqlRowSet rs = template.queryForRowSet(SQL_COUNT_RSVP);
        
        return rs;
    }

    public boolean updateRsvp(RSVP rsvp) {
        Integer added = template.update(
            SQL_UPDATE_RSVP_BY_EMAIL, 
            rsvp.getName(),
            rsvp.getPhone(),
            rsvp.getConfirmationDate(),
            rsvp.getComments(),
            rsvp.getEmail());
        
            return added > 0;
    }

    public boolean insertRsvp(RSVP rsvp) {
        Integer updatedCount = template.update(
            SQL_INSERT_RSVP,
            rsvp.getName(),
            rsvp.getEmail(),
            rsvp.getPhone(),
            rsvp.getConfirmationDate(),
            rsvp.getComments());
        
        return updatedCount > 0;
    }

}

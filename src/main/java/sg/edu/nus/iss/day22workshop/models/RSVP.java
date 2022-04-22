package sg.edu.nus.iss.day22workshop.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.MultiValueMap;

public class RSVP {
    
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private Date confirmationDate;
    private String comments;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getConfirmationDate() {
        return confirmationDate;
    }
    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public static RSVP create(SqlRowSet rs) {

        RSVP rsvp = new RSVP();
        rsvp.setId(rs.getInt("id"));
        rsvp.setName(rs.getString("name"));
        rsvp.setEmail(rs.getString("email"));
        rsvp.setPhone(rs.getString("phone"));
        rsvp.setComments(rs.getString("comments"));
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date confirmDate = format.parse(rs.getString("confirmation_date"));
            rsvp.setConfirmationDate(confirmDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsvp;
    }

    public static RSVP formConvert(MultiValueMap<String, String> form) {
        RSVP rsvp = new RSVP();
        rsvp.setName(form.getFirst("name"));
        rsvp.setEmail(form.getFirst("email"));
        rsvp.setPhone(form.getFirst("phone"));
        rsvp.setComments(form.getFirst("comments"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date confirmDate;
        try {
            confirmDate = format.parse(form.getFirst("date"));
            rsvp.setConfirmationDate(confirmDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }        

        return rsvp;
    }
}

package sg.edu.nus.iss.day22workshop.repository;

public interface Queries {
    
    public static final String SQL_SELECT_RSVP = "select * from rsvp;";

    public static final String SQL_SELECT_RSVP_BY_NAME = "select * from rsvp where name like concat('%',?,'%')";
    // took me a while to solve the %?% sql expression issue, had to use concat() 

    public static final String SQL_COUNT_RSVP = "select count(id) as headcount from rsvp";

    public static final String SQL_UPDATE_RSVP_BY_EMAIL = 
    "update rsvp set name = ?, phone = ?, confirmation_date = ?, comments = ? where email = ?";

    public static final String SQL_INSERT_RSVP = 
    "insert into rsvp(name, email, phone, confirmation_date, comments) values (?, ?, ?, ?, ?)";

}

package eventorganiser.dir.API;

import eventorganiser.dir.DBMethods.DBClasses.Event;
import eventorganiser.dir.DBMethods.DBRespositories.AttendanceRepository;
import eventorganiser.dir.DBMethods.DBRespositories.EventRepository;
import eventorganiser.dir.DBMethods.DBRespositories.TeamsRepository;
import eventorganiser.dir.DBMethods.DBRespositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EventsAPI {

    private EventRepository eventRepository;
    private TeamsRepository teamsRepository;
    private UserRepository userRepository;
    private AttendanceRepository attendanceRepository;

    @Autowired
    public EventsAPI(EventRepository eventRepository, TeamsRepository teamsRepository,
                     UserRepository userRepository, AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
        this.eventRepository = eventRepository;
        this.teamsRepository = teamsRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/api/get/events")
    public Object getEvents(@RequestParam(value="sortBy", defaultValue = "") String sort, @RequestParam(value="userId", defaultValue = "") Integer userId){
        List<Event> allEvents = null;

        if(sort.isEmpty() || sort.isBlank()) {
            allEvents = eventRepository.getAllEvents();
        }
        else if(sort.equals("chronological") && userId == null){
            allEvents = eventRepository.getAllEventsInChronologicalOrder();
            System.out.println("");
        }else if (sort.equals("chronological")){
            allEvents = userRepository.getFutureUserEventsFromUserIdInChronologicalOrder(userId);
        }
        return allEvents;
    }

    @GetMapping("/api/get/event")
    public Object getEventById(@RequestParam(value="id", required = false) Integer id) {
        Object event;
        if(id == null)
            event = eventRepository.getRandomEvent();
        else
            event = eventRepository.getEventDetailsByEventId(id);
        return event;
    }

    @GetMapping("/api/get/eventId")
    public Object getEventIDByNameAndDate(@RequestParam(value="name") String name, @RequestParam(value="date") String date) {
        //TODO
        return eventRepository.getEventIdFromEventNameAndDate(name,date);
    }

    @GetMapping("/api/get/events/created")
    public Object getEventsCreatedbyUserId(@RequestParam(value="userId") Integer userId){
        return eventRepository.getEventsCreatedbyUserId(userId);
    }

    @GetMapping("/api/get/eventAttendance")
    @ResponseBody
    public Object getEventAttendanceById(@RequestParam(value="id") Integer id) {

        return attendanceRepository.getEventAttendanceRecordsByEventId(id);
    }

    @GetMapping("/api/get/event/attendees")
    public Object getEventAttendeesByEventId(@RequestParam(value="eventid") Integer id){
        return eventRepository.getEventAttendeesByEventId(id);
    }

    @GetMapping("/api/post/event/team/update")
    @ResponseBody
    public int updateTeam(@RequestParam(value="userId") Integer userId,
                              @RequestParam(value="eventId") Integer eventId,
                              @RequestParam(value="teamId") Integer teamId){
        return eventRepository.updateUserTeam(userId,eventId,teamId);
    }


    @GetMapping("/api/get/event/teams")
    public Object getEventTeams(@RequestParam(value="eventid") Integer id) {

        return teamsRepository.getAllTeamsInEventByEventId(id);
    }


    ///////////////////////    POST     ////////////////////////////////

    // Catches incorrect format when user inputs date and time
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        binder.registerCustomEditor(Time.class, new CustomDateEditor(timeFormat, false));
    }

    @RequestMapping(value="/api/post/event/new", method=RequestMethod.GET)
    public boolean submitEvent(HttpServletRequest req,
                               @RequestParam(name="eventName") String eventTitle,
                               @RequestParam(name="eventDesc") String eventDescription,
                               @RequestParam(name="eventLocSt1") String address1,
                               @RequestParam(name="eventLocSt2") String address2,
                               @RequestParam(name="eventLocCity") String city,
                               @RequestParam(name="eventLocPost") String postcode,
                               @RequestParam(name="eventStartDate") String startDate,
                               @RequestParam(name="eventEndDate") String endDate,
                               @RequestParam(name="eventStartTime") String startTime,
                               @RequestParam(name="eventEndTime") String endTime,
                               @RequestParam(name="eventOrganiser") int eventOrganiser,
                               @RequestParam(name="maxTeamSize") int maxTeamSize,
                               @RequestParam(name="dateCreated") String dateCreated,
                               @RequestParam(name="eventColour") String eventColour){

        return 1 == eventRepository.postNewEvent(eventTitle,eventDescription,address1,address2,city,postcode,startDate,
                endDate,startTime,endTime,eventOrganiser, maxTeamSize, dateCreated, eventColour);

    }

    @RequestMapping(value="/api/post/event/slack", method=RequestMethod.POST)
    public String submitEventFromSlack(@Valid @ModelAttribute Event event, BindingResult bindingResult){
        System.out.println("TEST");
        return null;
    }

    ///////////////////////    DELETE     ////////////////////////////////

    @DeleteMapping("/api/delete/event")
    public boolean deleteEvents(@RequestParam(value="eventId") Integer eventId){
        return eventRepository.deleteEvents(eventId) == 1;
    }
}

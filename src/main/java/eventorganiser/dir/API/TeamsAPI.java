package eventorganiser.dir.API;

import eventorganiser.dir.DBMethods.DBRespositories.TeamsRepository;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TeamsAPI {

    private TeamsRepository teamsRepository;

    public TeamsAPI(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    // get all Teams
    @GetMapping("/api/get/teams")
    public Object APIgetAllTeams() {
        return teamsRepository.getAllTeams();
    }

    // get random Team
    @GetMapping("/api/get/team/random")
    public Object APIgetRandomTeam() {
        return teamsRepository.getRandomTeam();
    }

    // get max size of a Team
    @GetMapping("/api/get/team/maxsize")
    public Object APIgetTeamMaxSizeFromTeamId(@RequestParam(name="teamId", required=true) String teamId) {
        int teamIdInt = Integer.parseInt(teamId);
        return teamsRepository.getTeamMaxSizeFromTeamId(teamIdInt);
    }

    // get Team Id
    @GetMapping("/api/get/team")
    public Object APIgetTeamIdFromEventId(@RequestParam(name="eventId", required=true) String eventId,
                                          @RequestParam(name="userId", required=true) String userId) {
        System.out.println("Event ID is: " + eventId);
        System.out.println("User ID is: " + userId);
        int eventIdInt = Integer.parseInt(eventId);
        int userIdInt = Integer.parseInt(userId);
        return teamsRepository.getTeamIdFromEventIdAndUserId(eventIdInt, userIdInt);
    }

    @GetMapping("/api/get/event/team")
    public int getTeamIdFromTeamNameAndEventId(@RequestParam(name="teamName") String teamName,
                                                       @RequestParam(name="eventId") int eventId){
        return teamsRepository.getTeamIdFromTeamNameAndEventId(teamName,eventId);
    }

    @GetMapping("/api/get/event/teammates")
    public Object APIgetTeammatesFromUserIdFromEventId(@RequestParam(name="eventid", required=true) String eventId,
                                                       @RequestParam(name="teamid", required=true) String teamId) {
        System.out.println("Event ID is: " + eventId);
        System.out.println("Team ID is: " + teamId);
        int eventIdInt = Integer.parseInt(eventId);
        int teamIdInt = Integer.parseInt(teamId);
        return teamsRepository.getTeamMembers(eventIdInt, teamIdInt);
    }

    // get Team Name
    @GetMapping("/api/get/team/name")
    public Object APIgetTeamNameFromTeamId(@RequestParam(name="teamId", required=true) String teamId) {
        int teamIdInt = Integer.parseInt(teamId);
        return teamsRepository.getTeamNameFromTeamId(teamIdInt);
    }

    @RequestMapping(value="/api/post/team/new", method=RequestMethod.GET)
    public boolean submitNewTeam(HttpServletRequest req,
                                 @RequestParam(name="eventId") int eventId,
                                 @RequestParam(name="teamName") String teamName){

                return 1 == teamsRepository.postNewTeam(eventId, teamName);
    }

    @RequestMapping(value="/api/post/team/update", method=RequestMethod.GET)
    public boolean updateTeam(HttpServletRequest req,
                                @RequestParam(name="teamId") int teamId,
                                @RequestParam(name="teamName") String teamName){
                return 1 == teamsRepository.editTeamName(teamId, teamName);
    }
}

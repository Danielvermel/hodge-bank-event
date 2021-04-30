package eventorganiser.dir.API;

import eventorganiser.dir.DBMethods.DBRespositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAPI {

    private UserRepository userRepository;

    @Autowired
    public UserAPI(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all Users
    @GetMapping("/api/get/users")
    public Object APIgetAllUsers() {
        return userRepository.getAllUsers();
    }

    // get User id
    @GetMapping("/api/get/user")
    public  Object APIgetUserIdFromUsername(@RequestParam(name = "username", required = true) String username) {
        return userRepository.getUserIdFromUsername(username);
    }

    // get User Full name
    @GetMapping("/api/get/userfullname")
    public Object APIgetUserFullNameFromUsername(@RequestParam(name="username", required=true) String username) {
        return userRepository.getUserFullNAmeFromUsername(username);
    }

    // get details from a users with an userid
    @GetMapping("/api/get/user/details")
    public Object APIget(@RequestParam(name="userId", required=false) String userId) {
        return userRepository.getUserDetailsFromId(userId);
    }

    // get random User
    @GetMapping("/api/get/user/random")
    public Object APIgetRandomUser() {
        return userRepository.getRandomUser();
    }

    // get team from a users with an User Id
    @GetMapping("/api/get/user/teams")
    public Object APIgetUserTeamsFromUserId(@RequestParam(name="userId", required=true) String userId) {
        int userIdInt = Integer.parseInt(userId);
        return userRepository.getUserTeamsFromUserId(userIdInt);
    }

    // get User Group
    @GetMapping("/api/get/user/group")
    public Object APIgetUserGroupsFromUserId(@RequestParam(name="userId", required=true) String userId) {
        int userIdInt = Integer.parseInt(userId);
        return userRepository.getUserGroupsFromUserId(userIdInt);
    }

    // get User team
    @GetMapping("/api/get/userteams/events")
    public Object APIgetUserTeamForEventByUserIdAndEventId(@RequestParam(name="userId", required=true) String userId, @RequestParam(name="eventId", required=true) String eventId) {
        int userIdInt = Integer.parseInt(userId);
        int eventIdInt = Integer.parseInt(eventId);
        return userRepository.getUserTeamForEventByUserIdAndEventId(userIdInt,eventIdInt);
    }


















//    @GetMapping("/{id}", produces = "application/json")
//    public Book getBook(@PathVariable int id) {
//        return findBookById(id);
//    }



//    @GetMapping("/api/get/user/{userId}") // get user details from userId
//    @ResponseBody
//    public List<User> getUser(@PathVariable("userId") String userId) {
//        return userService.getUserDetailsFromId(userId);
//    }

//    @GetMapping("/api/get/user/id/{username}") // get a specific user id with username
//    @ResponseBody
//    public Object getUserId(@PathVariable("username") String username) {
//
//        return userService.getUserIdFromUsername(username);
//    }
//

//


//    @GetMapping("/api/get/attendance") // get a specific user id with username
//    @ResponseBody
//    // "www.OURWEBSITE.com/api/get/user?username=101test@gmail.com"
//    //@RequestParam(name="phrase", required=false) String phrase
//    public List<AttendanceRecord> getUserAttendace(@RequestParam(name="username", required=true) String username) {
//        String id = Integer.toString(userService.getUserIdFromUsername(username));
//        return userService.getUserAttendanceRecordsFromUserId(id);
//    }

}


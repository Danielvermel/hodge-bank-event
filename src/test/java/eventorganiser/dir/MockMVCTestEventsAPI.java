package eventorganiser.dir;

import eventorganiser.dir.API.EventsAPI;
import eventorganiser.dir.API.TeamsAPI;
import eventorganiser.dir.API.UserAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
//@SpringBootTest
@AutoConfigureMockMvc
public class MockMVCTestEventsAPI {

    private MockMvc mockMvc;

    @MockBean
    EventsAPI eventsAPI;


    @Test
    public void test_EventAPI_Get() throws  Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(eventsAPI).build();
        mockMvc.perform(get("/api/get/events")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/event?id=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/eventId?name=Fest&date=2020-03-29")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/events/created?userId=100")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/eventAttendance?id=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/event/attendees?eventid=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/event/teams?eventid=1")).andExpect(status().isOk());
    }


    @Test
    public void test_EventAPI_Post() throws  Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(eventsAPI).build();

        mockMvc.perform(get("/api/post/event/team/update?userId=100&eventId=1&teamId=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/post/event/new?eventName=Party&eventDesc=house&eventLocSt1=8&eventLocSt2=Lanbbledian&eventLocCity=Cardiff&eventLocPost=&eventStartDate=2222-12-22&eventEndDate=2222-12-22&eventStartTime=22:00&eventEndTime=24:00&eventOrganiser=100&maxTeamSize=0&dateCreated=222-12-20&eventColour=#3f8116")).andExpect(status().isOk());

    }


}
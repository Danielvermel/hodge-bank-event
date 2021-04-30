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
public class MockMVCTestTeamsAPI {

    private MockMvc mockMvc;

    @MockBean
    TeamsAPI teamsAPI;


    @Test
    public void test_TeamAPI_Get() throws  Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(teamsAPI).build();

        mockMvc.perform(get("/api/get/teams")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/team/random")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/team/maxsize?teamId=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/team?eventId=1&userId=100")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/event/team?teamName=monkeys&eventId=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/event/teammates?eventid=1&teamid=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/team/name?teamId=1")).andExpect(status().isOk());

    }

    @Test
    public void test_TeamAPI_Post() throws  Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(teamsAPI).build();

        mockMvc.perform(get("/api/post/team/new?eventId=1&teamName=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/post/team/update?teamId=1&teamName=1")).andExpect(status().isOk());

    }

}
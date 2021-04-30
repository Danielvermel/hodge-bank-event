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
public class MockMVCTestUserAPI {

    private MockMvc mockMvc;

    @MockBean
    UserAPI userAPI;

    @Test
    public void test_userAPI_Get() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userAPI).build();
        mockMvc.perform(get("/api/get/users")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/user?username=daniel@gmail.com")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/userfullname?username=daniel@gmail.com")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/user/details?userId=100")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/user/random")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/user/teams?userId=100")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/user/group?userId=100")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/user/teams?userId=100&eventId=1")).andExpect(status().isOk());

    }


}
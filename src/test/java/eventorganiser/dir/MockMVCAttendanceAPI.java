package eventorganiser.dir;

import eventorganiser.dir.API.AttendanceAPI;
import eventorganiser.dir.API.EventsAPI;
import eventorganiser.dir.API.TeamsAPI;
import eventorganiser.dir.API.UserAPI;
import eventorganiser.dir.DBMethods.DBClasses.Event;
import eventorganiser.dir.base.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
//@SpringBootTest
@AutoConfigureMockMvc
public class MockMVCAttendanceAPI {

    private MockMvc mockMvc;

    @MockBean
    AttendanceAPI attendanceAPI;


    @Test
    public void test_userAPI_Get() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(attendanceAPI).build();
        mockMvc.perform(get("/api/get/user/attendance?userId=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/user/attending?userId=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/event/attendance?eventId=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/attendance/all")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/attendance/random")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/user/attendance?userId=1&eventId=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/get/attendance/record?userId=1&eventId=100")).andExpect(status().isOk());
     }


    @Test
    public void test_userAPI_Post() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(attendanceAPI).build();

        mockMvc.perform(get("/api/post/attendance/update?userId=1&eventId=1&response=1&attendanceId=1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/post/attendance/new?userId=1&eventId=1&response=1")).andExpect(status().isOk());
    }


}
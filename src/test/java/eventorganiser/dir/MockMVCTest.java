package eventorganiser.dir;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import eventorganiser.dir.API.EventsAPI;
import eventorganiser.dir.API.UserAPI;
import eventorganiser.dir.MainController;
import eventorganiser.dir.base.BaseController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
//@SpringBootTest
@AutoConfigureMockMvc
public class MockMVCTest {

    private MockMvc mockMvc;

    @Mock
    BaseController controller;

    @Test
    public void test_feedbackController() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/feedPage"))
               .andExpect(status().isOk());
    }

    @Test
    public void test_CalendarController() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/calendar"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_AddEventController() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/addEvent"))
                .andExpect(status().isOk());
    }

    @Test
    public void test_DeleteEventController() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/deleteEvent"))
                .andExpect(status().isOk());
    }

}
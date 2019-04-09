package pers.quzhiyu.graduationproject.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenCreateSuccess() throws Exception {

        LocalDateTime lastdate = LocalDateTime.of(2018,6,29,9,24);
        LocalDateTime date = LocalDateTime.now();

        String content = "{\"name\":\"time\",\"fatherId\":1," +
                "\"description\":\"YYY小组，组长是YYY\"," +
                "\"startTime\":"+lastdate.toInstant(ZoneOffset.ofHours(8)).toEpochMilli()+","+
                "\"endTime\":"+date.toInstant(ZoneOffset.ofHours(8)).toEpochMilli()+","+
                "\"quantity\":20,"+
                "\"filePath\":\"/local/file/a.txt\"," +
                "\"stage\":1}";

        System.out.println(date.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        System.out.println(content);

        mockMvc.perform(MockMvcRequestBuilders.post("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenGetInfoByIdSuccess() throws Exception {
        String content = mockMvc.perform(MockMvcRequestBuilders.get("/task/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void whenGetAllInfoSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        LocalDateTime lastdate = LocalDateTime.of(2018,6,29,9,24);
        LocalDateTime date = LocalDateTime.now();

        String content = "{\"id\":2,\"name\":\"time\",\"fatherId\":1," +
                "\"description\":\"zzz小组，组长是YzYY\"," +
                "\"startTime\":"+lastdate.toInstant(ZoneOffset.ofHours(8)).toEpochMilli()+","+
                "\"endTime\":"+date.toInstant(ZoneOffset.ofHours(8)).toEpochMilli()+","+
                "\"quantity\":10,"+
                "\"filePath\":\"/local/file/bR.txt\"," +
                "\"stage\":1}";

        String result = mockMvc.perform(MockMvcRequestBuilders.put("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/task/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenAddMemberSuccess() throws Exception {
        String content = "[1,2,3,4,5]";

        String result = mockMvc.perform(MockMvcRequestBuilders.post("/task/2/member")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenDeleteMemberSuccess() throws Exception {

        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/task/2/member/1,2,3,4,5")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenDistributeSuccess() throws Exception {
        String content = "{\"taskId\":1,\"staffId\":1,\"groupId\":1}";

        String result = mockMvc.perform(MockMvcRequestBuilders.post("/task/distribute")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenUpdateTaskInfoSuccess() throws Exception {
        String content = "{\"id\":1,\"taskId\":2,\"staffId\":3,\"groupId\":4}";

        String result = mockMvc.perform(MockMvcRequestBuilders.put("/task/distribute")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}

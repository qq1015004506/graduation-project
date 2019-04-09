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

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        LocalDateTime date = LocalDateTime.now();

        String content = "{\"filename\":\"b.txt\"," +
                "\"uploadTime\":"+date.toInstant(ZoneOffset.ofHours(8)).toEpochMilli()+","+
                "\"staffId\":2," +
                "\"groupId\":2," +
                "\"commit\":\"增加了XXX功能\","+
                "\"version\":1}";

        System.out.println(content);

        mockMvc.perform(MockMvcRequestBuilders.post("/code")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenGetInfoByIdSuccess() throws Exception {
        String content = mockMvc.perform(MockMvcRequestBuilders.get("/code/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void whenGetAllInfoSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/code")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        String content = "{\"id\":\"2\"," +
                "\"filename\":\"c.txt\"," +
                "\"uploadTime\":"+date.toInstant(ZoneOffset.ofHours(8)).toEpochMilli()+","+
                "\"staffId\":2," +
                "\"groupId\":3," +
                "\"commit\":\"增加了YYY功能\","+
                "\"version\":1}";

        String result = mockMvc.perform(MockMvcRequestBuilders.put("/code")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/code/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenAddMemberSuccess() throws Exception {
        String content = "[1,2,3,4,5]";

        String result = mockMvc.perform(MockMvcRequestBuilders.post("/code/2/member")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenDeleteMemberSuccess() throws Exception {

        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/code/2/member/1,2,3,4,5")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}

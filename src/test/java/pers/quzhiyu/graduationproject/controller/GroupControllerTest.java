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

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        String content = "{\"name\":\"jack\",\"leaderId\":\"6\"," +
                "\"info\":\"YYY小组，组长是YYY\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/group")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenGetInfoByIdSuccess() throws Exception {
        String content = mockMvc.perform(MockMvcRequestBuilders.get("/group/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void whenGetAllInfoSuccess() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/group")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        String content = "{\"id\":\"2\",\"name\":\"呵呵\",\"leaderId\":\"7\",\"info\":\"ZZZ小组，ZZZ\"}";

        String result = mockMvc.perform(MockMvcRequestBuilders.put("/group")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/group/3")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenAddMemberSuccess() throws Exception {
        String content = "[1,2,3,4,5]";

        String result = mockMvc.perform(MockMvcRequestBuilders.post("/group/2/member")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}

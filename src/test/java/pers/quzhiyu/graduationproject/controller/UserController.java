package pers.quzhiyu.graduationproject.controller;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.jdbc.SQL;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pers.quzhiyu.graduationproject.dto.GroupInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    private String folder = "D:\\source\\graduation-project\\src\\main\\java\\pers\\quzhiyu\\graduationproject\\controller";

    @Test
    public void testFileReader() throws IOException {
        File file = new File(folder,"FileController.java");
        try(InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(isr)) {
            StringBuilder sb = new StringBuilder();
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                sb.append(lineTxt+"\n");
            }
        }
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get("/user/coder")
                .param("username", "zhangsan")
                .param("password", "123123")
                .param("id", "2")
                .param("size", "15")
                .param("page", "2")
                .param("sort", "username desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getRequest().getContentAsString();
    }

    @Test
    public void whenGetInfoByIdSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/staff/4")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        String content = "{\"username\":\"tom\",\"password\":\"123123\"," +
                "\"name\":\"屈治宇\",\"job\":\"1\",\"email\":\"1015004506@qq.com\",\"phone\":\"15076022053\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/staff")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        String content = "{\"id\":1,\"username\":\"tom\",\"password\":\"null\"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.put("/user/coder/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/coder/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUploadSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
                .file(new MockMultipartFile("file","test.txt","multipart/form-data","hello upload".getBytes())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test(){
        List<Long> arr = Arrays.asList(1L,2L,3L,4L,5L);

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `groupinfo`\n");
        sb.append("(group_id, staff_id)\n");
        sb.append("VALUES\n");
        for(Long l : arr) {
            sb.append("(" + "1" + ", " + l.toString() + "), ");
        }
        System.out.println(sb.subSequence(0,sb.length()-2).toString());

    }

}

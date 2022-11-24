package springbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import springbackend.model.Blog;
import springbackend.services.BlogService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(BlogController.class)
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BlogService blogService;

    //if using spring security then need to MockBean that also

    @Test
    void shouldGetAllBlogs() throws Exception {
        //arrange
        List<Blog> listBlogs = new ArrayList<>();
        listBlogs.add(new Blog("1","First blog", "Hello World!" ));
        listBlogs.add(new Blog("2","Second blog", "Anyone there!" ));
        listBlogs.add(new Blog("2","Third blog", "Hello...." ));
        Mockito.when(blogService.getAllBlogs()).thenReturn(listBlogs);

        String url = "/blogs/list";

        mockMvc.perform(get(url)).andExpect(status().isOk());

    }

}
package springbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import springbackend.model.Blog;
import springbackend.services.BlogService;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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

        //act
        given(blogService.getAllBlogs()).willReturn(listBlogs);
        String url = "/blogs/list";
        ResultActions response = mockMvc.perform(get(url));

        //assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",
                        is(listBlogs.size())));
    }

    @Test
    void shouldCreateBlog() throws Exception {
        //arrange
        String blogId = UUID.randomUUID().toString().split("-")[0];
        Blog blog = Blog.builder()
                .title("My first blog")
                .content("Hello world")
                .build();
        given(blogService.addBlog(any(Blog.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        //act
        ResultActions response = mockMvc.perform(post("/blogs/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(blog)));
        //assert
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title",
                        is(blog.getTitle())))
                .andExpect(jsonPath("$.content",
                        is(blog.getContent())));
    }
}
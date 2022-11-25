package springbackend.controller;

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
import static org.mockito.Mockito.when;

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

    @Test
    void shouldGetBlogByTitle() throws Exception {
        //arrange
        String blogId = UUID.randomUUID().toString().split("-")[0];
        Blog blog = Blog.builder()
                .title("My first blog")
                .content("Hello world")
                .build();
        String titleToSearch = "First blog";
        given(blogService.findBlogByTitle(titleToSearch)).willReturn(blog);

        //act
        ResultActions response = mockMvc.perform(get("/blogs/title/{title}", titleToSearch));

        //assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(blog.getTitle())))
                .andExpect(jsonPath("$.content", is(blog.getContent())));
    }

    @Test
    void shouldGetBlogByID() throws Exception {
        //arrange
        String blogId = UUID.randomUUID().toString().split("-")[0];
        Blog blog = Blog.builder()
                .title("My first blog")
                .content("Hello world")
                .build();
        when(blogService.findBlogById(blogId)).thenReturn(Optional.of(blog));

        //act
        ResultActions response = mockMvc.perform(get("/blogs/{blogId}", blogId));

        //assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(blog.getTitle())))
                .andExpect(jsonPath("$.content", is(blog.getContent())));
    }

    @Test
    void shouldUpdateBlog() throws Exception {
        //arrange
        String blogId = UUID.randomUUID().toString().split("-")[0];
        Blog savedBlog = Blog.builder()
                .title("My first blog")
                .content("Hello world")
                .build();

        Blog updatedBlog = Blog.builder()
                .title("What does Mastery involve?")
                .content("Welcome to my mastery blog...")
                .build();

        given(blogService.findBlogById(blogId)).willReturn(Optional.of(savedBlog));
        given(blogService.updateBlog(any(Blog.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        //act
        ResultActions response = mockMvc.perform(put("/blogs", blogId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBlog)));

        //assert
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title", is(updatedBlog.getTitle())))
                .andExpect(jsonPath("$.content", is(updatedBlog.getContent())));
    }

    //add testcase for negative scenario in updateBlog method

    @Test
    void deleteBlog() throws Exception {
        //arrange
        String blogID = UUID.randomUUID().toString().split("-")[0];
        willDoNothing().given(blogService).deleteBlog(blogID);

        //act
        ResultActions response = mockMvc.perform(delete("/blogs/{blogID}", blogID));

        //assert
        response.andExpect(status().isOk());
    }
}
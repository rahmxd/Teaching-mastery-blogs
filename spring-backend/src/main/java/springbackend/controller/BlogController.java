package springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springbackend.model.Blog;
import springbackend.services.BlogService;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody Blog blog){
        return blogService.addBlog(blog);
    }

    @GetMapping
    public List<Blog> getAllBlogs(){
        return blogService.getAllBlogs();
    }

//    @GetMapping
//    public List<Blog> getBlogByTitle(String title){
//        return blogService.findBlogByTitle(title);
//    }

    //add get blog by id later


    @PutMapping
    public Blog updateBlog(@RequestBody Blog blog){
        return blogService.updateBlog(blog);
    }

    @DeleteMapping("/{blogID}")
    public String deleteBlog(String blogID){
        return blogService.deleteBlog(blogID);
    }
}

package springbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springbackend.model.Blog;
import springbackend.services.BlogService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody Blog blog){
        return blogService.addBlog(blog);
    }

    @GetMapping("/list")
    public List<Blog> getAllBlogs(){
        return blogService.getAllBlogs();
    }

    @GetMapping("/title/{title}")
    public Blog getBlogByTitle(@PathVariable String title){
        return blogService.findBlogByTitle(title);
    }

    @GetMapping("/{blogID}")
    public Optional<Blog> getBlogByID(@PathVariable String blogID){
        return blogService.findBlogById(blogID);
    }

    @PutMapping
    public Blog updateBlog(@RequestBody Blog blog){
        return blogService.updateBlog(blog);
    }

    @DeleteMapping("/{blogID}")
    public String deleteBlog(String blogID){
        return blogService.deleteBlog(blogID);
    }
}

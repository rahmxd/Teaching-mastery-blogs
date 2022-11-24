package springbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbackend.model.Blog;
import springbackend.repository.BlogRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BlogService {

    //look to implement with Interface instead
    @Autowired
    private BlogRepository repository;

    public Blog addBlog(Blog blog){
        blog.setBlogID(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(blog);
    }

    public List<Blog> getAllBlogs(){
        return repository.findAll();
    }

    public Blog findBlogById(String blogID){
        return repository.findById(blogID).get();
    }

    public List<Blog> findBlogByTitle(String title){
        return repository.findByTitle(title);
    }

    //update
    public Blog updateBlog(Blog blog){
        Blog existingBlog = repository.findById(blog.getBlogID()).get();
        existingBlog.setContent(blog.getContent());
        existingBlog.setTitle(blog.getContent());
        return repository.save(existingBlog);
    }

    //deleteBlog
    public String deleteBlog(String blogID){
        repository.deleteById(blogID);
        return blogID + " blog deleted from feed ";
    }

}

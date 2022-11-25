package springbackend.services;

import springbackend.model.Blog;

import java.util.List;
import java.util.Optional;

public interface IBlogService {

    Blog addBlog(Blog blog);

    List<Blog> getAllBlogs();

    Optional<Blog> findBlogById(String blogID);

    Blog findBlogByTitle(String title);

    Blog updateBlog(Blog blog);

    void deleteBlog(String blogID);
}

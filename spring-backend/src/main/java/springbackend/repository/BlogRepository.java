package springbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import springbackend.model.Blog;

import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, String> {
    // @Query("{ 'title' : ?0 }")
    Blog findByTitle(String title);
}

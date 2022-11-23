package springbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "blogs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    private String blogID;
    private String title;
    private String content;
}

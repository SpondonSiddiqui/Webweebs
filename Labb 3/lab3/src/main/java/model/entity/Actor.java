package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Actor implements Serializable {

    @Id
    @NonNull private String name;
    @NonNull private String birthday;
    @NonNull private String deathday;
    @NonNull private String bio;
    @NonNull private String id;
    @NonNull private String picture_path;
    
    
    @ManyToMany(mappedBy = "actors") private List<Movie> moviesActedIn;
}

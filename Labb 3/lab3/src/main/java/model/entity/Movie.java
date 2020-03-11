package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Movie implements Serializable {

    @Id
    @NonNull private String name;
    @NonNull private Integer releaseYear;
    @NonNull private Integer rating;
    @NonNull private String description;
    @NonNull private String reviews;
    
    @JoinTable(name = "actor_list",
            joinColumns = @JoinColumn(name = "movie"),
            inverseJoinColumns = @JoinColumn(name = "actor"))
    @ManyToMany private List<Actor> actors;
    
   
}

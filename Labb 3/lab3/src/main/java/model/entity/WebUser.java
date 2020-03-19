package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class WebUser implements Serializable {

    @Id
    @NonNull private String username;
    @NonNull private String password;
    
    /*@NonNull
    @OneToMany(mappedBy = "")
    private List<Review> reviews;*/
    
    /*@JoinTable(name = "watch_list",
            joinColumns = @JoinColumn(name = "user"),
            inverseJoinColumns = @JoinColumn(name = "movie"))
    @ManyToMany private List<Movie> watchList;*/
}

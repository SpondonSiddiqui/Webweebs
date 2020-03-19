package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchList implements Serializable {
    
    @Id
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Movie> movies;
    
    
    @OneToOne
    @JoinColumn(name = "byUser")
    private WebUser user;
}

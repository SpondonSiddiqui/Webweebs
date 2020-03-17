package model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Review implements Serializable {

    @Id
    //@NonNull private String postedBy;
    //@NonNull private String postedWhen;
    @NonNull private String content;
    //@NonNull private Double rating;
    
    @JoinColumn(name = "movie_id")
    @ManyToOne private Movie movie;

}

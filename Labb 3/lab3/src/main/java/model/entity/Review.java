package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
    @NonNull private String content;
    
    @OneToOne
    @NonNull private WebUser postedBy;
    
    @NonNull private String postedWhen;    
    @NonNull private String rating;
    
    
    @ManyToOne
    @NonNull
    @JoinColumn(name = "movie_reviewed")
    private Movie movieReviewed;

}

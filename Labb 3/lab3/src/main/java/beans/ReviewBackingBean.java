package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.MovieDAO;
import model.dao.ReviewDAO;
import model.entity.Movie;
import model.entity.Review;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Faces;

@Data
@Named
@ViewScoped
public class ReviewBackingBean implements Serializable {

    @Inject
    @Param(name = "id")
    private String id;
    
    @EJB
    private MovieDAO movieDAO;
    
    @EJB
    private ReviewDAO reviewDAO;
    
    private List<Review> reviews;
    private String contentInput;
    private Movie movie;
    
    @PostConstruct
    private void init() {
        try {
            movie = movieDAO.getMovie(id);         
        } catch (IOException e) {
            System.out.println(e);
        }
        reviews = new ArrayList<>(reviewDAO.findReviewsByName(movie));
    }
    
    public void submitReview(){
        if (contentInput.equals("")) return;
        
        try {
            movie = movieDAO.getMovie(id);         
        } catch (IOException e) {
            System.out.println(e);
        }
        
        if(movieDAO.findMovieById(movie.getId()) == null) {
            movieDAO.create(movie);
        }
        Review newReview = new Review(contentInput, movie);
        reviewDAO.create(newReview);
        Faces.redirect("detailedMovie.xhtml", id);
    }
}

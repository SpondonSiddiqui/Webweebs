package beans;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.MovieDAO;
import model.dao.ReviewDAO;
import model.dao.UserDAO;
import model.entity.Movie;
import model.entity.Review;
import model.entity.WebUser;
import model.view.UserBean;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Faces;
import org.primefaces.event.RateEvent;

@Data
@Named
@ViewScoped
@ManagedBean
public class ReviewBackingBean implements Serializable {

    @Inject
    @Param(name = "id")
    private String id;
    
    @Inject
    private UserBean userBean;
    
    @EJB
    private MovieDAO movieDAO;
    
    @EJB
    private ReviewDAO reviewDAO;

    @EJB
    private UserDAO userDAO;
    
    private List<Review> reviews;
    private String contentInput;
    private Movie movie;
    private Integer rating;
    
    @PostConstruct
    private void init() {
        try {
            movie = movieDAO.getMovie(id);         
        } catch (IOException e) {
            System.out.println(e);
        }
        reviews = new ArrayList<>(reviewDAO.findReviewsByName(movie));
        rating = 0;
    }
    
    /** submitReview
     * Creates a user review and saves content, rating, user, time and date
     * 
     * @param user user session
     */
    public void submitReview(){
        
        final WebUser webUser = userBean.getUser();
        
        if (contentInput.equals("")) return;
        
        try {
            movie = movieDAO.getMovie(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        if(!movieDAO.checkMovieExists(movie.getTitle())) {
            descriptionTrim();
            movieDAO.create(movie);
        } 

        Review newReview = new Review(contentInput, userDAO.getUserByName(webUser.getUsername()).get(0), 
                formatDate(), rating.toString(), movie);
        reviewDAO.create(newReview);
        
        Faces.redirect("detailedMovie.xhtml?id=" + id);
    }
    
    /**
     * Formats datetime.now();
     * @return string of formatted date
     */
    private String formatDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    
    /**
     * Some descriptions for movies have a length over 255 characters which 
     * the "Overview" column does not support.
     */
    private void descriptionTrim(){
        String desc = movie.getOverview();
        if(desc.length() > 255) movie.setOverview(desc.substring(0, 250) + "...");
    }
}
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import model.dao.ReviewDAO;
import model.entity.Review;

@ViewScoped
public class ReviewBackingBean implements Serializable {

    @EJB
    private ReviewDAO reviewDAO;
    private List<Review> reviews;
    private String contentInput;
    
    @PostConstruct
    private void init() {
        Review test = new Review("Hej");
        reviewDAO.create(test);
        reviews = new ArrayList<>(reviewDAO.findAll());
    }
    
    private void submitReview(){
        if (contentInput.equals("")) return;
        Review newReview = new Review(contentInput);
        reviewDAO.create(newReview);
    }
}

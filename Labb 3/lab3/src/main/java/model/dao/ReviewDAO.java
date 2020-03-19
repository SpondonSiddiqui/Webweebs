package model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.Getter;
import model.entity.Movie;
import model.entity.Review;
import model.entity.WebUser;

@Stateless
public class ReviewDAO extends AbstractDAO<Review, String> {

    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public ReviewDAO() {
        super(Review.class);
    }
    
    public List<Review> findReviewsByName(Movie movie) {
        Query query = entityManager.createQuery("SELECT r FROM Review r "
                + "WHERE r.movieReviewed = :movie");
        query.setParameter("movie", movie);
        return query.getResultList();
    }
    
    public List<Review> findReviewsByUser(WebUser webUser) {
        Query query = entityManager.createQuery("SELECT r FROM Review r "
                + "WHERE r.postedBy = :user");
        query.setParameter("user", webUser);
        return query.getResultList();
    }
}

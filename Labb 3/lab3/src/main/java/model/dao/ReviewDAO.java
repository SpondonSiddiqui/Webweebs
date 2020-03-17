package model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Review;

@Stateless
public class ReviewDAO extends AbstractDAO<Review, String> {

    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public ReviewDAO() {
        super(Review.class);
    }
}

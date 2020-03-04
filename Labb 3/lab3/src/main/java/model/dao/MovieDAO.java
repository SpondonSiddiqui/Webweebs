package model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.Getter;
import model.entity.Movie;

@Stateless
public class MovieDAO extends AbstractDAO<Movie> {

    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public MovieDAO() {
        super(Movie.class);
    }

    public List<Movie> findMoviesByName(String name) {
        Query query = entityManager.createQuery("SELECT m FROM Movie m "
                + "WHERE m.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }
    
    public List<Movie> findMoviesByYear(int year) {
        Query query = entityManager.createQuery("SELECT m FROM Movie m "
                + "WHERE m.releaseYear = :year");
        query.setParameter("year", year);
        return query.getResultList();
    }
    
    public List<Movie> getSortedByRating() {
        Query query = entityManager.createQuery("SELECT m FROM Movie m "
                + "ORDER BY m.rating DESC");
        return query.getResultList();
    }
    
    public List<Movie> getAllMovies(){
        Query query = entityManager.createQuery("SELECT m FROM Movie m");
        return query.getResultList();
    }
    
    
}

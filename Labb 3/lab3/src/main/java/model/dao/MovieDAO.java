package model.dao;

import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.Getter;
import model.entity.Movie;
import model.utils.JsonReader;

@Stateless
public class MovieDAO extends AbstractDAO<Movie> {

    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public MovieDAO() {
        super(Movie.class);
    }

    public List<Movie> findMoviesByName(String title) {
        Query query = entityManager.createQuery("SELECT m FROM Movie m "
                + "WHERE m.title = :title");
        query.setParameter("title", title);
        return query.getResultList();
    }
    
    public List<Movie> findMoviesByYear(int release_date) {
        Query query = entityManager.createQuery("SELECT m FROM Movie m "
                + "WHERE m.release_date = :release_date");
        query.setParameter("release_date", release_date);
        return query.getResultList();
    }
    
    public List<Movie> getSortedByRating() {
        Query query = entityManager.createQuery("SELECT m FROM Movie m "
                + "ORDER BY m.avg_rating DESC");
        return query.getResultList();
    }
    
    public List<Movie> getTopMovies(String url) throws IOException{
        return JsonReader.getMoviesFromUrl(url);
    }
}

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
public class MovieDAO extends AbstractDAO<Movie, String> {

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
    
    public List<Movie> findMoviesByYear(String release_date) {
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
    
    public List<Movie> getTopMovies(String page) throws IOException{
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&sort_by=popularity.desc&include_adult=true&include_video=false";
        return JsonReader.getMoviesFromUrl(url + "&page=" + page);
    }
    
    public List<Movie> searchMovie(String movie) throws IOException{
        return JsonReader.getMoviesFromUrl("https://api.themoviedb.org/3/search/movie?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&query="+movie+"&page=1&include_adult=true");
    }
}

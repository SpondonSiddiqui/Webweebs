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
    
    public boolean checkMovieExists(String title) {
        Query query = entityManager.createQuery("SELECT m FROM Movie m "
                + "WHERE m.title = :title");
        query.setParameter("title", title);
        return query.getResultList().size() == 1;
    }
    
    public List<Movie> findMovieById(String id) {
        Query query = entityManager.createQuery("SELECT m FROM Movie m "
                + "WHERE m.id = :id");
        query.setParameter("id", id);
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
    
    public List<Movie> getAllMovies(){
        Query query = entityManager.createQuery("SELECT m FROM Movie m");
        return query.getResultList();
    }

      /**
     * Get the most popluar movies at the moment by page.
     * @param page results page number
     * @return List of movies 
     * @throws IOException 
     */
    public List<Movie> getTopMovies(String page) throws IOException{
        return JsonReader.getMoviesFromUrl("https://api.themoviedb.org/3/movie/popular?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&page="+page);
    }
        
    /**
     * Get the most popluar movies at the moment
     * @return List of movies
     * @throws IOException 
     */
    public List<Movie> getTopMovies() throws IOException{
        return getTopMovies("1");
    }
    
    /**
     * Get upcoming movies by page
     * @param page
     * @return 
     * @throws IOException 
     */
    public List<Movie> getUpcomingMovies(String page) throws IOException{
        return JsonReader.getMoviesFromUrl("https://api.themoviedb.org/3/movie/upcoming?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&page="+page+"&region=SE");
    }
    
    /**
     * Get upcoming movies
     * @return
     * @throws IOException 
     */
    public List<Movie> getUpcomingMovies() throws IOException{
        return getUpcomingMovies("1");
    }
    
    /**
     * Get movies now playing in theaters by page
     * @param page
     * @return
     * @throws IOException 
     */
    public List<Movie> getNowPlayingMovies(String page) throws IOException{
        return JsonReader.getMoviesFromUrl("https://api.themoviedb.org/3/movie/now_playing?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&page="+page+"&region=SE");
    }
    
    /**
     * Get movies now playing in theaters
     * @return
     * @throws IOException 
     */
    public List<Movie> getNowPlayingMovies() throws IOException{
        return getNowPlayingMovies("1");
    }
    
    /**
     * Get top rated movies by page
     * @param page
     * @return
     * @throws IOException 
     */
    public List<Movie> getTopRatedMovies(String page) throws IOException{
        return JsonReader.getMoviesFromUrl("https://api.themoviedb.org/3/movie/top_rated?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&page="+page);
    }
    
    /**
     * Get top rated movies
     * @return
     * @throws IOException 
     */
    public List<Movie> getTopRatedMovies() throws IOException{
        return getTopRatedMovies("1");
    }

    
    /**
     * Search for movie by name. Returns a list of search results.
     * @param movie
     * @return List of movies that matches the input
     * @throws IOException 
     */
    public List<Movie> searchMovie(String movie) throws IOException{
        return searchMovie(movie,"1");
    }
    
    public Movie getMovie(String movieID) throws IOException{     
        return JsonReader.getMovieFromUrl("https://api.themoviedb.org/3/movie/"+movieID+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US");
    
    }
    /**
     * Get genre by id
     * @param id
     * @return Genre that matches the id
     * @throws IOException 
     */
    public String getGenre(String id)throws IOException {
        return JsonReader.getGenreFromUrl("https://api.themoviedb.org/3/genre/movie/list?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US", id);
    }
    
    /**
     * Get movie by id
     * @param movieID
     * @return Movie that matches the id
     * @throws IOException 
     */
    
    /**
     * Search for movie by name and page
     * @param movie Searched movie
     * @param page results page number
     * @return List of movies that matches the input
     * @throws IOException 
     */
    public List<Movie> searchMovie(String movie, String page) throws IOException{
        movie = movie.replaceAll(" ", "%20");
        movie = movie.replaceAll("ö", "%C3%B6");
        movie = movie.replaceAll("ä", "%C3%A4");
        movie = movie.replaceAll("å", "%C3%A5");
        if(movie.isEmpty()){
            return getTopMovies("1");
        }
        return JsonReader.getMoviesFromUrl("https://api.themoviedb.org/3/search/movie?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&query="+movie+"&page=1&include_adult=true" + "&page=" + page);
    }
}

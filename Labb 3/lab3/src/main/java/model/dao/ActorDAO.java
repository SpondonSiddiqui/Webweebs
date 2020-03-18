package model.dao;

import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.Getter;
import model.entity.Actor;
import model.entity.Movie;
import model.utils.JsonReader;

@Stateless
public class ActorDAO extends AbstractDAO<Actor,String> {

    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public ActorDAO() {
        super(Actor.class);
    }
    
    public List<Actor> getAllActors(){
        Query query = entityManager.createQuery("SELECT m FROM Actor m ");
        return query.getResultList();
    }
    
    public List<Actor> findActorsByName(String name) {
        Query query = entityManager.createQuery("SELECT m FROM Actor m "
                + "WHERE m.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }
    /**
     * Get an actor by id.
     * @param id The id of the actor.
     * @return The actor.
     * @throws IOException 
     */
    public Actor getActor(String id)throws IOException{
        return JsonReader.getActorFromUrl("https://api.themoviedb.org/3/person/"+id+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US");
    }
    
    /**
     * Gets a list of actors starring in a movie specified by its id.
     * @param movieID  The id of the movie.
     * @return List of actor-names and their respective id's.
     * @throws IOException 
     */
    public List<Actor> getActorsFromMovie(String movieID) throws IOException{
        return JsonReader.getActorsFromMovieCreditsUrl("https://api.themoviedb.org/3/movie/"+movieID+"/credits?api_key=10dfedc564f5b41f3c803582d1d3a5fa");
    }
    
    /**
     * Get movies that actor starred in.
     * @param actorID Id of actor
     * @return List of movies 
     * @throws IOException 
     */
    public List<Movie> getMoviesFromActor(String actorID) throws IOException{
        Actor actor = getActor(actorID);
        return JsonReader.getMoviesFromActorUrl("https://api.themoviedb.org/3/search/person?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&query="+actor.getName()+"&page=1&include_adult=true",actorID);
    }
    
    /**
     * Get the director of a movie specified by its id.
     * @param movieID The id of the movie.
     * @return The director.
     * @throws IOException 
     */
    public Actor getDirectorOfMovie(String movieID) throws IOException{
        Actor actor = JsonReader.getDirectorFromUrl("https://api.themoviedb.org/3/movie/"+movieID+"/credits?api_key=10dfedc564f5b41f3c803582d1d3a5fa");
        //return getActor(actor.getId());
        return actor;
    }
    
    public List<Actor> searchActor(String name, String page) throws IOException{
        name = name.replaceAll(" ", "%20");
        name = name.replaceAll("ö", "%C3%B6");
        name = name.replaceAll("ä", "%C3%A4");
        name = name.replaceAll("å", "%C3%A5");
        if(name.isEmpty()){
            return getTopActors();
        }
        return JsonReader.getActorsFromUrl("https://api.themoviedb.org/3/search/person?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&query="+name+"&page="+page+"&include_adult=true");
    }
    
    public List<Actor> searchActor(String name) throws IOException{
        return searchActor(name,"1");
    }
    
    public List<Actor> getTopActors(String page) throws IOException{
        return JsonReader.getActorsFromUrl("https://api.themoviedb.org/3/person/popular?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&page="+page);
    }
    
    public List<Actor> getTopActors() throws IOException{
        return getTopActors("1");
    }
    

}

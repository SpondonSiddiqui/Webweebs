package model.dao;

import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Actor;
import model.utils.JsonReader;

@Stateless
public class ActorDAO extends AbstractDAO<Actor,String> {

    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public ActorDAO() {
        super(Actor.class);
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
     * @param movieId  The id of the movie.
     * @return List of actor-names and their respective id's.
     * @throws IOException 
     */
    public List<Actor> getActorsFromMovie(String movieId) throws IOException{
        return JsonReader.getActorsFromMovieCreditsUrl("https://api.themoviedb.org/3/movie/"+movieId+"/credits?api_key=10dfedc564f5b41f3c803582d1d3a5fa");
    }
    
    /**
     * Get the director of a movie specified by its id.
     * @param movieId The id of the movie.
     * @return The director.
     * @throws IOException 
     */
    public Actor getDirectorOfMovie(String movieId) throws IOException{
        Actor actor = JsonReader.getDirectorFromUrl("https://api.themoviedb.org/3/movie/"+movieId+"/credits?api_key=10dfedc564f5b41f3c803582d1d3a5fa");
        return getActor(actor.getId());
    }
}

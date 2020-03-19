/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;
import model.dao.ActorDAO;
import model.dao.MovieDAO;
import model.entity.Actor;
import model.entity.Movie;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 *
 * @author User
 */
@Data
@Named
@ViewScoped
public class ShowMovieBackingBean implements Serializable {

    @Inject
    @Param(name = "id")
    private String id;
    @Size(min = 5, max = 100, message = "Review need to be between 5 and 100 characters")
    private String review;

    @EJB
    private MovieDAO movieDAO;
    private Movie movie;

    @PostConstruct
    private void init() {
        try {
            movie = movieDAO.getMovie(id);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String releaseYear(){
        String date = movie.getRelease_date();
        char[] cDate = date.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<4; i++){
            sb.append(cDate[i]);
        }
        return sb.toString();
    }
    
    public String getDir()throws IOException{
        ActorDAO actorDAO = new ActorDAO();
        Actor actor = actorDAO.getDirectorOfMovie(id);
        return actor.getName();
    }
    
    public String getStars() throws IOException{
        ActorDAO actorDAO = new ActorDAO();
        List<Actor> actors = new ArrayList<>();
        actors = actorDAO.getActorsFromMovie(id);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++){
            if(i>0){
                sb.append(", ");
            }
            sb.append(actors.get(i).getName());
        }
        return sb.toString();
    }
    
    /*public void validateSubmission() {
        movie = movieDAO.findMoviesByName(name).get(0);
        if (!movie.getReviews().equals("")) {
            movie.setReviews(review);
            movieDAO.update(movie);
            Messages.addGlobalWarn("Added review", null);
            Faces.validationFailed();
        } else {
            Messages.addGlobalWarn("You have already written a review "
                    + "of this movie.", null);
            Faces.validationFailed();
        }
    }*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
import model.dao.MovieDAO;
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
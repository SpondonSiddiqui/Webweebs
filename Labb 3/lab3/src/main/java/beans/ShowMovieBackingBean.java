/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;
import lombok.Data;
import model.dao.MovieDAO;
import model.entity.Movie;
import org.omnifaces.cdi.Param;

/**
 *
 * @author User
 */
@Data
@Named
@ViewScoped
public class ShowMovieBackingBean implements Serializable {

    @Inject
    @Param(name = "name")
    private String name;
    @Min(value = 10, message = "Minimum is 10 characters")
    private String review;

    @EJB
    private MovieDAO movieDAO;
    private Movie movie;

    @PostConstruct
    private void init() {
        movie = movieDAO.findMoviesByName(name).get(0);
    }

    public void validateSubmission() {
        movie = movieDAO.findMoviesByName(name).get(0);
        movie.setReviews(review);
        movieDAO.update(movie);
    }
}

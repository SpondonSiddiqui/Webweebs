/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.MovieDAO;
import model.entity.Movie;


/**
 *
 * @author User
 */
@Data
@Named
@ViewScoped
public class MovieBackingBean implements Serializable {

    @EJB
    private MovieDAO movieDAO;
    private List<Movie> movies;

    @PostConstruct
    private void init() {
        movies = new ArrayList<>(movieDAO.getAllMovies());
    }
}

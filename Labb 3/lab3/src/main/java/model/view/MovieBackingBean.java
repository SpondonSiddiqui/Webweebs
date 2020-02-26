/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.MovieDAO;
import model.entity.Movie;

/**
 *
 * @author Spondon
 */
@Data
@Named
@ViewScoped
public class MovieBackingBean implements Serializable {
    
    @EJB
    MovieDAO movieDAO;

    private List<Movie> movies = movieDAO.findAll();
    
    private String test;
    
    @PostConstruct
    private void init() {
        //movies = new ArrayList<>(movieGrid.getMovies());
        test = "Hello world";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.MovieDAO;
import model.entity.Movie;

/**
 *
 * @author jespe
 */
@Data
@Named
@ViewScoped
public class SearchBackingBean implements Serializable{
    
    private List<Movie> movies;
    
    private String searchKey;
   
    @EJB
    private MovieDAO movieDAO;
    
    /*@PostConstruct
    private void init() {
        try{
            movies = movieDAO.getTopMovies("1");
        } catch (IOException ex){
            System.out.println(ex.toString());
        }
    }*/
    
    public void searchMovie(){
        try{
            this.movies = movieDAO.searchMovie(searchKey);
        } catch (IOException ex){
            System.out.println(ex.toString());
        }
    }
    
}

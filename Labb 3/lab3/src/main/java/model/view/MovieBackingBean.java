/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.view;

import java.io.Serializable;
import java.util.List;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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

    private List<Movie> movies;
    
    //private String key = "10dfedc564f5b41f3c803582d1d3a5fa";

    @Inject
    private MovieBean movieBean;
    
    private String test;
    private String searchKey;
    
    @PostConstruct
    private void init(){
       try{
       movies = movieDAO.getTopMovies();
       } catch (IOException ex){
           System.out.println(ex.toString());
       }
    }
    
    public List<Movie> getTopMovies(){
        try{
            movies = movieDAO.getTopMovies();
            return movies;
        } catch (IOException ex){
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public void searchMovie(){
        try{
            this.movies = movieDAO.searchMovie(searchKey);
            System.out.println(searchKey);
        } catch (IOException ex){
            System.out.println(ex.toString());
        }
    }
    
    public List<Movie> getUpcomingMovies(){
        try{
            movies = movieDAO.getUpcomingMovies();
            return movies;
        } catch (IOException ex){
            System.out.println(ex.toString());
            return null;
        }
    }
}

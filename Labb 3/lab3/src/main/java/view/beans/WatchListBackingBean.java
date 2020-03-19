/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.beans;

import java.io.Console;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.System.console;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.MovieDAO;
import model.dao.WatchListDAO;
import model.entity.Movie;
import model.entity.WatchList;
import model.entity.WebUser;
import view.beans.UserBean;
import org.omnifaces.cdi.Param;

/**
 *
 * @author Spondon
 */

@Data
@Named
@ViewScoped
public class WatchListBackingBean implements Serializable {
    
    @Inject
    @Param(name = "id")
    private String id;
    
    //private List<WatchList> watchLists;
    
    @EJB
    private WatchListDAO watchListDAO;
    
    @EJB
    private MovieDAO movieDAO;
    
    private Movie movie;

    @Inject
    private UserBean userBean;
    
    private WatchList userWatchList;

    @PostConstruct
    private void init() {
        /*System.out.println("HALLOJ " + id);
        try {
            movie = movieDAO.getMovie(id);    
            System.out.println("mememememem: " + movie);
        } catch (IOException e) {
            System.out.println(e);
        }*/
        final WebUser webUser = userBean.getUser();
        userWatchList = watchListDAO.find(webUser.getUsername() + "'s watchlist");
    }
    
    public boolean isInList(String id){
        try {
            movie = movieDAO.getMovie(id);
        } catch (IOException ex) {
            Logger.getLogger(WatchListBackingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return watchListDAO.movieIsInList(movie);
        
    }
    
    public void onAddMovieToWatchList(String id) {
        
        System.out.println("Add button pressed");
        
        try {
            movie = movieDAO.getMovie(id);
            System.out.println("HALLOJ" + id);
        } catch (IOException ex) {
            Logger.getLogger(WatchListBackingBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!movieDAO.checkMovieExists(movie.getTitle())) {
            descriptionTrim();
            movieDAO.create(movie);
        } 
        
        //List<Movie> movies = new ArrayList<>();
        //movies.add(movie);
        final WebUser webUser = userBean.getUser();
        final WatchList watchList = watchListDAO.getWatchListBelongingToUser(webUser).get(0);
        watchList.getMovies().add(movie);
        watchListDAO.update(watchList);
    }
    
    /**
     * Some descriptions for movies have a length over 255 characters which 
     * the "Overview" column does not support.
     */
    private void descriptionTrim(){
        String desc = movie.getOverview();
        if(desc.length() > 255) movie.setOverview(desc.substring(0, 250) + "...");
    }
}

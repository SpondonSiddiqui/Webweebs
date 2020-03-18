/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.UserDAO;
import model.dao.WatchListDAO;
import model.entity.Movie;
import model.entity.UserWatchList;
import model.entity.WebUser;

/**
 *
 * @author Spondon
 */
@Data
@Named
@ViewScoped
public class WatchListBackingBean implements Serializable{
    
    private UserWatchList watchList;
    private UserBean userBean;
    
    private UserDAO userDAO;
    private WatchListDAO watchListDAO;
    
    /*@PostConstruct
    public void init() {
        watchList = user.getMyWatchList();
    }*/
    
    public UserWatchList getWatchList() {
        return watchList;
    }
    
    public void addMovieToWatchList(Movie movie) {
        //watchList.getWatchList().add(movie.getTitle());
    }
    
    /*public String populateWatchLists() {
        return "watchlists";
    }*/
}

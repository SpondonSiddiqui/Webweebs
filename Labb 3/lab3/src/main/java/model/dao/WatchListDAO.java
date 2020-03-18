/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.Getter;
import model.entity.Movie;
import model.entity.UserWatchList;

/**
 *
 * @author Spondon
 */
@Stateless
public class WatchListDAO extends AbstractDAO<UserWatchList, String>{
    
    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public WatchListDAO() {
        super(UserWatchList.class);
    }
    
    public List<Movie> getWatchList(){
        Query query = entityManager.createQuery("SELECT m FROM Movie m");
        return query.getResultList();
    }
    
}

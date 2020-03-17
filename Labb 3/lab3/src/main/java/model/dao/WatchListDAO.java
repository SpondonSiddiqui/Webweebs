/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.WatchList;

/**
 *
 * @author Spondon
 */
@Stateless
public class WatchListDAO extends AbstractDAO<WatchList, String>{
    
    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public WatchListDAO() {
        super(WatchList.class);
    }
    
}

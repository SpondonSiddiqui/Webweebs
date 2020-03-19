/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.WatchListDAO;

/**
 *
 * @author Spondon
 */

@Data
@Named
@ViewScoped
public class WatchListBackingBean implements Serializable {
    
    @EJB
    private WatchListDAO watchListDAO;
    
    @PostConstruct
    private void init() {
        
    }
}

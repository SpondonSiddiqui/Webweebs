/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.ActorDAO;
import model.entity.Actor;
import org.omnifaces.cdi.Param;
/**
 *
 * @author User
 */
@Data
@Named
@ViewScoped
public class ShowActorBackingBean implements Serializable {

    @Inject
    @Param(name = "name")
    private String name;
    
    @EJB
    private ActorDAO actorDAO;
    private Actor actor;

  
    @PostConstruct
    private void init(){
        actor = actorDAO.findActorsByName(name).get(0);
    }
   
}

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
    @Param(name = "id")
    private String id;
    
    @EJB
    private ActorDAO actorDAO;
    private Actor actor;

    @PostConstruct
    private void init(){
        try {
            actor = actorDAO.getActor(id);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
   
}

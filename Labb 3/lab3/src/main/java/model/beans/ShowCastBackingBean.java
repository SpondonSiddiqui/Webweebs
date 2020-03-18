/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import com.github.javafaker.Faker;
import java.io.Serializable;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.ActorDAO;
import model.entity.Actor;
import org.omnifaces.cdi.Param;

/**
 *
 * @author Spondon
 */
@Data
@Named
@ViewScoped
public class ShowCastBackingBean implements Serializable {

    @Inject
    @Param(name = "id")
    private String id;
    
    @Inject
    private ShowMovieBackingBean bean;

    @EJB
    ActorDAO actorDAO;

    private List<Actor> actors;

    //private String key = "10dfedc564f5b41f3c803582d1d3a5fa";
    @PostConstruct
    private void init() {
        /*Faker faker = new Faker();
       actors = new ArrayList<>();
       for(int i=0; i<10; i++){
           String name = faker.name().fullName();
           Actor actor = new Actor(name, "", "", "", "", "");
           actors.add(actor);
       }*/
        //id="0";
        //id = bean.getId();
        /*actors = actorDAO.getAllActors();
        System.out.println(actors.get(0).getBio());
        System.out.println(id);
*/
         try{
       actors = actorDAO.getActorsFromMovie(id);
       } catch (IOException ex) {
           System.out.println(ex.toString());
       }
        System.out.println(actors.get(0).getPicture_path());
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Actor> getActorsFromMovie() {
        try {
            actors = actorDAO.getActorsFromMovie(id);
            return actors;
        } catch (IOException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}

package model.beans;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.ActorDAO;
import model.entity.Actor;

@Data
@Named
@ViewScoped
public class ActorBackingBean implements Serializable {

    @EJB
    private ActorDAO actorDAO;
    
    private List<Actor> actors;
    
    private String searchKey;
    
    @PostConstruct
    private void init() {
        try{
            actors = actorDAO.getTopActors();
        } catch (IOException ex){
            System.out.println(ex.toString());
        }   
    }
    
    public void searchMovie(){
        try{
            this.actors = actorDAO.searchActor(searchKey);
            System.out.println(searchKey);
        } catch (IOException ex){
            System.out.println(ex.toString());
        }
    }
}


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.ActorDAO;
import model.dao.MovieDAO;
import model.entity.Actor;
import model.entity.Movie;

@Data
@Named
@ViewScoped
public class ActorBackingBean implements Serializable {

    @EJB
    private ActorDAO actorDAO;
    
    private List<Actor> actors;
    
    @PostConstruct
    private void init() {
        actors = new ArrayList<>(actorDAO.findAll());
    }
}
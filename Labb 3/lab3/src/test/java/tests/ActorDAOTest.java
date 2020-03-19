package tests;

import java.util.List;
import javax.ejb.EJB;
import junit.framework.Assert;
import model.dao.ActorDAO;
import model.dao.MovieDAO;
import model.entity.Actor;
import model.entity.Movie;
import model.entity.WebUser;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ActorDAOTest {
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(MovieDAO.class, Movie.class, ActorDAO.class, Actor.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    private ActorDAO actorDAO;
    
    private Actor actor;
    
    private List<Movie> actedIn;
    
    @Before
    public void setUp() {
        actor = new Actor("Russel Crowe", "","","","","");
        actorDAO.create(actor);
    }
    
    @After
    public void tearDown() {
        actorDAO.remove(actor);
    }

    @Test
    public void getActorsByName() {
        Assert.assertTrue(actorDAO.findActorsByName("Russel Crowe").get(0).getName().equals("Russel Crowe"));
    }
}

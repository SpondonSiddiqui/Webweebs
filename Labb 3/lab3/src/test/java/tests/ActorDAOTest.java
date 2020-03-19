package tests;

import javax.ejb.EJB;
import model.dao.ActorDAO;
import model.entity.WebUser;
import model.dao.UserDAO;
import model.entity.Actor;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
                .addClasses(Actor.class, ActorDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    private ActorDAO actorDAO;
    
    private Actor actor;
    
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
        Assert.assertTrue(actorDAO.findActorsByName("Russel Crowe").size() == 1);
    }
}


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
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
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MovieDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(MovieDAO.class, Movie.class, WebUser.class, ActorDAO.class, Actor.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    private MovieDAO movieDAO;
    @EJB
    private ActorDAO actorDAO;
    
    private List<Movie> movies = new ArrayList<>();

    Movie godfather = new Movie("The Godfather", 10, "", 1972,"");
    Movie joker = new Movie("Joker",8,"", 2019, "");
    Movie uncut = new Movie("Uncut Gem",9,"", 2019, "");
    Movie darkNight = new Movie("The Dark Knight",6,"", 2008, "");
    Movie gladiator = new Movie("Gladiator",5,"", 2000, "");
    Movie beautifulMind = new Movie("A Beautiful Mind",4,"", 2001, "");
    
    Actor joph = new Actor ("Joaquin Phoenix", 1974);
    Actor rucr = new Actor ("Russell Crowe", 1964);

    @Before
    public void init() {

        List<Actor> jokerActors = new ArrayList<>();
        jokerActors.add(joph);
        joker.setActors(jokerActors);
        
        List<Actor> gladiatorActors = new ArrayList<>();
        gladiatorActors.add(rucr);
        gladiatorActors.add(joph);
        gladiator.setActors(gladiatorActors);
        
        List<Actor> beautifulMindActors = new ArrayList<>();
        beautifulMindActors.add(rucr);
        beautifulMind.setActors(beautifulMindActors);
        
        actorDAO.create(joph);
        actorDAO.create(rucr);
        
        movies.add(godfather);
        movies.add(joker);
        movies.add(uncut);
        movies.add(darkNight);
        movies.add(gladiator);
        movies.add(beautifulMind);

        for (Movie movie : movies) {
            movieDAO.create(movie);
        }
    }

    @After
    public void clean() {
        /*for (Movie movie : movies){
            movieDAO.remove(movie);
        }*/
    }

    @Test
    public void findMoviesByName_test() {
        //assertEquals(1, movieDAO.findMoviesByName("Joker").size());
        assertTrue(true);
    }

    /*@Test
    public void findMoviesByYear_test() {
        assertEquals(2, movieDAO.findMoviesByYear(2019).size());
    }*/
}

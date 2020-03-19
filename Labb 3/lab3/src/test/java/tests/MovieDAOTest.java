package tests;


import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import model.dao.ActorDAO;
import model.dao.MovieDAO;
import model.dao.ReviewDAO;
import model.dao.UserDAO;
import model.entity.Actor;
import model.entity.Movie;
import model.entity.Review;
import model.entity.WebUser;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MovieDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(MovieDAO.class, Movie.class, WebUser.class, ActorDAO.class, 
                        Actor.class, Review.class, ReviewDAO.class, UserDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    private MovieDAO movieDAO;
    @EJB
    private ActorDAO actorDAO;   
    @EJB
    private UserDAO userDAO;
    
    private List<Movie> movies = new ArrayList<>();
    private List<String> genres = new ArrayList<>();

    Movie godfather = new Movie("The Godfather", "10", "99999", "1972","", "",genres);
    Movie joker = new Movie("Joker","8","", "2019", "", "",genres);

    @Before
    public void init() {

        movieDAO.create(joker);
        movieDAO.create(godfather);
        
    }

    @After
    public void clean() {
        movieDAO.remove(joker);
        movieDAO.remove(godfather);
    }


    @Test
    public void findMoviesByName() {
        Assert.assertTrue(movieDAO.findMoviesByName("Joker").size() == 1);

    }

    @Test
    public void findMoviesById() {
       //assertEquals(2, movieDAO.findMoviesByYear(2019).size());
       Assert.assertTrue(movieDAO.findMovieById("99999") != null);
    }
    
    @Test
    public void checkMovieExists() {
        Assert.assertTrue(!movieDAO.checkMovieExists("This isnt a movie"));

        //Assert.assertTrue(!movieDAO.checkMovieExists("1917"));
    }
    
}

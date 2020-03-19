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
    private List<Actor> actors = new ArrayList<>();
    private Faker faker = new Faker();
    int n = 8;
    private Random rand = new Random();

    Movie godfather = new Movie("The Godfather", "10", "", "1972","", "",genres);
    Movie joker = new Movie("Joker","8","", "2019", "", "",genres);
    Movie uncut = new Movie("Uncut Gem","9","", "2019", "", "",genres);
    Movie darkNight = new Movie("The Dark Knight","6","", "2008", "", "",genres);
    Movie gladiator = new Movie("Gladiator","5","", "2000", "", "",genres);
    Movie beautifulMind = new Movie("A Beautiful Mind","4","", "2001", "", "",genres);
    
    Actor joph = new Actor ("Joaquin Phoenix", "1974","","","","");
    Actor rucr = new Actor ("Russell Crowe", "1964","","","","");

    @Before
    public void init() {
        
        /*for(int i=0; i<n; i++ ) {
            String title = faker.book().title();
            Integer year = rand.nextInt((2020 -1950)+1) + 1950 ;
            Integer rating = rand.nextInt((10-1)+1) + 1;
            String description = faker.gameOfThrones().quote();
            String review = " ";
            Movie movie = new Movie(title, year, rating, description, review);
            
            String name = faker.funnyName().name();
            Integer byear = rand.nextInt((2020-1900)+1) + 1900;
            String bio = faker.harryPotter().spell();
            Actor actor = new Actor(name, byear, bio);
            actorDAO.create(actor);
            actors.add(actor);
            movieDAO.create(movie);
            movie.setActors(actors);
            
            
        }*/
        
        //WebUser wb = new WebUser("marcus", "hej");
        //userDAO.create(wb);
    }

    /*@After
    public void clean() {
        for (Movie movie : movies){
            movieDAO.remove(movie);
        }
    }*/

    /*@Test
    public void findMoviesByName_test() {
        assertEquals(1, movieDAO.findMoviesByName("Joker").size());
    }

    @Test
    public void findMoviesByYear_test() {
       //assertEquals(2, movieDAO.findMoviesByYear(2019).size());
        //Assert.assertTrue(movieDAO.findMovieById("10752") == null);
    }
    
    @Test
    public void checkMovieExists() {
        //Assert.assertTrue(!movieDAO.checkMovieExists("1917"));
    }*/
    
    @Test
    public void findUserByName() {
       //assertEquals(2, movieDAO.findMoviesByYear(2019).size());
       /*WebUser wb = new WebUser("marcus", "hej");
       WebUser dbwb = userDAO.getUserByName("marcus").get(0);
        System.out.println("HALLOJ " + dbwb);
        Assert.assertTrue(wb.getUsername().equals(dbwb.getUsername()) &&
                wb.getPassword().equals(dbwb.getPassword()));*/
        
    }
}
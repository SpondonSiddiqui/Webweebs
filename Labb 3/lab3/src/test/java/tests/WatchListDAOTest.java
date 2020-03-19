package tests;

import java.util.List;
import java.util.Arrays;
import javax.ejb.EJB;
import model.dao.ActorDAO;
import model.dao.MovieDAO;
import model.dao.ReviewDAO;
import model.dao.UserDAO;
import model.dao.WatchListDAO;
import model.entity.Actor;
import model.entity.Movie;
import model.entity.Review;
import model.entity.WatchList;
import model.entity.WebUser;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class WatchListDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(MovieDAO.class, Movie.class, WebUser.class, ActorDAO.class, 
                        Actor.class, Review.class, ReviewDAO.class, UserDAO.class, WatchList.class,
                        WatchListDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    private WatchListDAO watchListDAO;
    
    @EJB
    private UserDAO userDAO;
    
    @EJB
    private MovieDAO movieDAO;
    
    private List<Movie> movies = Arrays.asList(new Movie("The Godfather", "10", "99999", "1972","", "", Arrays.asList("1", "2", "3")),
            new Movie("Joker","8","", "2019", "", "", Arrays.asList("1", "2", "3")));
    
    private WebUser wb = new WebUser("spondon1", "hej");
    
    private List<String> genres = Arrays.asList("1","2","3","4");
    
    private WatchList watchList = new WatchList("test_user_3's watchlist", movies, wb);
    
    @Before
    public void setUp() {
        userDAO.create(wb);
        movieDAO.create(new Movie("The Godfather", "10", "99999", "1972","", "", Arrays.asList("1", "2", "3")));
        movieDAO.create(new Movie("Joker","8","", "2019", "", "", Arrays.asList("1", "2", "3")));
        watchListDAO.create(watchList);
    }
    
    @After
    public void tearDown() {
        watchListDAO.remove(watchList);
        userDAO.remove(wb);
        movieDAO.remove(movieDAO.find("Joker"));
        movieDAO.remove(movieDAO.find("The Godfather"));
    }

    @Test
    public void getWatchListForUser() {
        assertEquals(watchList.getMovies().get(0).getTitle(), 
                watchListDAO.getWatchListBelongingToUser(wb).get(0).getMovies().get(0).getTitle());
        assertEquals(watchList.getMovies().get(1).getTitle(), 
                watchListDAO.getWatchListBelongingToUser(wb).get(0).getMovies().get(1).getTitle());
    }
}

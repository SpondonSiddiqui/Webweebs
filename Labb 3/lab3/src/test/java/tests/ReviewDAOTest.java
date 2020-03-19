package tests;

import com.sun.imageio.plugins.wbmp.WBMPMetadata;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import junit.framework.Assert;
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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ReviewDAOTest {
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(Movie.class, Review.class, ReviewDAO.class, WebUser.class,
                        Actor.class, UserDAO.class, MovieDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    private ReviewDAO reviewDAO;
    
    @EJB
    private UserDAO userDAO;
    
    @EJB
    private MovieDAO movieDAO;
    
    private WebUser webUser = new WebUser("test_user_1", "hej");
    private WebUser webUser2 = new WebUser("test_user_2", "hej");
    
    private List<String> genres = Arrays.asList("1", "2", "3");
   
    
    Movie godfather = new Movie("The Godfather", "10", "99999", "1972","", "",genres);
    
    Movie joker = new Movie("Joker", "10", "99999", "1972","", "",genres);
    
    private Review review = new Review("Very good movie I liked it", webUser, "19-03-2020 19:35", "7", godfather);
    private Review review2 = new Review("Hated this movie", webUser2, "19-03-2020 19:35", "7", joker);
    
    @Before
    public void setUp() { 
        userDAO.create(webUser);
        userDAO.create(webUser2);
        movieDAO.create(joker);
        movieDAO.create(godfather);
        reviewDAO.create(review);
        reviewDAO.create(review2);
    }
    
    @After
    public void tearDown() {
        reviewDAO.remove(review);
        reviewDAO.remove(review2);
        userDAO.remove(webUser);
        userDAO.remove(webUser2);
        movieDAO.remove(joker);
        movieDAO.remove(godfather);
    }

    @Test
    public void testGetReviewsByMovieName() {
        assertEquals(review.getContent(), reviewDAO.findReviewsByName(godfather).get(0).getContent());
    }
    
    @Test
    public void testGetReviewsByUser() {
        assertEquals(review2,reviewDAO.findReviewsByUser(webUser2).get(0));
    }
}

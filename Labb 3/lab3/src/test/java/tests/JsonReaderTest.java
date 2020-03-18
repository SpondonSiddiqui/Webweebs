package tests;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import model.dao.ActorDAO;
import model.dao.MovieDAO;
import model.entity.Actor;
import model.entity.Movie;
import model.utils.JsonReader;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class JsonReaderTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(Movie.class, Actor.class, JsonReader.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    private final String UncutGemsID = "473033";
    private Movie UncutGems;
    private List<Actor> UncutGemsActors;
    private Actor AdamSandler;
    private final String SandlerID = "19292";
    private Actor LakeithStanfield;
    private final String StanfieldID = "1200864";
    
    @Before
    public void setUp() throws IOException{
        UncutGems = JsonReader.getMovieFromUrl("https://api.themoviedb.org/3/movie/"+UncutGemsID+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US");
        UncutGemsActors = JsonReader.getActorsFromMovieCreditsUrl("https://api.themoviedb.org/3/movie/"+UncutGemsID+"/credits?api_key=10dfedc564f5b41f3c803582d1d3a5fa");
        AdamSandler = JsonReader.getActorFromUrl("https://api.themoviedb.org/3/person/"+id+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US");
        LakeithStanfield = JsonReader.getActorFromUrl("https://api.themoviedb.org/3/person/"+id+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getMovie_test(){
        assertEquals("Uncut Gems", UncutGems.getTitle());
        assertEquals(UncutGemsID, UncutGems.getId());
    }
    
    @Test
    public void getActorsFromMovie_test(){
       // assertTrue(UncutGemsActors.contains(""))
    }
    
    @Test
    public void getDirector_test(){
        
    }
    
    @Test
    public void getActor_test(){
        
    }
    


}

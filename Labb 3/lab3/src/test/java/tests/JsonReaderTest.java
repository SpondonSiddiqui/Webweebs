/*package tests;

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
    private Actor UncutGemsDirector;
    private Actor AdamSandler;
    private final String SandlerID = "19292";
    private List<Movie> SandlerMovies;
    private Actor LakeithStanfield;
    private final String StanfieldID = "1200864";
    private Actor JoshSafdie;
    private final String SafdieID = "129561";
    String ComedyGenre;
    String DramaGenre;
    String ThrillerGenre;
    String CrimeGenre;
    
    @Before
    public void setUp() throws IOException{
        UncutGems = JsonReader.getMovieFromUrl("https://api.themoviedb.org/3/movie/"+UncutGemsID+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US");
        UncutGemsActors = JsonReader.getActorsFromMovieCreditsUrl("https://api.themoviedb.org/3/movie/"+UncutGemsID+"/credits?api_key=10dfedc564f5b41f3c803582d1d3a5fa");
        UncutGemsDirector = JsonReader.getDirectorFromUrl("https://api.themoviedb.org/3/movie/"+UncutGemsID+"/credits?api_key=10dfedc564f5b41f3c803582d1d3a5fa");
        AdamSandler = JsonReader.getActorFromUrl("https://api.themoviedb.org/3/person/"+SandlerID+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US");
        LakeithStanfield = JsonReader.getActorFromUrl("https://api.themoviedb.org/3/person/"+StanfieldID+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US");
        JoshSafdie = JsonReader.getActorFromUrl("https://api.themoviedb.org/3/person/"+SafdieID+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US");
        SandlerMovies = JsonReader.getMoviesFromActorUrl("https://api.themoviedb.org/3/search/person?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US&query=Adam%20Sandler&page=1&include_adult=true", SandlerID);
        CrimeGenre = "Crime";
        ThrillerGenre = "Thriller";
        DramaGenre = "Drama";
        ComedyGenre = "Comedy";
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getMovie_test(){
        //assertEquals("Uncut Gems", UncutGems.getTitle());
        //assertEquals(UncutGemsID, UncutGems.getId());
    }*/
    
    /*@Test
    public void getActorsFromMovie_test(){
        assertTrue(UncutGemsActors.contains(AdamSandler));
        assertTrue(UncutGemsActors.contains(LakeithStanfield));
    }
    
    @Test
    public void getDirector_test(){
        assertEquals(UncutGemsDirector, JoshSafdie);
    }
    
    @Test
    public void getActor_test(){
        assertEquals(AdamSandler.getName(), "Adam Sandler");
        assertEquals(AdamSandler.getBirthday(), "1966-09-09");
    }
    
    @Test
    public void getMoviesFromActor_test(){
        assertTrue(SandlerMovies.contains(UncutGems));
    }
    
    public void getGenres_test() throws IOException{   
        assertEquals(CrimeGenre,JsonReader.getGenreFromUrl("https://api.themoviedb.org/3/genre/movie/list?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US", "80"));
        assertEquals(ThrillerGenre ,JsonReader.getGenreFromUrl("https://api.themoviedb.org/3/genre/movie/list?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US", "53"));
        assertEquals(DramaGenre ,JsonReader.getGenreFromUrl("https://api.themoviedb.org/3/genre/movie/list?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US", "18"));
        assertEquals(ComedyGenre ,JsonReader.getGenreFromUrl("https://api.themoviedb.org/3/genre/movie/list?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US", "35"));
        
    }*/
    


//}

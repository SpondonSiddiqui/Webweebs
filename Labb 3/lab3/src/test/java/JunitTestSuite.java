import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ActorDAOTest;
import tests.JsonReaderTest;
import tests.MovieDAOTest;
import tests.ReviewDAOTest;
import tests.WebUserDAOTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    ActorDAOTest.class,
    MovieDAOTest.class,
    WebUserDAOTest.class,
    JsonReaderTest.class,
    ReviewDAOTest.class
})
public class JunitTestSuite {   
    
}
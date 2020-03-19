package tests;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import model.entity.WebUser;
import model.dao.UserDAO;
import model.entity.Movie;
import model.entity.WatchList;
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
public class WebUserDAOTest {
    
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(WebUser.class, UserDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private UserDAO userDao;
    
    private WebUser wb = new WebUser("marcus1", "hej");
    
    @Before
    public void setUp() {

        userDao.create(wb);

    }
    
    @After
    public void tearDown() {
        userDao.remove(wb);
    }

    @Test
    public void getUserByName() {
        WebUser dbwb = new WebUser("marcus1", "hej");
        Assert.assertTrue(wb.getUsername().equals(dbwb.getUsername()) &&
                wb.getPassword().equals(dbwb.getPassword()));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import model.dao.CarDAO;
import model.entity.Car;
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

/**
 *
 * @author Spondon
 */
@RunWith(Arquillian.class)
public class CarDAOTest {
    @Deployment 
    public static WebArchive createDeployment() { 
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(CarDAO.class, Car.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml"); 
    }
    
    @EJB private CarDAO carDAO;
    private List<Car> cars = new ArrayList<>();
    
    @Before public void init() {
        cars.add(new Car("IFF780", "Renault Clio"));
        cars.add(new Car("LTP520", "Volvo 760GT"));
        cars.add(new Car("XOL345", "Isuzu Traga"));
        for(Car c : cars) {
            carDAO.create(c);
        }
    }
    
    @Test
    public void checkThatFindCarsMatchingNameMatchesCorrectly() { 
        
        List<Car> carTestList = new ArrayList<>();
        List<Car> carDAOList = carDAO.findCarsMatchingName("Isuzu Traga");
        
        carTestList.add(new Car("XOL345", "Isuzu Traga"));

        Assert.assertTrue(carDAOList.equals(carTestList));
    }
    
    @After public void clean() { 
        for(Car c : cars) {
            carDAO.remove(c);
        }
    }
    
}

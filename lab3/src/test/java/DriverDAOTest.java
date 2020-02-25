/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import javax.ejb.EJB;
import model.dao.CarDAO;
import model.dao.DriverDAO;
import model.dao.ParkingLotDAO;
import model.entity.Car;
import model.entity.Driver;
import model.entity.ParkingLot;
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
 * @author makka
 */
@RunWith(Arquillian.class)
public class DriverDAOTest {
    
    @Deployment 
    public static WebArchive createDeployment() { 
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(CarDAO.class, Car.class, ParkingLot.class, ParkingLotDAO.class, Driver.class, DriverDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml"); 
    }
    
    @EJB private DriverDAO driverDAO;
    @EJB private CarDAO carDAO;
    @EJB private ParkingLotDAO parkingLotDAO;
    
    Car car;
    Driver driver;
    ParkingLot parkingLot;
    
    @Before
    public void setUp() {    
        parkingLot = new ParkingLot(2, 100, null);
        parkingLotDAO.create(parkingLot);
        car = new Car("AAA009", "Volvo 240", parkingLot);
        carDAO.create(car);
        driver = new Driver("Jesper", 45, car);
        driverDAO.create(driver);
        
    }
    
    @After
    public void tearDown() {
        driverDAO.remove(driver);
        carDAO.remove(car);
        parkingLotDAO.remove(parkingLot);
    }

    @Test
    public void getCarByName() {
        Assert.assertEquals(1, driverDAO.getNumberOfCarsByName("Jesper"));
    }
}

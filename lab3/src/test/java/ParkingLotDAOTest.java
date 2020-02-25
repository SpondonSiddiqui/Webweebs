/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import junit.framework.Assert;
import model.dao.CarDAO;
import model.dao.ParkingLotDAO;
import model.entity.Car;
import model.entity.ParkingLot;
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

/**
 *
 * @author makka
 */
@RunWith(Arquillian.class)
public class ParkingLotDAOTest {
    @Deployment 
    public static WebArchive createDeployment() { 
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(CarDAO.class, Car.class, ParkingLot.class, ParkingLotDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml"); 
    }
    
    @EJB private ParkingLotDAO parkingLotDAO;
    
    @EJB private CarDAO carDAO;
    
    private ParkingLot parkingLot;
    
    List<Car> cars = new ArrayList<>();
    
    @Before
    public void setUp() {
        
        parkingLot = new ParkingLot(1, 100, null);
        parkingLotDAO.create(parkingLot);
        
        for(int i = 0; i < 5; i++) {
            Car tmpcar = new Car("AAA00" + i, "Volvo 740", parkingLot);
            cars.add(tmpcar);
            carDAO.create(tmpcar);
        } 
        //parkingLot.setCars(cars);
        
        
        
        
        
    }  
    
    @After
    public void tearDown() {
        
        for(Car c : cars) {
            carDAO.remove(c);
        }
        parkingLotDAO.remove(parkingLot);
    }

    @Test
    public void checkAvailableSpaces() {
        Assert.assertEquals(95, parkingLotDAO.available_spaces(parkingLot));
    }
}
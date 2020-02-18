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
    
    private ParkingLot parkingLot;
    
    @Before
    public void setUp() {
        List<Car> cars = new ArrayList<>();
        
        for(int i = 0; i < 5; i++) {
            cars.add(new Car("AAA00" + i, "Volvo 740", null));
        }       
        parkingLot = new ParkingLot(1, 100, cars);
        
        for(int i = 0; i < 5; i++){
            parkingLot.getCars().get(i).setParkingLot(parkingLot);
        }
        
        parkingLotDAO.create(parkingLot);
    }  
    
    @After
    public void tearDown() {
        
    }

    @Test
    public void checkAvailableSpaces() {
        Assert.assertEquals(95, parkingLotDAO.available_spaces(parkingLot));
    }
}

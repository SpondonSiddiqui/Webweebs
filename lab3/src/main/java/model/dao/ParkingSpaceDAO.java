package model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.ParkingLot;

/**
 *
 * @author makka
 */
@Stateless
public class ParkingSpaceDAO extends AbstractDAO<ParkingSpaceDAO> {
    @Getter
    @PersistenceContext(unitName = "webweebs")
    private EntityManager entityManager;
    
    public ParkingSpaceDAO() {
        super(ParkingSpaceDAO.class);
    }

}

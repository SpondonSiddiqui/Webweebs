/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import easycriteria.JPAQuery;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.JoinType;
import lombok.Getter;
import lombok.Setter;
import model.entity.Car;
import model.entity.ParkingLot;
import model.entity.QCar;
import model.entity.QParkingLot_;
import model.entity.QCar_;

/**
 *
 * @author makka
 */
@Stateless
public class ParkingLotDAO extends AbstractDAO<ParkingLot> {
    @Getter
    @Setter
    @PersistenceContext(unitName = "webweebs")
    private EntityManager entityManager;
    
    public ParkingLotDAO() {
        super(ParkingLot.class);
    }
    
    public int available_spaces(ParkingLot pl) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCar car = QCar.car;
        
        List<Car> list = queryFactory.selectFrom(car)
                .where(car.parkingLot.eq(pl))
                .fetch();
        
        return pl.getCapacity() - list.size();
    }
}
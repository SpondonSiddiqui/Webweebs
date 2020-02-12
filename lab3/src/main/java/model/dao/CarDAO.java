/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import easycriteria.JPAQuery;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Car;
import model.entity.QCar_;

/**
 *
 * @author Spondon
 */
@Stateless
public class CarDAO extends AbstractDAO<Car> {

    @Getter
    @PersistenceContext(unitName = "webweebs")
    private EntityManager entityManager;

    public CarDAO() {
        super(Car.class);
    }

    public List<Car> findCarsMatchingName(String name) {
        QCar_ carx = new QCar_();

        JPAQuery query = new JPAQuery(entityManager);
        
        return query.select(Car.class).where(carx.name.like(name)).getResultList();
    }
}

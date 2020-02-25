/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import easycriteria.JPAQuery;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Driver;
import model.entity.QDriver;
import model.entity.QDriver_;

/**
 *
 * @author makka
 */
@Stateless
public class DriverDAO extends AbstractDAO<Driver> {
    @Getter
    @PersistenceContext(unitName = "webweebs")
    private EntityManager entityManager;
    
    public DriverDAO(){
        super(Driver.class);
    }
    
    public int getNumberOfCarsByName(String name) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        
        QDriver driver = QDriver.driver;
        
        List<Driver> list = queryFactory.selectFrom(driver)
                .where(driver.name.eq(name))
                .fetch();
        
        return list.size();
    }
}

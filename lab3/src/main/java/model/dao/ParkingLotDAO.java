/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.ParkingLot;

/**
 *
 * @author jespe
 */
@Stateless
public class ParkingLotDAO extends AbstractDAO<ParkingLot> {
    @Getter
    @PersistenceContext(unitName = "webweebs")
    private EntityManager entityManager;

    public ParkingLotDAO() {
        super(ParkingLot.class);
    }
}
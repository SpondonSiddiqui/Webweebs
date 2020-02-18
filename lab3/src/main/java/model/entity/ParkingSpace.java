/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author makka
 */
@Entity
public class ParkingSpace implements Serializable {
    @Id private int space_id;
    //@JoinColumn(space_id = "parkingLot_id")
    @ManyToOne private ParkingLot parkingLot;
    @Getter @Setter @OneToOne private Car car;
    
    public ParkingSpace() {
        car = null;
    }
    
    public ParkingSpace(Car car) {
        this.car = car;
    }
}
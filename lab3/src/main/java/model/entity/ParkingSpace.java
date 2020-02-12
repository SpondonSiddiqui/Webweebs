/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author jespe
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpace {
    @Id private int number;
    @JoinColumn (name = "parkingLot_id")
    @ManyToOne private ParkingLot parkingLot;
    @OneToOne public Car car;
}

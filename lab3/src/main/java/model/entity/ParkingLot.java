/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class ParkingLot {
    @Id private String address;
    @OneToMany (mappedBy = "parkingLot") public List<ParkingSpace> parkingSpaces; 
}

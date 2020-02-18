package model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Spondon
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {
    @Id private String license;
    private String name;
    @ManyToOne private ParkingLot parkingLot;
}
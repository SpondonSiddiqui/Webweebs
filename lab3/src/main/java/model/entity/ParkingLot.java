package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author makka
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParkingLot implements Serializable {
    @Id private int parkingLot_id;
    private int capacity;
    @OneToMany(mappedBy = "parkingLot") private List<Car> cars;
}

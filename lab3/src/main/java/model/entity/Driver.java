package model.entity;

import java.io.Serializable;
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
 * @author Spondon
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Driver implements Serializable {
    @Id private String name;
    private int age;
    @OneToOne private Car car;
}

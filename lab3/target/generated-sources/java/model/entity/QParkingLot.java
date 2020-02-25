package model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParkingLot is a Querydsl query type for ParkingLot
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QParkingLot extends EntityPathBase<ParkingLot> {

    private static final long serialVersionUID = 2132581375L;

    public static final QParkingLot parkingLot = new QParkingLot("parkingLot");

    public final NumberPath<Integer> capacity = createNumber("capacity", Integer.class);

    public final ListPath<Car, QCar> cars = this.<Car, QCar>createList("cars", Car.class, QCar.class, PathInits.DIRECT2);

    public final NumberPath<Integer> parkingLot_id = createNumber("parkingLot_id", Integer.class);

    public QParkingLot(String variable) {
        super(ParkingLot.class, forVariable(variable));
    }

    public QParkingLot(Path<? extends ParkingLot> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParkingLot(PathMetadata metadata) {
        super(ParkingLot.class, metadata);
    }

}


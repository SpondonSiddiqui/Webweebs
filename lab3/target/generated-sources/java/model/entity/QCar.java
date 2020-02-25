package model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCar is a Querydsl query type for Car
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCar extends EntityPathBase<Car> {

    private static final long serialVersionUID = -47846642L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCar car = new QCar("car");

    public final StringPath license = createString("license");

    public final StringPath name = createString("name");

    public final QParkingLot parkingLot;

    public QCar(String variable) {
        this(Car.class, forVariable(variable), INITS);
    }

    public QCar(Path<? extends Car> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCar(PathMetadata metadata, PathInits inits) {
        this(Car.class, metadata, inits);
    }

    public QCar(Class<? extends Car> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parkingLot = inits.isInitialized("parkingLot") ? new QParkingLot(forProperty("parkingLot")) : null;
    }

}


package model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDriver is a Querydsl query type for Driver
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDriver extends EntityPathBase<Driver> {

    private static final long serialVersionUID = 574007982L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDriver driver = new QDriver("driver");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final QCar car;

    public final StringPath name = createString("name");

    public QDriver(String variable) {
        this(Driver.class, forVariable(variable), INITS);
    }

    public QDriver(Path<? extends Driver> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDriver(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDriver(PathMetadata metadata, PathInits inits) {
        this(Driver.class, metadata, inits);
    }

    public QDriver(Class<? extends Driver> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.car = inits.isInitialized("car") ? new QCar(forProperty("car"), inits.get("car")) : null;
    }

}


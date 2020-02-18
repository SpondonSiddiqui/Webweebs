package model.entity;

import easycriteria.meta.*;

public class QCar_ extends ObjectAttribute<Car>{
    public QCar_() {
        this(null, null);
    }
    public StringPropertyAttribute<Car, java.lang.String> license = new StringPropertyAttribute<>("license", this, java.lang.String.class);
    public StringPropertyAttribute<Car, java.lang.String> name = new StringPropertyAttribute<>("name", this, java.lang.String.class);
    public model.entity.QParkingLot_ parkingLot;
    private static InstancesInits inits = InstancesInits.DIRECT2;
    public QCar_(String attribute, EntityPathNode parent) {
        this(attribute, parent, inits);
    }
    public QCar_(String attribute, EntityPathNode parent, InstancesInits inits) {
        super(attribute, parent, Car.class);
        this.parkingLot = inits.isInitialized("parkingLot") ? new model.entity.QParkingLot_("parkingLot", this, inits.get("parkingLot")) : null;
    }
}

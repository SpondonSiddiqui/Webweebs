package model.entity;

import easycriteria.meta.*;

public class QParkingLot_ extends ObjectAttribute<ParkingLot>{
    public QParkingLot_() {
        this(null, null);
    }
    public NumberPropertyAttribute<ParkingLot, Integer> parkingLot_id = new NumberPropertyAttribute<>("parkingLot_id", this, Integer.class);
    public NumberPropertyAttribute<ParkingLot, Integer> capacity = new NumberPropertyAttribute<>("capacity", this, Integer.class);
    public CollectionAttribute<ParkingLot, model.entity.Car> cars = new CollectionAttribute<>("cars", this, model.entity.Car.class);
    private static InstancesInits inits = InstancesInits.DIRECT2;
    public QParkingLot_(String attribute, EntityPathNode parent) {
        this(attribute, parent, inits);
    }
    public QParkingLot_(String attribute, EntityPathNode parent, InstancesInits inits) {
        super(attribute, parent, ParkingLot.class);
    }
}

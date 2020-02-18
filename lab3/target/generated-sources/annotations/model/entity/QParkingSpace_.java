package model.entity;

import easycriteria.meta.*;

public class QParkingSpace_ extends ObjectAttribute<ParkingSpace>{
    public QParkingSpace_() {
        this(null, null);
    }
    public NumberPropertyAttribute<ParkingSpace, Integer> space_id = new NumberPropertyAttribute<>("space_id", this, Integer.class);
    public model.entity.QParkingLot_ parkingLot;
    public model.entity.QCar_ car;
    private static InstancesInits inits = InstancesInits.DIRECT2;
    public QParkingSpace_(String attribute, EntityPathNode parent) {
        this(attribute, parent, inits);
    }
    public QParkingSpace_(String attribute, EntityPathNode parent, InstancesInits inits) {
        super(attribute, parent, ParkingSpace.class);
        this.parkingLot = inits.isInitialized("parkingLot") ? new model.entity.QParkingLot_("parkingLot", this, inits.get("parkingLot")) : null;
        this.car = inits.isInitialized("car") ? new model.entity.QCar_("car", this, inits.get("car")) : null;
    }
}

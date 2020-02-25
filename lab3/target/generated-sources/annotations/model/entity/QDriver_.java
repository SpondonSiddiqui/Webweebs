package model.entity;

import easycriteria.meta.*;

public class QDriver_ extends ObjectAttribute<Driver>{
    public QDriver_() {
        this(null, null);
    }
    public StringPropertyAttribute<Driver, java.lang.String> name = new StringPropertyAttribute<>("name", this, java.lang.String.class);
    public NumberPropertyAttribute<Driver, Integer> age = new NumberPropertyAttribute<>("age", this, Integer.class);
    public model.entity.QCar_ car;
    private static InstancesInits inits = InstancesInits.DIRECT2;
    public QDriver_(String attribute, EntityPathNode parent) {
        this(attribute, parent, inits);
    }
    public QDriver_(String attribute, EntityPathNode parent, InstancesInits inits) {
        super(attribute, parent, Driver.class);
        this.car = inits.isInitialized("car") ? new model.entity.QCar_("car", this, inits.get("car")) : null;
    }
}

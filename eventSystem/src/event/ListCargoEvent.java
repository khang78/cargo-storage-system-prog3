package event;

import geschaeftslogik.CargoObjekt;

import java.util.EventObject;
import java.util.List;

public class ListCargoEvent extends EventObject {
    private String cargoType;
    private List<CargoObjekt> cargoList;

    public ListCargoEvent(Object source,String cargoType, List<CargoObjekt> cargoList) {
        super(source);
        this.cargoType = cargoType;
        this.cargoList = cargoList;
    }
    public String getCargoType() {
        return cargoType;
    }
    public List<CargoObjekt> getCargoList() {
        return cargoList;
    }
}

package event;

import geschaeftslogik.CargoObjekt;

import java.util.EventObject;
import java.util.List;

public class ListCustomerEvent extends EventObject {
    private List<CargoObjekt> customerList;


    public ListCustomerEvent (Object source,List<CargoObjekt> cargoList) {
        super(source);
        this.customerList =cargoList;
    }
    public List<CargoObjekt> getCustomerList() {
        return customerList;
    }
}

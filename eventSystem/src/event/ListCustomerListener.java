package event;

import geschaeftslogik.CargoObjekt;
import geschaeftslogik.Manager;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerListener implements EventListener<ListCustomerEvent> {
    Manager manager;
    public ListCustomerListener(Manager manager){
        this.manager = manager;
    }

    @Override
    public void onEvent(ListCustomerEvent event) {
        manager.listCustomers();
    }
}

package event;

import cargo.*;
import geschaeftslogik.CargoObjekt;
import geschaeftslogik.Manager;

import java.util.List;

public class ListCargoListener implements EventListener<ListCargoEvent> {
    Manager manager;
    public ListCargoListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void onEvent(ListCargoEvent event) {
        List<CargoObjekt> cargoList = event.getCargoList();
        System.out.println("Cargo List: " + cargoList);
        String eventCargoType = event.getCargoType();
        manager.listCargos(eventCargoType);
    }
}

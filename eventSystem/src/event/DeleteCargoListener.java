package event;

import geschaeftslogik.CargoObjekt;
import geschaeftslogik.Manager;

import java.util.List;

public class DeleteCargoListener implements EventListener<DeleteCargoEvent> {
    Manager manager;
    public DeleteCargoListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void onEvent(DeleteCargoEvent event) {
        int storageLocation = event.getLocation();
        boolean removeCargo = manager.removeCargo(storageLocation);
        if(!removeCargo) {
            System.out.println("Cargo does not exist");
        }else {
            System.out.println("Cargo deleted");
        }
    }
}

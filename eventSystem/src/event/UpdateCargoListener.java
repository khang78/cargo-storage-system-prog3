package event;


import geschaeftslogik.CargoObjekt;
import geschaeftslogik.Manager;

import java.time.LocalDate;
import java.util.List;

public class UpdateCargoListener implements EventListener<UpdateCargoEvent> {
    Manager manager;
    public UpdateCargoListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void onEvent(UpdateCargoEvent event) {
        int storageLocation = event.getStorageLocation();
        int newstorageLocation = event.getNewStorageLocation();

        boolean updated = manager.updateCargo(storageLocation, newstorageLocation);
        if (updated) {
            System.out.println("Cargo updated");
        }else{
            System.out.println("Cargo not found");
        }
    }
}

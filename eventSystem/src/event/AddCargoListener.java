package event;

import geschaeftslogik.CargoObjekt;
import geschaeftslogik.Manager;

import java.util.EventObject;

public class AddCargoListener implements EventListener<AddCargoEvent>{
    private Manager manager;
    public AddCargoListener(Manager manager){
        this.manager = manager;
    }

    @Override
    public void onEvent(AddCargoEvent event) {
        CargoObjekt cargo = new CargoObjekt(
                event.getCargoType(),
                event.getValue(),
                event.getHazards(),
                event.getCustomer(),
                event.getDurationOfStorage(),
                this.manager.getCargos().toArray().length
        );
        cargo.setFragile(event.isFragile());
        cargo.setPressurized(event.isPressurized());
        cargo.setgrainSize(event.getGrainSize());
        boolean added = this.manager.addCargo(cargo);
        if (added) {
            System.out.println("New Cargo " + cargo.getCargoType() + " added at " + cargo.getStorageLocation());
        }
    }
}

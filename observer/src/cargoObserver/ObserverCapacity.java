package cargoObserver;

import geschaeftslogik.Manager;

public class ObserverCapacity implements Observer {
private Manager manager;
public ObserverCapacity(Manager manager) {
    this.manager = manager;

}

    @Override
    public void update() {
    double capacity = ((double) manager.getCargoCounter() / manager.getMaxCapacity())*100;
    if (capacity >= 90) {
        System.out.println("Warning: Capacity is at " + capacity + "%");
    }
    }

    @Override
    public void update(String message) {

    }
}

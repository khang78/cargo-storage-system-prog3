package simulation;

import geschaeftslogik.CargoObjekt;
import geschaeftslogik.Manager;
import geschaeftslogik.StorableImpl;

import java.util.List;
import java.util.Random;

public class DeleteSimulation extends Thread{
    private final Manager manager;

    public DeleteSimulation(Manager manager) {
        this.manager = manager;
    }

    public void run() {
        while(true) {
            synchronized (manager) {
                Integer location = getRandomLocation(manager);
                if (location != null) {
                    manager.removeCargo(location);
                    System.out.println("deleted cargo at index: " + location);
                }
            }
        }
    }

    public Integer getRandomLocation(Manager manager) {
        Random r = new Random();

        List<CargoObjekt> list = manager.getCargos();

        if (list.isEmpty() || list == null) {
            return null;
        }

        return r.nextInt(list.size());
    }

}

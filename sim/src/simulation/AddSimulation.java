package simulation;

import administration.Customer;
import cargo.DryBulkCargo;
import cargo.Hazard;
import geschaeftslogik.CargoObjekt;
import geschaeftslogik.CustomerImpl;
import geschaeftslogik.Manager;
import geschaeftslogik.StorableImpl;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class AddSimulation extends Thread {
    final Manager manager;

    public AddSimulation(Manager manager) {
        this.manager = manager;
    }

    public void run() {
        while (true) {
            synchronized (manager) {
                addCargoSimulation(manager);
                System.out.println("created cargo");
            }
        }
    }
    public void addCargoSimulation(Manager manager) {
        Random r = new Random();

        int random = r.nextInt(7);
        int hazardNumber = r.nextInt(4);
        Hazard hazard = Hazard.values()[hazardNumber];
        int storageLocation = r.nextInt(100);
        BigDecimal value = BigDecimal.valueOf(r.nextDouble());


        switch (random) {
            case 1:
                manager.addCargo(new CargoObjekt("DryBulkAndUnitisedCargo", value, Collections.singleton(hazard), new CustomerImpl("Khang"),
                        Duration.ZERO, storageLocation));
            case 2:
                manager.addCargo(new CargoObjekt("DryBulkCargo", value, Collections.singleton(hazard), new CustomerImpl("Khang"),
                        Duration.ZERO, storageLocation));
            case 3:
                manager.addCargo(new CargoObjekt("LiquidAndDryBulkCargo", value, Collections.singleton(hazard), new CustomerImpl("Khang"),
                        Duration.ZERO, storageLocation));
            case 4:
                manager.addCargo(new CargoObjekt("LiquidBulkAndUnitisedCargo", value, Collections.singleton(hazard), new CustomerImpl("Khang"),
                        Duration.ZERO, storageLocation));
            case 5:
                manager.addCargo(new CargoObjekt("LiquidBulkCargo", value, Collections.singleton(hazard), new CustomerImpl("Khang"),
                        Duration.ZERO, storageLocation));
            case 6:
                manager.addCargo(new CargoObjekt("UnitisedCargo", value, Collections.singleton(hazard), new CustomerImpl("Khang"),
                        Duration.ZERO, storageLocation));
        }
    }
}

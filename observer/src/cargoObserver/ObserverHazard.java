package cargoObserver;

import cargo.Hazard;
import geschaeftslogik.Manager;

import java.util.ArrayList;
import java.util.List;

public class ObserverHazard implements Observer {
    private Manager manager;
    private List<Hazard> currentHazard;
    public ObserverHazard(Manager manager) {
        this.manager = manager;
        this.currentHazard = new ArrayList<>(manager.getHazard());

    }

    @Override
    public void update() {
        List<Hazard> newHazard = new ArrayList<>(manager.getHazard());
        if(!newHazard.equals(currentHazard)) {
            currentHazard = new ArrayList<>(newHazard);
            System.out.println("Hazards have been added. Current Hazards: "+ currentHazard);
        }
    }

    @Override
    public void update(String message) {
        List<Hazard> newHazard = new ArrayList<>(manager.getHazard());
        if(!newHazard.equals(currentHazard)) {
            currentHazard = new ArrayList<>(newHazard);
        }
    }
}

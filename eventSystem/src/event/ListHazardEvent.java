package event;

import geschaeftslogik.CargoObjekt;

import java.util.EventObject;
import java.util.List;

public class ListHazardEvent extends EventObject {
    private boolean hazard;
    public ListHazardEvent(Object source, boolean hazard) {
        super(source);
        this.hazard = hazard;
    }
    public boolean isHazard() {
        return hazard;
    }
}

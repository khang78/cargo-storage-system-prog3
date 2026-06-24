package event;

import java.util.EventObject;

public class DeleteCargoEvent extends EventObject {
    private int storageLocation;
    public DeleteCargoEvent(Object source, int storageLocation) {
        super(source);
        this.storageLocation = storageLocation;
    }
    public int getLocation() {
        return storageLocation;
    }

}

package event;

import java.util.EventObject;

public class UpdateCargoEvent extends EventObject {
    private int storageLocation;
    private int newStorageLocation;
    public UpdateCargoEvent(Object source, int storageLocation, int newStorageLocation) {
        super(source);
        this.storageLocation = storageLocation;
        this.newStorageLocation = newStorageLocation;
    }

    public int getStorageLocation() {
        return storageLocation;
    }
    public int getNewStorageLocation() {
        return newStorageLocation;
    }
}

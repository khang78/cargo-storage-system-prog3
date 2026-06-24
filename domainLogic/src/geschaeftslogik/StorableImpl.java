package geschaeftslogik;

import administration.Customer;
import administration.Storable;

import java.time.Duration;
import java.util.Date;

public class StorableImpl implements Storable {
    private int storageLocation;


    @Override
    public Customer getOwner() {
        return null;
    }

    @Override
    public Duration getDurationOfStorage() {
        return null;
    }

    @Override
    public Date getLastInspectionDate() {
        return null;
    }

    @Override
    public int getStorageLocation() {
        return storageLocation;
    }
    public void setStorageLocation(int storageLocation) {
        this.storageLocation = storageLocation;
    }

}

package geschaeftslogik;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorableImplTest {
    private StorableImpl storable;

    @Test
    public void getCustomerTest() {
        storable = new StorableImpl();
        assertNull(storable.getOwner());
    }

    @Test
    public void getDurationOfStorableTest() {
        storable = new StorableImpl();
        assertNull(storable.getDurationOfStorage());
    }
    @Test
    public void getLastInsertedDateTest() {
        storable = new StorableImpl();
        assertNull(storable.getLastInspectionDate());
    }

    @Test
    public void getStorageLocationTest() {
        storable = new StorableImpl();
        assertEquals(0, storable.getStorageLocation());
    }
    @Test
    public void setStorageLocationTest() {
        storable = new StorableImpl();
        storable.setStorageLocation(10);
        assertEquals(10, storable.getStorageLocation());
    }

}

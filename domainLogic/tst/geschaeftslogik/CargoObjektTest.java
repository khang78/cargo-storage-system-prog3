package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CargoObjektTest {
    private CargoObjekt cargo;

    @Test
    public void cargoObjektTestGetOwner() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("100");
        Duration duration = Duration.ofMinutes(10);
        String cargoType = "DryBulkCargo";
        int storageLocation = 2;
        cargo = new CargoObjekt(cargoType,value,hazards,customer,duration,storageLocation);
        assertEquals(customer,cargo.getOwner());
    }

    @Test
    public void cargoObjektTestGetStorageLocation() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("100");
        Duration duration = Duration.ofMinutes(10);
        String cargoType = "DryBulkCargo";
        int storageLocation = 2;

        cargo = new CargoObjekt(cargoType,value,hazards,customer,duration,storageLocation);
        assertEquals(storageLocation,cargo.getStorageLocation());
    }
    @Test
    public void cargoObjektTestLastInspectionDateNotNull() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("100");
        Duration duration = Duration.ofMinutes(10);
        String cargoType = "DryBulkCargo";
        int storageLocation = 2;

        cargo = new CargoObjekt(cargoType,value,hazards,customer,duration,storageLocation);
        assertNotNull(cargo.getLastInspectionDate());
    }
    @Test
    public void cargoObjektTestGetValue(){
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("100");
        Duration duration = Duration.ofMinutes(10);
        String cargoType = "DryBulkCargo";
        int storageLocation = 2;

        cargo = new CargoObjekt(cargoType,value,hazards,customer,duration,storageLocation);
        assertEquals(value,cargo.getValue());
    }
    @Test
    public void cargoObjektTestGetHazards(){
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("100");
        Duration duration = Duration.ofMinutes(10);
        String cargoType = "DryBulkCargo";
        int storageLocation = 2;

        cargo = new CargoObjekt(cargoType,value,hazards,customer,duration,storageLocation);
        assertEquals(hazards,cargo.getHazards());
    }
    @Test
    public void cargoObjektTestGetCargoType(){
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("100");
        Duration duration = Duration.ofMinutes(10);
        String cargoType = "DryBulkCargo";
        int storageLocation = 2;

        cargo = new CargoObjekt(cargoType,value,hazards,customer,duration,storageLocation);
        assertEquals(cargoType,cargo.getCargoType());
    }
    @Test
    public void setStoragelocationTest(){
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("100");
        Duration duration = Duration.ofMinutes(10);
        String cargoType = "DryBulkCargo";
        int storageLocation = 2;

        cargo = new CargoObjekt(cargoType,value,hazards,customer,duration,storageLocation);
        cargo.setStorageLocation(20);
        assertEquals(20,cargo.getStorageLocation());
    }
    @Test
    public void updateInspectionDateTest() throws InterruptedException {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("100");
        Duration duration = Duration.ofMinutes(10);
        String cargoType = "DryBulkCargo";
        int storageLocation = 2;
        cargo = new CargoObjekt(cargoType,value,hazards,customer,duration,storageLocation);
        Date inspectionDate = cargo.getLastInspectionDate();
        Thread.sleep(20);
        cargo.setInspectionDate();
        Date newInspectionDate = cargo.getLastInspectionDate();
        assertNotEquals(inspectionDate,newInspectionDate);
    }

}

package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class LiquidBulkAndUnitisedCargoImplTest {
    private LiquidBulkAndUnitisedCragoImpl cargo;

    @Test
    public void liquidBulkAndUnitisedCargoIsFragileAndIsPressurized() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidBulkAndUnitisedCargo";
        int storageLocation = 1;
        boolean fragile = true;
        boolean pressurized = true;

        cargo = new LiquidBulkAndUnitisedCragoImpl(cargoType,value,hazards,customer,duration,storageLocation,pressurized,fragile);
        assertTrue(cargo.isFragile());
        assertTrue(cargo.isPressurized());
    }

    @Test
    public void liquidBulkAnsUnitisedCargoIsNotFragileAndIsNotPressurized(){
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidBulkAndUnitisedCargo";
        int storageLocation = 1;
        boolean fragile = false;
        boolean pressurized = false;

        cargo = new LiquidBulkAndUnitisedCragoImpl(cargoType,value,hazards,customer,duration,storageLocation,pressurized,fragile);
        assertFalse(cargo.isFragile());
        assertFalse(cargo.isPressurized());

    }
}

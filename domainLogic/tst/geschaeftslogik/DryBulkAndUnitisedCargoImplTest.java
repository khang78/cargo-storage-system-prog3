package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class DryBulkAndUnitisedCargoImplTest {
    private DryBulkAndUnitisedCargoImpl cargo;

    @Test
    public void dryBulkAndUnitisedCargoTestIsNotFragile() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidAndDryBulkCargo";
        int storageLocation = 1;
        boolean fragile = false;
        int grainSize = 1;
        cargo = new DryBulkAndUnitisedCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,grainSize,fragile);
        assertFalse(cargo.isFragile());
        assertEquals(1,cargo.getGrainSize());
    }

    @Test
    public void dryBulkAndUnitisedCargoTestIsFragile() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidAndDryBulkCargo";
        int storageLocation = 1;
        boolean fragile = true;
        int grainSize = 1;
        cargo = new DryBulkAndUnitisedCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,grainSize,fragile);
        assertTrue(cargo.isFragile());
        assertEquals(1,cargo.getGrainSize());

    }
}

package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;


public class LiquidAndDryBulkCargoImplTest {
    private LiquidAndDryBulkCargoImpl cargo;

    @Test
    public void liquidAndDryBulkCargoTestIsNotPressurized() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidAndDryBulkCargo";
        int storageLocation = 1;
        boolean pressurized = false;
        int grainSize = 1;
        cargo = new LiquidAndDryBulkCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,grainSize,pressurized);
        assertFalse(cargo.isPressurized());
        assertEquals(1,cargo.getGrainSize());
    }

    @Test
    public void liquidAndDryBulkCargoTestIsPressurized() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidAndDryBulkCargo";
        int storageLocation = 1;
        boolean pressurized = true;
        int grainSize = 1;
        cargo = new LiquidAndDryBulkCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,grainSize,pressurized);
        assertTrue(cargo.isPressurized());
        assertEquals(1,cargo.getGrainSize());
    }

}

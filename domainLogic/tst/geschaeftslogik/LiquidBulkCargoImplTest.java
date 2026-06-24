package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class LiquidBulkCargoImplTest {
    private LiquidBulkCargoImpl cargo;

    @Test
    public void liquidBulkCargoTestIsNotPressurized() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidBulkCargo";
        int storageLocation = 1;
        boolean pressurized = false;

        cargo = new LiquidBulkCargoImpl("LiquidBulkCargo",value,hazards,customer,duration,storageLocation,pressurized);
        assertFalse(cargo.isPressurized());
    }

    @Test
    public void liquidBulkCargoTestIsPressurized() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidBulkCargo";
        int storageLocation = 1;
        boolean pressurized = true;

        cargo = new LiquidBulkCargoImpl("LiquidBulkCargo",value,hazards,customer,duration,storageLocation,pressurized);
        assertTrue(cargo.isPressurized());

    }
}

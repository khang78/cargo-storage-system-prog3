package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DryBulkCargoImplTest {
    private DryBulkCargoImpl cargo;

    @Test
    public void dryBulkCargotest() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidAndDryBulkCargo";
        int storageLocation = 1;
        int grainSize = 1;
        cargo = new DryBulkCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,grainSize);
        assertEquals(1,cargo.getGrainSize());
    }
}

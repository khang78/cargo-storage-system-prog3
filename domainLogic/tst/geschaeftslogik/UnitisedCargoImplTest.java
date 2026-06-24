package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class UnitisedCargoImplTest {
    private UnitisedCargoImpl unitisedCargo;

    @Test
    public void unitisedCargoTestIsFragile() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "UnitisedCargo";
        int storageLocation = 1;
        boolean fragile = true;

        unitisedCargo = new UnitisedCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,fragile);
        assertTrue(unitisedCargo.isFragile());
    }
    @Test
    public void unitisedCargoTestIsNotFragile() {
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "UnitisedCargo";
        int storageLocation = 1;
        boolean fragile = false;

        unitisedCargo = new UnitisedCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,fragile);
        assertFalse(unitisedCargo.isFragile());

    }

}


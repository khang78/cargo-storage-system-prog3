package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import cargo.LiquidBulkCargo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class LiquidBulkCargoImpl extends CargoObjekt implements LiquidBulkCargo {
    private boolean pressurized;

    public LiquidBulkCargoImpl(String cargoType,BigDecimal value, Collection<Hazard> hazards, Customer customer, Duration getDurationOfStorage, int StorageLocation,boolean pressurized) {
        super(cargoType,value, hazards, customer, getDurationOfStorage, StorageLocation);
        this.pressurized = pressurized;
    }
    @Override
    public boolean isPressurized() {
        return pressurized;
    }
}

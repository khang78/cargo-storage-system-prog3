package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import cargo.LiquidBulkAndUnitisedCargo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class LiquidBulkAndUnitisedCragoImpl extends CargoObjekt implements LiquidBulkAndUnitisedCargo {
    private boolean pressurized;
    private boolean fragile;

    public LiquidBulkAndUnitisedCragoImpl(String cargoType,BigDecimal value, Collection<Hazard> hazards, Customer customer, Duration getDurationOfStorage, int StorageLocation,boolean pressurized, boolean fragile) {
        super(cargoType,value, hazards, customer, getDurationOfStorage, StorageLocation);
        this.pressurized = pressurized;
        this.fragile = fragile;
    }


    @Override
    public boolean isPressurized() {
        return pressurized;
    }

    @Override
    public boolean isFragile() {
        return fragile;
    }
}

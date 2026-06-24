package geschaeftslogik;

import administration.Customer;
import cargo.DryBulkAndUnitisedCargo;
import cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class DryBulkAndUnitisedCargoImpl extends CargoObjekt implements DryBulkAndUnitisedCargo {
    private int grainSize;
    private boolean fragile;

    public DryBulkAndUnitisedCargoImpl(String cargoType,BigDecimal value, Collection<Hazard> hazards, Customer customer, Duration getDurationOfStorage, int StorageLocation, int grainSize, boolean fragile) {
        super(cargoType,value, hazards, customer, getDurationOfStorage, StorageLocation);
        this.grainSize = grainSize;
        this.fragile = fragile;
    }
    @Override
    public int getGrainSize() {
        return grainSize;
    }

    @Override
    public boolean isFragile() {
        return fragile;
    }
}

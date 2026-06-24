package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import cargo.UnitisedCargo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class UnitisedCargoImpl extends CargoObjekt implements UnitisedCargo {
    private boolean fragile;

    public UnitisedCargoImpl(String cargoType,BigDecimal value, Collection<Hazard> hazards, Customer customer, Duration getDurationOfStorage, int StorageLocation, boolean fragile) {
        super(cargoType,value, hazards, customer, getDurationOfStorage, StorageLocation);
        this.fragile = fragile;
    }

    @Override
    public boolean isFragile() {
        return fragile;
    }
}

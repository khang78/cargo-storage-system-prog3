package geschaeftslogik;

import administration.Customer;
import cargo.DryBulkCargo;
import cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class DryBulkCargoImpl extends CargoObjekt implements DryBulkCargo {
    private int grainSize;

    public DryBulkCargoImpl(String cargoType,BigDecimal value,Collection<Hazard> hazards,Customer customer, Duration getDurationOfStorage, int StorageLocation, int grainSize) {
        super(cargoType,value,hazards,customer,getDurationOfStorage,StorageLocation);
        this.grainSize = grainSize;
    }

    @Override
    public int getGrainSize() {
        return grainSize;
    }
}

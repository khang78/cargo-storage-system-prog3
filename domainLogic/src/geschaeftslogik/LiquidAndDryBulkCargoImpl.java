package geschaeftslogik;

import administration.Customer;
import cargo.Hazard;
import cargo.LiquidAndDryBulkCargo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public class LiquidAndDryBulkCargoImpl extends CargoObjekt implements LiquidAndDryBulkCargo {
    private int grainSize;
    private boolean pressurized;

    public LiquidAndDryBulkCargoImpl(String cargoType,BigDecimal value, Collection<Hazard> hazards, Customer customer, Duration getDurationOfStorage, int StorageLocation,int grainSize,boolean pressurized) {
        super(cargoType,value,hazards, customer, getDurationOfStorage, StorageLocation);

        this.grainSize = grainSize;
        this.pressurized = pressurized;
    }

    @Override
    public int getGrainSize() {
        return grainSize;
    }

    @Override
    public boolean isPressurized() {
        return pressurized;
    }
}

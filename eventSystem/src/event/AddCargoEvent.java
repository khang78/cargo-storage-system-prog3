package event;

import administration.Customer;
import cargo.Hazard;
import geschaeftslogik.CustomerImpl;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.EventObject;

public class AddCargoEvent extends EventObject {
    private int storageLocation;
    private CustomerImpl customer;
    private BigDecimal value;
    private Duration duration;
    private Collection<Hazard> hazards;
    private String cargoType;
    private boolean fragile;
    private boolean pressurized;
    private int grainSize;

    public AddCargoEvent (Object source,String cargoType, CustomerImpl customer, BigDecimal value, Collection<Hazard> hazards,boolean fragile, boolean pressurized, int grainSize){
        super(source);
        this.cargoType = cargoType;
        this.customer = customer;
        this.value = value;
        this.hazards = hazards;
        this.fragile = fragile;
        this.pressurized = pressurized;
        this.grainSize = grainSize;
    }
    public int getStorageLocation() {
        return storageLocation;
    }
    public CustomerImpl getCustomer() {
        return customer;
    }
    public BigDecimal getValue() {
        return value;
    }

    public Duration getDurationOfStorage() {
        return this.duration;
    }
    public Collection<Hazard> getHazards() {
        return hazards;
    }
    public String getCargoType() {
        return cargoType;
    }
    public boolean isFragile() {
        return fragile;
    }
    public boolean isPressurized() {
        return pressurized;
    }
    public int getGrainSize() {
        return grainSize;
    }
}

package geschaeftslogik;

import administration.Customer;
import administration.Storable;
import cargo.Cargo;
import cargo.Hazard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CargoObjekt implements Cargo, Serializable, Storable {
    private static final long serialVersionUID = 1L;
    private String cargoType;
    private BigDecimal value;
    private Collection<Hazard> hazards;
    private Customer customer;
    private Duration durationOfStorage;
    private Date startStorageDate;
    private Date lastInspectionDate;
    private int storageLocation;
    private int newStoragelocation;
    private boolean fragile = false;
    private boolean pressurized = false;
    private int grainSize = 0;

    public CargoObjekt(String cargoType, BigDecimal value, Collection<Hazard> hazards, Customer customer, Duration durationOfStorage, int storageLocation) {
        this.cargoType = cargoType;
        this.value = value;
        this.hazards = hazards;
        this.customer = customer;
        this.durationOfStorage = durationOfStorage;
        this.storageLocation = storageLocation;
        this.lastInspectionDate = new Date();
        this.startStorageDate = new Date();
    }

    public CargoObjekt() {
        this.value = BigDecimal.ZERO;
        this.hazards = new ArrayList<>();
        this.durationOfStorage = Duration.ZERO;
        this.lastInspectionDate = new Date();
        this.startStorageDate = new Date();
    }


    @Override
    public Customer getOwner() {
        return customer;
    }

    public void setOwner(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Duration getDurationOfStorage() {
        return Duration.between(startStorageDate.toInstant(), new Date().toInstant());
    }

    public void setDurationOfStorage(Duration durationOfStorage) {
        this.durationOfStorage = durationOfStorage;
    }

    @Override
    public Date getLastInspectionDate() {
        return lastInspectionDate;
    }

    public void setLastInspectionDate(Date lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
    }

    @Override
    public int getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(int storageLocation) {
        this.storageLocation = storageLocation;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public Collection<Hazard> getHazards() {
        return hazards;
    }

    public void setHazards(Collection<Hazard> hazards) {
        this.hazards = hazards;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public int getgrainSize(){
        return grainSize;
    }

    public void setgrainSize(int grainSize){
        this.grainSize = grainSize;
    }
    public boolean isFragile(){
        return fragile;
    }

    public void setFragile(boolean fragile){
        this.fragile = fragile;
    }
    public boolean isPressurized(){
        return pressurized;
    }

    public void setPressurized(boolean pressurized){
        this.pressurized = pressurized;
    }

    public void setInspectionDate(){
        this.lastInspectionDate = new Date();
    }

}

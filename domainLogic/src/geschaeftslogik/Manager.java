package geschaeftslogik;

import administration.Customer;
import cargo.*;
import cargoObserver.Observer;
import persistence.Persistence;

import java.io.Serializable;
import java.util.*;


public class Manager implements Serializable {
    private List<CargoObjekt> cargos;
    private List<CustomerImpl> customers;
    private List<Observer> observers;
    private int maxCapacity;

    public Manager() {
        this.maxCapacity = 100;
        this.cargos = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public Manager(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.cargos = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.observers = new ArrayList<>();
    }


    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }


    public void createCustomer (CustomerImpl customer) {
        if (!customerExists(customer)) {
            customers.add(customer);
        }
    }
    public List<CustomerImpl> getCustomers() {
        return customers;
    }

    public boolean customerExists (CustomerImpl customer) {
        for (CustomerImpl c : customers) {
            if (c.getName().equals(customer.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteCustomer (String name) {
        for (int i = cargos.size() - 1; i >= 0; i--) {
            if (cargos.get(i).getOwner().getName().equals(name)) {
                cargos.remove(i);
            }
        }

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(name)) {
                customers.remove(i);
                return true;
            }
        }
        return false;

    }

    public synchronized boolean addCargo (CargoObjekt cargo) {
        if (cargos.size() >= maxCapacity) {
            System.out.println("Maximum cargo limit reached");
            return false;
        }

        for (CargoObjekt cargoObjekt : cargos){
            if (cargoObjekt.getStorageLocation() == cargo.getStorageLocation()){
                return false;
            }
        }

        CargoObjekt newCargo = null;
        switch (cargo.getCargoType()){
            case "DryBulkAndUnitisedCargo":
                newCargo = new DryBulkAndUnitisedCargoImpl(cargo.getCargoType(),cargo.getValue(),cargo.getHazards(),cargo.getOwner(),cargo.getDurationOfStorage(),cargo.getStorageLocation(),cargo.getgrainSize(),cargo.isFragile());
                break;
            case "DryBulkCargo":
                newCargo = new DryBulkCargoImpl(cargo.getCargoType(),cargo.getValue(),cargo.getHazards(),cargo.getOwner(),cargo.getDurationOfStorage(),cargo.getStorageLocation(),cargo.getgrainSize());
                break;
            case "LiquidAndDryBulkCargo":
                newCargo = new LiquidAndDryBulkCargoImpl(cargo.getCargoType(),cargo.getValue(),cargo.getHazards(),cargo.getOwner(),cargo.getDurationOfStorage(),cargo.getStorageLocation(),cargo.getgrainSize(),cargo.isPressurized());
                break;
            case"LiquidBulkAndUnitisedCargo":
                newCargo = new LiquidBulkAndUnitisedCragoImpl(cargo.getCargoType(),cargo.getValue(),cargo.getHazards(),cargo.getOwner(),cargo.getDurationOfStorage(),cargo.getStorageLocation(),cargo.isPressurized(),cargo.isFragile());
                break;
            case "LiquidBulkCargo":
                newCargo = new LiquidBulkCargoImpl(cargo.getCargoType(),cargo.getValue(),cargo.getHazards(),cargo.getOwner(),cargo.getDurationOfStorage(),cargo.getStorageLocation(),cargo.isPressurized());
                break;
            case "UnitisedCargo":
                newCargo = new UnitisedCargoImpl(cargo.getCargoType(),cargo.getValue(),cargo.getHazards(),cargo.getOwner(),cargo.getDurationOfStorage(),cargo.getStorageLocation(),cargo.isFragile());
                break;
        }
        cargos.add(newCargo);
        notifyObservers();
        return true;
    }

    public synchronized boolean removeCargo(int storageLocation) {
        Iterator<CargoObjekt> iterator = cargos.iterator();
        while (iterator.hasNext()) {
            CargoObjekt cargoObjekt = iterator.next();
            if (cargoObjekt.getStorageLocation() == storageLocation) {
                iterator.remove();
                notifyObservers();
                return true;
            }
        }
        return false;
    }
    public List<CargoObjekt> getCargos() {
        return new ArrayList<>(cargos);
    }

    public List<String> CargoCounter(){
        List<String> counter = new ArrayList<>();

        for (Customer customer : customers){
            int counterIndex = 0;
            for (CargoObjekt cargoObjekt : cargos){
                if (cargoObjekt.getOwner().equals(customer)){
                    counterIndex++;
                }
            }
            counter.add(customer.getName() + " : " + counterIndex);
        }
        return counter;
    }

    public List<CargoObjekt> getCargosType(String type) {
        List<CargoObjekt> cargoType = new ArrayList<>();
        for (CargoObjekt cargo : cargos){
            switch (type.toLowerCase()){
                case "dry":
                    if (cargo instanceof DryBulkCargo){
                        cargoType.add(cargo);
                    }
                   break;
                case "liquid":
                    if(cargo instanceof LiquidBulkCargo){
                        cargoType.add(cargo);
                    }
                    break;
                case "unitised":
                    if (cargo instanceof UnitisedCargo){
                        cargoType.add(cargo);
                    }
                    break;
            }
        }
        return cargoType;
    }
    public boolean updateCargo (int storageLocation,int newStorageLocation) {
        for (CargoObjekt cargo : cargos){
            if (Integer.valueOf(cargo.getStorageLocation()).equals(storageLocation)){
                cargo.setInspectionDate();
                cargo.setStorageLocation(newStorageLocation);
                return true;
            }
        }
        return false;

    }
    public List<Hazard> getHazard(){
        List<Hazard> hazards = new ArrayList<>();
        for (CargoObjekt cargo : cargos){
            for (Hazard hazard : cargo.getHazards()){
                if (!hazards.contains(hazard)){
                    hazards.add(hazard);
                }
            }
        }
        return hazards;
    }

    public List<String> listWithHazards(){
        List<String> hazards = new ArrayList<>();
        for(CargoObjekt objekt : cargos){
                if(!objekt.getHazards().isEmpty()){
                    hazards.add(objekt.getHazards().toString());
                }
            }
        System.out.println(hazards);
        return hazards;
    }
    public List<String> listWithoutHazards(){
        List<Hazard> noHazards = new ArrayList<>(List.of(Hazard.values()));
        for(CargoObjekt objekt : cargos){
            noHazards.removeAll(objekt.getHazards());
        }
        return Collections.singletonList(noHazards.toString());
    }

    public int getMaxCapacity() {
        return maxCapacity;

    }
    public int getCargoCounter(){
        return cargos.size();
    }

    public void saveJOS(){
        Persistence persistence = new Persistence();
        persistence.saveJOS(this);
    }

    public void loadJOS(){
        Persistence persistence = new Persistence();
        Manager newManager = persistence.loadJOS();
        this.cargos = newManager.getCargos();
        this.customers= newManager.getCustomers();
        this.maxCapacity = newManager.getMaxCapacity();
    }

    public void saveJBP(){
        Persistence persistence = new Persistence();
        persistence.saveJBP(this);
    }

    public void loadJBP(){
        Persistence persistence = new Persistence();
        Manager newManager = persistence.loadJBP();
        this.cargos = newManager.getCargos();
        this.customers= newManager.getCustomers();
        this.maxCapacity = newManager.getMaxCapacity();
    }


    public void listCargos(String eventCargoType) {
        for (CargoObjekt cargo : cargos) {
            String cargoType = cargo.getCargoType();
            if (cargoType.equals(eventCargoType)) {
                if (cargoType.equals("LiquidBulk")){
                    System.out.println("LiquidBulkCargo -> storageloaction: " + cargo.getStorageLocation() + ", inspectiondate: " + cargo.getLastInspectionDate() + ",storage duration in seconds: " + cargo.getDurationOfStorage());
                }
                if (cargoType.equals("DryBulkCargo")){
                    System.out.println("DryBulkCargo -> storageloaction: " + cargo.getStorageLocation() + ", inspectiondate: " + cargo.getLastInspectionDate() + ",storage duration in seconds: " + cargo.getDurationOfStorage());
                }
                if (cargoType.equals("UnitisedCargo")){
                    System.out.println("UnitisedCargo -> storageloaction: " + cargo.getStorageLocation() + ", inspectiondate: " + cargo.getLastInspectionDate() + ",storage duration in seconds: " + cargo.getDurationOfStorage());
                }
                if (cargoType.equals("LiquidAndDryBulkCargo")){
                    System.out.println("LiquidAndDryBulkCargo -> storageloaction: " + cargo.getStorageLocation() + ", inspectiondate: " + cargo.getLastInspectionDate() + ",storage duration in seconds: " + cargo.getDurationOfStorage());
                }
                if (cargoType.equals("LiquidAndUnitisedCargo")){
                    System.out.println("LiquidAndUnitisedCargo -> storageloaction: " + cargo.getStorageLocation() + ", inspectiondate: " + cargo.getLastInspectionDate() + ",storage duration in seconds: " + cargo.getDurationOfStorage());
                }
                if (cargoType.equals("DryBulkAndUnitisedCargo")){
                    System.out.println("DryBulkAndUnitisedCargo -> storageloaction: " + cargo.getStorageLocation() + ", inspectiondate: " + cargo.getLastInspectionDate() + ",storage duration in seconds: " + cargo.getDurationOfStorage());
                }
            }
        }
    }

    public void listCustomer(String name) {
        int counter = 0;

        for(CargoObjekt cargo : cargos){
            if(cargo.getOwner().getName().equalsIgnoreCase(name)){
                counter++;
            }
        }
        if(counter == 0){
            System.out.println("Customer does not exist");
        }else{
            System.out.println("Customer " + name + " has " + counter + " cargos");
        }
    }

    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        for (Customer customer : customers) {
            int count = 0;
            for (CargoObjekt cargo : cargos) {
                if (cargo.getOwner().getName().equalsIgnoreCase(customer.getName())) {
                    count++;
                }
            }
            System.out.println("Customer " + customer.getName() + " has " + count + " cargos");
        }
    }

    // needed for JBP
    public void setCargos(List<CargoObjekt> cargos) {
        this.cargos = cargos;
    }

    // needed for JBP
    public void setCustomers(List<CustomerImpl> customers) {
        this.customers = customers;
    }

    // needed for JBP
    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    // needed for JBP
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }
}



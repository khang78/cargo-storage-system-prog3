package cli;

import cargo.Hazard;
import event.*;
import geschaeftslogik.CustomerImpl;
import geschaeftslogik.Manager;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class CLI {
    private Scanner scanner;
    private Mode mode;
    private Manager manager;

    private EventHandler<AddCargoEvent> addCargoEventHandler;
    private EventHandler<AddCustomerEvent> addCustomerEventHandler;
    private EventHandler<DeleteCargoEvent> deleteCargoEventHandler;
    private EventHandler<DeleteCustomerEvent> deleteCustomerEventHandler;
    private EventHandler<UpdateCargoEvent> updateCargoEventHandler;
    private EventHandler<LoadJOSEvent> loadJOSEventHandler;
    private EventHandler<SaveJOSEvent> saveJOSEventHandler;
    private EventHandler<ListCustomerEvent> listCustomerEventHandler;
    private EventHandler<ListCargoEvent> listCargoEventEventHandler;
    private EventHandler<ListHazardEvent> listHazardEventHandler;
    private EventHandler<LoadJBPEvent> loadJBPEventHandler;
    private EventHandler<SaveJBPEvent> saveJBPEventHandler;

    public void setAddCargoEventHandler(EventHandler<AddCargoEvent> addCargoEventHandler) {
        this.addCargoEventHandler = addCargoEventHandler;
    }
    public void setAddCustomerEventHandler(EventHandler<AddCustomerEvent> addCustomerEventHandler) {
        this.addCustomerEventHandler = addCustomerEventHandler;
    }
    public void setDeleteCargoEventHandler(EventHandler<DeleteCargoEvent> deleteCargoEventHandler) {
        this.deleteCargoEventHandler = deleteCargoEventHandler;
    }
    public void setDeleteCustomerEventHandler(EventHandler<DeleteCustomerEvent> deleteCustomerEventHandler) {
        this.deleteCustomerEventHandler = deleteCustomerEventHandler;
    }
    public void setUpdateCargoEventHandler(EventHandler<UpdateCargoEvent> updateCargoEventHandler) {
        this.updateCargoEventHandler = updateCargoEventHandler;
    }
    public void setLoadJOSEventHandler(EventHandler<LoadJOSEvent> loadJOSEventHandler) {
        this.loadJOSEventHandler = loadJOSEventHandler;
    }
    public void setSaveJOSEventHandler(EventHandler<SaveJOSEvent> saveJOSEventHandler) {
        this.saveJOSEventHandler = saveJOSEventHandler;
    }
    public void setListCustomerEventHandler(EventHandler<ListCustomerEvent> listCustomerEventHandler) {
        this.listCustomerEventHandler = listCustomerEventHandler;
    }
    public void setListCargoEventHandler(EventHandler<ListCargoEvent> listCargoEventHandler) {
        this.listCargoEventEventHandler = listCargoEventHandler;
    }
    public void setListHazardEventHandler(EventHandler<ListHazardEvent> listHazardEventHandler) {
        this.listHazardEventHandler = listHazardEventHandler;
    }
    public void setLoadJBPEventHandler(EventHandler<LoadJBPEvent> loadJBPEventHandler) {
        this.loadJBPEventHandler = loadJBPEventHandler;
    }
    public void setSaveJBPEventHandler(EventHandler<SaveJBPEvent> saveJBPEventHandler) {
        this.saveJBPEventHandler = saveJBPEventHandler;
    }

    public CLI(Manager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
        this.mode = Mode.Default;
    }
    public void start(){
        System.out.println("Commands:");
        System.out.println(":c -> Add");
        System.out.println(":d -> Delete");
        System.out.println(":u -> Update");
        System.out.println(":r -> List");
        System.out.println(":p -> Persistenz");
        System.out.println(":q -> Exit");
        while(true){
            String input = scanner.nextLine();
            if(input.equals(":q")){
                break;
            }

            if (input.startsWith(":")) {
                switch (input) {
                    case ":c":
                        mode = Mode.Add;
                        continue;
                    case ":d":
                        mode = Mode.Delete;
                        System.out.println("Delete cargo or customer?");
                        continue;
                    case ":u":
                        mode = Mode.Update;
                        System.out.println("Enter Location");
                        continue;
                    case ":r":
                        mode = Mode.List;
                        System.out.println("Show List of customers,cargos [cargoType] or hazards [i/e]");
                        continue;
                    case ":p":
                        mode = Mode.Persistence;
                        System.out.println("Do you want to save [JOS|JBP] or load [JOS|JBP]?");
                        continue;
                    default:
                        System.out.println("Invalid input mode");
                }
            } else {
                String[] parts = input.split(" ");
                switch (mode) {
                    case Mode.Add:
                        if (parts.length == 1) {
                            String customerName = parts[0];
                            addCustomer(new CustomerImpl(customerName));
                        } else {
                            createCargo(input);
                        }
                        break;
                    case Mode.Delete:
                        switch (input.toLowerCase()) {
                            case "cargo":
                                System.out.println("Delete at what location?");
                                String loc = scanner.nextLine();
                                deleteCargo(loc);
                                break;
                            case "customer":
                                System.out.println("Which customer?");
                                String customerName = scanner.nextLine();
                                deleteCustomer(customerName);
                                break;
                            default:
                                System.out.println("Invalid input. Choose one of the following: cargos, customers, hazards.");
                        }
                        break;
                    case Mode.Update:
                        updateCargo(input);
                        break;
                    case Mode.List:
                        switch (parts[0].toLowerCase()){
                            case "cargos":
                                String cargoType = parts[1];
                                listCargo(cargoType);
                                break;
                            case "hazards":
                                switch (parts[1].toLowerCase()){
                                    case "i":
                                        listHazard(true);
                                        break;
                                    case "e":
                                        listHazard(false);
                                        break;
                                    default:
                                        System.out.println("Invalid input. Choose one of the following: i or e.");
                                        break;
                                }
                                break;
                            case "customers":
                                listCustomers();
                                break;
                            default:
                                System.out.println("Invalid input. Choose one of the following: cargos [cargoType], customers, hazards [i/e].");
                        }
                        break;
                    case Mode.Persistence:
                        persistCargo(input);
                        break;
                    default:
                        System.out.println("Invalid input mode");
                }
            }
        }
        scanner.close();
    }

    private void createCargo(String input) {
        String[] parts = input.split(" ");
        String cargoType = parts[0];
        CustomerImpl newCustomer = new CustomerImpl(parts[1]);
        BigDecimal value = new BigDecimal(parts[2].replace(",", "."));
        Collection<Hazard> hazards = new ArrayList<>();
        if (!parts[3].equals(",")) {
            String[] hazardParts = parts[3].split(",");
            for (String hazard : hazardParts) {
                hazards.add(Hazard.valueOf(hazard.toLowerCase()));
            }
        }
        boolean fragile = false;
        boolean pressure = false;
        int grainSize = 0;
        if (parts.length >= 5) {
            fragile = parts[4].equalsIgnoreCase("true");
        }
        if (parts.length >= 6) {
            pressure = parts[5].equalsIgnoreCase("true");
        }
        if (parts.length >= 7) {
            grainSize = Integer.parseInt(parts[6]);
        }
        if (!manager.customerExists(newCustomer)) {
            addCustomer(newCustomer);
        }

        addCargo(cargoType, newCustomer, value, hazards, fragile, pressure, grainSize);
    }

    private void addCargo(String cargoType, CustomerImpl customer, BigDecimal value, Collection<Hazard> hazards, boolean fragile, boolean pressurized, int grainSize){
        if (addCargoEventHandler != null) {
            AddCargoEvent addCargoEvent = new AddCargoEvent(this, cargoType, customer,value,hazards,fragile,pressurized,grainSize);
            addCargoEventHandler.handle(addCargoEvent);
        }
    }
    private void addCustomer(CustomerImpl customer) {
        if (addCustomerEventHandler != null) {
            AddCustomerEvent addCustomerEvent = new AddCustomerEvent(this,customer);
            addCustomerEventHandler.handle(addCustomerEvent);
        }
    }
    private void deleteCustomer(String input){
        if (deleteCustomerEventHandler != null) {
            DeleteCustomerEvent deleteCustomerEvent = new DeleteCustomerEvent(this,input);
            deleteCustomerEventHandler.handle(deleteCustomerEvent);
        }
    }

    private void deleteCargo(String input){
        if (deleteCargoEventHandler != null) {
            DeleteCargoEvent deleteCargoEvent = new DeleteCargoEvent(this, Integer.parseInt(input));
            deleteCargoEventHandler.handle(deleteCargoEvent);
        }
    }

    private void updateCargo(String input){
        System.out.println("Enter new Location");
        int newStorageLocation = Integer.parseInt(scanner.nextLine());
        if (updateCargoEventHandler != null) {
            UpdateCargoEvent updateCargoEvent = new UpdateCargoEvent(this, Integer.parseInt(input),newStorageLocation);
            updateCargoEventHandler.handle(updateCargoEvent);
        }
    }
    private void listCustomers(){
        if (listCustomerEventHandler != null){
            ListCustomerEvent listCustomerEvent = new ListCustomerEvent(this,manager.getCargos());
            listCustomerEventHandler.handle(listCustomerEvent);
        }
    }
    private void listCargo(String cargoType){
        if (listCargoEventEventHandler != null){
            ListCargoEvent listCargoEvent = new ListCargoEvent(this,cargoType,manager.getCargos());
            listCargoEventEventHandler.handle(listCargoEvent);
        }
    }
    private void listHazard(boolean hasHazard){
        if (listHazardEventHandler != null){
            ListHazardEvent listHazardEvent = new ListHazardEvent(this,hasHazard);
            listHazardEventHandler.handle(listHazardEvent);

        }
    }
    private void persistCargo(String input){
        String[] parts = input.split(" ");
        System.out.println(parts[0] + parts[1]);

        if (parts.length != 2) {
            System.out.println("Invalid input.");
            return;
        }

        String action = parts[0].toLowerCase();
        String method = parts[1].toUpperCase();
        System.out.println(action + method);

        switch (action) {
            case "save":
                System.out.println("Saving....");
                if ("JOS".equals(method)) {
                    saveJOS();
                } else if ("JBP".equals(method)) {
                    saveJBP();
                } else {
                    System.out.println("Invalid method. Use: save [JOS|JBP]");
                }
                System.out.println("Saving done.");
                break;

            case "load":
                System.out.println("Loading....");
                if ("JOS".equals(method)) {
                    loadJOS();
                } else if ("JBP".equals(method)) {
                    loadJBP();
                } else {
                    System.out.println("Invalid method. Use: load [JOS|JBP]");
                }
                System.out.println("Loading done.");
                break;

            default:
                System.out.println("Invalid command. Use: save [JOS|JBP] or load [JOS|JBP]");
        }
    }

    private void loadJOS(){
        if (loadJOSEventHandler != null) {
            LoadJOSEvent loadJOSEvent = new LoadJOSEvent(this);
            loadJOSEventHandler.handle(loadJOSEvent);
        }
    }
    private void saveJOS(){
        if (saveJOSEventHandler != null) {
            SaveJOSEvent saveJOSEvent = new SaveJOSEvent(this);
            saveJOSEventHandler.handle(saveJOSEvent);
        }
    }
    private void loadJBP(){
        if (loadJBPEventHandler != null) {
            LoadJBPEvent loadJBPEvent = new LoadJBPEvent(this);
            loadJBPEventHandler.handle(loadJBPEvent);
        }
    }
    private void saveJBP(){
        if (saveJBPEventHandler != null) {
            SaveJBPEvent saveJBPEvent = new SaveJBPEvent(this);
            saveJBPEventHandler.handle(saveJBPEvent);
        }
    }
}
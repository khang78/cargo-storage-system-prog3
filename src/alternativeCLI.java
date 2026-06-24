import cargoObserver.Observer;
import cargoObserver.ObserverCapacity;
import cargoObserver.ObserverHazard;
import cli.CLI;
import event.*;
import geschaeftslogik.Manager;

// alternative CLI, Löschen der Kunden und Auflisten der Gefahrenstoffe deaktiviert
public class alternativeCLI {
    public static void main(String[] args) {
        int capacity = Integer.parseInt(args[0]);

        Manager manager = new Manager(capacity);

        EventHandler<AddCargoEvent> addCargoHandler = new EventHandler<>();
        AddCargoListener addCargoListener = new AddCargoListener(manager);
        addCargoHandler.add(addCargoListener);

        EventHandler<ListCargoEvent> listCargoHandler = new EventHandler<>();
        ListCargoListener listCargoListener = new ListCargoListener(manager);
        listCargoHandler.add(listCargoListener);

        EventHandler<DeleteCargoEvent> deleteCargoHandler = new EventHandler<>();
        DeleteCargoListener deleteCargoListener = new DeleteCargoListener(manager);
        deleteCargoHandler.add(deleteCargoListener);

        EventHandler<UpdateCargoEvent> updateCargoHandler = new EventHandler<>();
        UpdateCargoListener updateCargoListener = new UpdateCargoListener(manager);
        updateCargoHandler.add(updateCargoListener);

        EventHandler<ListCustomerEvent> listCustomerHandler = new EventHandler<>();
        ListCustomerListener listCustomerListener = new ListCustomerListener(manager);
        listCustomerHandler.add(listCustomerListener);

        // deactivated
        //EventHandler<DeleteCustomerEvent> deleteCustomerHandler = new EventHandler<>();
        //DeleteCustomerListener deleteCustomerListener = new DeleteCustomerListener(manager);
        //deleteCustomerHandler.add(deleteCustomerListener);

        EventHandler<AddCustomerEvent> addCustomerHandler = new EventHandler<>();
        AddCustomerListener addCustomerListener = new AddCustomerListener(manager);
        addCustomerHandler.add(addCustomerListener);

        // deactivate
        //EventHandler<ListHazardEvent> listHazardHandler = new EventHandler<>();
        //ListHazardListener listHazardListener = new ListHazardListener(manager);
        //listHazardHandler.add(listHazardListener);

        EventHandler<LoadJOSEvent> loadJOSEventHandler = new EventHandler<>();
        LoadJOSListener loadJOSListener = new LoadJOSListener(manager);
        loadJOSEventHandler.add(loadJOSListener);

        EventHandler<SaveJOSEvent> saveJOSEventHandler = new EventHandler<>();
        SaveJOSListener saveJOSListener = new SaveJOSListener(manager);
        saveJOSEventHandler.add(saveJOSListener);

        EventHandler<LoadJBPEvent> loadJBPEventHandler = new EventHandler<>();
        LoadJBPListener loadJBPListener = new LoadJBPListener(manager);
        loadJBPEventHandler.add(loadJBPListener);

        EventHandler<SaveJBPEvent> saveJBPEventHandler = new EventHandler<>();
        SaveJBPListener saveJBPListener = new SaveJBPListener(manager);
        saveJBPEventHandler.add(saveJBPListener);

        Observer capacityObserver = new ObserverCapacity(manager);
        manager.addObserver(capacityObserver);
        Observer hazardObserver = new ObserverHazard(manager);
        manager.addObserver(hazardObserver);

        CLI client = new CLI(manager);
        client.setAddCargoEventHandler(addCargoHandler);
        client.setListCargoEventHandler(listCargoHandler);
        client.setDeleteCargoEventHandler(deleteCargoHandler);
        client.setUpdateCargoEventHandler(updateCargoHandler);
        client.setAddCustomerEventHandler(addCustomerHandler);
        client.setListCustomerEventHandler(listCustomerHandler);
        //client.setDeleteCustomerEventHandler(deleteCustomerHandler);
        //client.setListHazardEventHandler(listHazardHandler);
        client.setLoadJOSEventHandler(loadJOSEventHandler);
        client.setSaveJOSEventHandler(saveJOSEventHandler);
        client.setLoadJBPEventHandler(loadJBPEventHandler);
        client.setSaveJBPEventHandler(saveJBPEventHandler);

        client.start();
    }
}

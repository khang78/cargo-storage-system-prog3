package event;

import geschaeftslogik.CustomerImpl;
import geschaeftslogik.Manager;

public class AddCustomerListener implements EventListener<AddCustomerEvent> {
    Manager manager;
public AddCustomerListener(Manager manager) {
    this.manager = manager;
}
    @Override
    public void onEvent(AddCustomerEvent event) {
        CustomerImpl customer = event.getCustomer();
        manager.createCustomer(customer);
        System.out.println("New customer " + customer.getName() +" added!");
    }
}

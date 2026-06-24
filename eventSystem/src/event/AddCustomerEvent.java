package event;

import geschaeftslogik.CustomerImpl;

import java.util.EventObject;

public class AddCustomerEvent extends EventObject {
    private CustomerImpl customer;
    public AddCustomerEvent(Object source,CustomerImpl customer) {
        super(source);
        this.customer = customer;

    }
    public CustomerImpl getCustomer() {
        return customer;
    }
}

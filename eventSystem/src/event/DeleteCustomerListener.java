package event;

import geschaeftslogik.Manager;

public class DeleteCustomerListener implements EventListener<DeleteCustomerEvent>{
    Manager manager;
public DeleteCustomerListener(Manager manager){
    this.manager = manager;
}

    @Override
    public void onEvent(DeleteCustomerEvent event) {
     boolean removecustomer = manager.deleteCustomer(event.getOwner());

     if(removecustomer){
         System.out.println("Customer deleted successfully");
     }else {
         System.out.println("Customer could not be deleted");
     }

    }
}

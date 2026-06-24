package event;

import java.util.EventObject;

public class DeleteCustomerEvent extends EventObject {
 private String customer;
 public DeleteCustomerEvent(Object source, String customer) {
     super(source);
     this.customer = customer;
 }
 public String getOwner() {
     return customer;
 }
}

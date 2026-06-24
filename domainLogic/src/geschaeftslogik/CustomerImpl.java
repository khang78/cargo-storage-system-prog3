package geschaeftslogik;

import administration.Customer;

import java.io.Serializable;

public class CustomerImpl implements Customer, Serializable {
    private String name;


    public CustomerImpl(String name) {
        this.name = name;
    }

    public CustomerImpl() {}

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package geschaeftslogik;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerImplTest {

    @Test
    public void getName() {
        CustomerImpl customer = new CustomerImpl("Khang");
        assertEquals("Khang", customer.getName());

    }

}

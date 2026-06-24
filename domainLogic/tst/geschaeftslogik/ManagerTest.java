package geschaeftslogik;

import administration.Customer;
import cargo.Cargo;
import cargo.Hazard;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ManagerTest {
    private Manager manager;
    private DryBulkCargoImpl cargo;
    private DryBulkCargoImpl cargo1;


    @Test
    public void createCustomerTest(){
        manager = new Manager();
        CustomerImpl customer = new CustomerImpl("Khang");
        manager.createCustomer(customer);

        assertEquals(1, manager.getCustomers().size());
    }

    @Test
    public void deleteCustomerTest(){
        manager = new Manager();
        CustomerImpl customer = new CustomerImpl("Khang");
        manager.createCustomer(customer);

        manager.deleteCustomer("Khang");
        assertEquals(0, manager.getCustomers().size());
    }
    @Test
    public void addCargoMaxCapacityTest(){
        manager = new Manager(1);
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidAndDryBulkCargo";
        int storageLocation = 1;
        int storageLocation2 = 3;
        int grainSize = 1;
        cargo = new DryBulkCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,grainSize);
        cargo1 = new DryBulkCargoImpl(cargoType,value,hazards,customer,duration,storageLocation2,grainSize);
        assertTrue(manager.addCargo(cargo));
        assertFalse(manager.addCargo(cargo1));


    }

    @Test
    public void addCargoFailsStoragelocationTakenTest(){
        manager = new Manager();
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidAndDryBulkCargo";
        int storageLocation = 1;
        int grainSize = 1;
        cargo = new DryBulkCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,grainSize);
        cargo1 = new DryBulkCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,grainSize);

        assertTrue(manager.addCargo(cargo));
        assertFalse(manager.addCargo(cargo1),"Storagelocation taken");
    }
    @Test
    public void updateCargoFailsStoragelocationNotFoundTest(){
        manager = new Manager();
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidAndDryBulkCargo";
        int storageLocation = 1;
        int grainSize = 1;
        cargo = new DryBulkCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,grainSize);

        boolean updated = manager.updateCargo(2,10);
        assertFalse(updated,"No Cargo at Location 2");
    }
    @Test
    public void deleteCargoFailsCargoNotFoundTest(){
        manager = new Manager();
        Collection<Hazard> hazards = new ArrayList<>();
        Customer customer = new CustomerImpl("Khang");
        BigDecimal value = new BigDecimal("10");
        Duration duration = Duration.ofMinutes(5);
        String cargoType = "LiquidAndDryBulkCargo";
        int storageLocation = 1;
        int grainSize = 1;
        cargo = new DryBulkCargoImpl(cargoType,value,hazards,customer,duration,storageLocation,grainSize);
        manager.addCargo(cargo);
        boolean delete = manager.removeCargo(2);
        assertFalse(delete,"No Cargo at Location 2");

    }


    @Test
    public void removeCargoMockTest() {
        manager = new Manager();
        CargoObjekt cargo = mock(CargoObjekt.class);
        when(cargo.getStorageLocation()).thenReturn(1);
        when(cargo.getHazards()).thenReturn(new ArrayList<>());
        when(cargo.getCargoType()).thenReturn("DryBulkCargo");

        manager.addCargo(cargo);

        assertTrue(manager.removeCargo(1));
        verify(cargo).getStorageLocation();
    }
    @Test
    public void customerDoesNotExistsMockTest(){
        manager = new Manager();
        CustomerImpl customer = mock(CustomerImpl.class);
        when(customer.getName()).thenReturn("Khang");
        boolean exist = manager.customerExists(customer);
        assertFalse(exist);
        verify(customer, never()).getName();

    }
    @Test
    public void customerExistsMockTest() {
        manager = new Manager();
        CustomerImpl customer = mock(CustomerImpl.class);
        when(customer.getName()).thenReturn("Khang");

        manager.createCustomer(customer);
        boolean exists = manager.customerExists(customer);

        assertTrue(exists);
        verify(customer,atLeastOnce()).getName();
    }
    @Test
    public void addCargoMockTest(){
        manager = new Manager();
        CargoObjekt objekt = mock(CargoObjekt.class);
        when(objekt.getStorageLocation()).thenReturn(Integer.valueOf("1"));
        when(objekt.getHazards()).thenReturn(new ArrayList<>());
        when(objekt.getCargoType()).thenReturn("DryBulkCargo");
        assertTrue(manager.addCargo(objekt));
        assertEquals(1, manager.getCargos().size());
        verify(objekt).getStorageLocation();
        verify(objekt).getHazards();
    }
    @Test
    public void deleteCargoMockTest(){
        manager = new Manager();
        CargoObjekt objekt = mock(CargoObjekt.class);
        when(objekt.getStorageLocation()).thenReturn(1);
        when(objekt.getHazards()).thenReturn(new ArrayList<>());
        when(objekt.getCargoType()).thenReturn("DryBulkCargo");
        assertTrue(manager.addCargo(objekt));
        assertTrue(manager.removeCargo(objekt.getStorageLocation()));

        assertEquals(0, manager.getCargos().size());
    }
    @Test
    public void updateCargoMockTest() {
        manager = new Manager();
        CargoObjekt objekt = mock(CargoObjekt.class);
        when(objekt.getStorageLocation()).thenReturn(2);
        when(objekt.getHazards()).thenReturn(new ArrayList<>());
        when(objekt.getCargoType()).thenReturn("DryBulkCargo");
        assertTrue(manager.addCargo(objekt));
        assertTrue(manager.updateCargo(2,3));

    }

}





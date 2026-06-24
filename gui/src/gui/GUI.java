package gui;

import administration.Customer;
import cargo.Cargo;
import cargo.Hazard;
import geschaeftslogik.CargoObjekt;
import geschaeftslogik.CustomerImpl;
import geschaeftslogik.Manager;
import javafx.application.Application;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

// Quelle: https://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm
public class GUI extends Application{
    private TableView<Customer> customerTable;
    private TableView<CargoObjekt> cargoTable;
    private Manager manager;

    @Override
    public void start(Stage stage) throws Exception {
        manager = new Manager(100);

        customerTable = createCustomerTable();
        cargoTable = createCargoTable();

        BorderPane root = new BorderPane();
        root.setRight(customerTable);
        root.setCenter(cargoTable);
        root.setTop(createControlPanel());

        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("GUI");
        stage.setScene(scene);
        stage.show();
    }

    private Node createControlPanel() {
        Label label = new Label("What do you want to do?");
        Button addCustomer = new Button("Add Customer");
        Button addCargo = new Button("Add Cargo");

        addCustomer.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add a new Customer");
            dialog.setContentText("Customer name:");

            // Create Customer Dialog
            dialog.showAndWait().ifPresent(name -> {
                try {
                    CustomerImpl customer = new CustomerImpl(name);
                    manager.createCustomer(customer);
                    customerTable.setItems(FXCollections.observableArrayList(manager.getCustomers()));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        });

        addCargo.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add a new Cargo");

            TextField customerName = new TextField();
            customerName.setPromptText("Customer name");
            ComboBox<String> cargoType = new ComboBox<>();
            cargoType.getItems().addAll(
                    "DryBulkAndUnitisedCargo",
                    "DryBulkCargo",
                    "LiquidAndDryBulkCargo",
                    "LiquidBulkAndUnitisedCargo",
                    "LiquidBulkCargo",
                    "UnitisedCargo"
            );
            cargoType.setPromptText("Select cargo type");
            TextField value  = new TextField();
            value.setPromptText("Value");
            TextField hazard = new TextField();
            hazard.setPromptText("Hazard");

            TextField grainSize = new TextField();
            grainSize.setPromptText("Grain size (optional)");

            CheckBox pressurized = new CheckBox("Pressurized");
            CheckBox fragile = new CheckBox("Fragile");

            VBox vbox = new VBox(10, new Label("Enter cargo data:"), customerName, cargoType, value, hazard, grainSize, pressurized, fragile);
            dialog.getDialogPane().setContent(vbox);

            dialog.showAndWait().ifPresent(type -> {
                    try {
                        String name = customerName.getText();
                        Customer customer = manager.getCustomer(name);

                        if (customer == null) {
                            showAlert("Customer not found");
                            return;
                        }

                        String cargoTypeInput = cargoType.getValue();
                        BigDecimal valueInput = new BigDecimal(value.getText());
                        String hazardInput = hazard.getText();
                        Random r = new Random();
                        int storageLocation = r.nextInt(100);

                        CargoObjekt newCargo = new CargoObjekt(cargoTypeInput, valueInput, Collections.singleton(Hazard.valueOf(hazardInput)), customer, Duration.ZERO, storageLocation);

                        if (!grainSize.getText().trim().isEmpty()) {
                            try {
                                newCargo.setgrainSize(Integer.parseInt(grainSize.getText().trim()));
                            } catch (NumberFormatException nfe) {
                                showAlert("Grain size must be a number");
                                return;
                            }
                        }

                        newCargo.setPressurized(pressurized.isSelected());
                        newCargo.setFragile(fragile.isSelected());

                        manager.addCargo(newCargo);
                        cargoTable.setItems(FXCollections.observableArrayList(manager.getCargos()));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        showAlert("Error while adding cargo: " + ex.getMessage());
                    }
                });
        });

        return new HBox(10, label, addCustomer, addCargo);
    }

    private TableView<CargoObjekt> createCargoTable() {
        TableView<CargoObjekt> table = new TableView<>();

        TableColumn<CargoObjekt, String> locationCol = new TableColumn<>("Storage Location");
        TableColumn<CargoObjekt, String> customerCol = new TableColumn<>("Customer");
        TableColumn<CargoObjekt, Date> dateCol = new TableColumn<>("Inspection Date");
        TableColumn<CargoObjekt, String> durationCol = new TableColumn<>("Storage Duration");

        locationCol.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getStorageLocation())));
        customerCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOwner().getName()));
        dateCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getLastInspectionDate()));
        durationCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDurationOfStorage().toString()));

        locationCol.setSortable(true);
        customerCol.setSortable(true);
        dateCol.setSortable(true);
        durationCol.setSortable(true);

        table.getColumns().addAll(locationCol, customerCol, dateCol, durationCol);
        table.setItems(FXCollections.observableArrayList(manager.getCargos()));
        System.out.println(manager.getCargos());
        return table;
    }

    private TableView<Customer> createCustomerTable() {
        TableView<Customer> table = new TableView<>();

        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        table.getColumns().add(nameCol);
        table.setItems(FXCollections.observableArrayList(manager.getCustomers()));

        return table;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}

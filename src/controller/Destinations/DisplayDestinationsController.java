package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class DisplayDestinationsController extends Controller<Agency> {
    @FXML private TextField countryFilterTf;
    @FXML private TableView<Destination> destinationsTableView;
    @FXML private TableColumn<Destination, String> nameColumn;
    @FXML private TableColumn<Destination, String> countryColumn;

    private FilteredList<Destination> filteredDestinations;

    public ObservableList<Destination> getDestinations() {
        return model.getDestinations().getDestinations();
    }

    @FXML private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());

        filteredDestinations = new FilteredList<>(getDestinations(), p -> true); // Initially show all destinations

        countryFilterTf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDestinations.setPredicate(destination -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // If filter text is empty, display all destinations
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (destination.getCountry().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches country
                }
                return false; // Does not match
            });
        });

        destinationsTableView.setItems(filteredDestinations);
    }

    @FXML
    private void exit(ActionEvent event) {
        stage.close();
    }
}

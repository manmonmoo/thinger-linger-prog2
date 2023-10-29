package controller.Flights;

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

public class DisplayFlightsController extends Controller<Agency> {
	@FXML private TextField countryFilterTf;
    @FXML private TableView<Flight> flightsTableView;
    @FXML private TableColumn<Flight, String> airlineColumn;
    @FXML private TableColumn<Flight, Integer> flightNoColumn;
    @FXML private TableColumn<Flight, String> takeoffColumn;
    @FXML private TableColumn<Flight, String> landingColumn;
    @FXML private TableColumn<Flight, Double> costColumn;

	private FilteredList<Flight> filteredFlights;

	public ObservableList<Flight> getFlights() {
		return model.getFlights().getFlights();
	}

	@FXML private void initialize() {
        airlineColumn.setCellValueFactory(cellData -> cellData.getValue().airlineProperty());
        flightNoColumn.setCellValueFactory(cellData -> cellData.getValue().flightNumberProperty().asObject());
        takeoffColumn.setCellValueFactory(cellData -> cellData.getValue().takeoffProperty());
        landingColumn.setCellValueFactory(cellData -> cellData.getValue().landingProperty());
        costColumn.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());

        filteredFlights = new FilteredList<>(getFlights(), p -> true); // Initially show all flights

        countryFilterTf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredFlights.setPredicate(flight -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // If filter text is empty, display all flights
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (flight.getTakeoff().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches takeoff country
                } else if (flight.getLanding().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches landing country
                }
                return false; // Does not match
            });
        });

        flightsTableView.setItems(filteredFlights);
    }

	@FXML
	private void exit(ActionEvent event) {
		stage.close();
	}
}

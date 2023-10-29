package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class DisplayFlightsController extends Controller<Agency> {

	@FXML
	private TableView<Flight> flightsTableView;
	@FXML
	private TableColumn<Flight, String> airlineColumn;
	@FXML
	private TableColumn<Flight, Integer> flightNoColumn;
	@FXML
	private TableColumn<Flight, String> takeoffColumn;
	@FXML
	private TableColumn<Flight, String> landingColumn;
	@FXML
	private TableColumn<Flight, Double> costColumn;

	public ObservableList<Flight> getFlights() {
		return model.getFlights().getFlights();
	}

	public void initialize() {
		// Bind the TableView to the flights data
		flightsTableView.setItems(model.getFlights().getFlights());

		// Set cell value factories for each column
		airlineColumn.setCellValueFactory(cellData -> cellData.getValue().airlineProperty());
		flightNoColumn.setCellValueFactory(cellData -> cellData.getValue().flightNumberProperty().asObject());
		takeoffColumn.setCellValueFactory(cellData -> cellData.getValue().takeoffProperty());
		landingColumn.setCellValueFactory(cellData -> cellData.getValue().landingProperty());
		costColumn.setCellValueFactory(cellData -> cellData.getValue().costProperty().asObject());
	}

	@FXML
	private void exit(ActionEvent event) {
		stage.close();
	}
}

package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;

public class AgencyController extends Controller<Agency> {

	@FXML
	private Label greetingLabel;
	
	private Stage flightStage = new Stage();
	private Stage destinationStage = new Stage();
	private Stage tripStage = new Stage();
	
	private Trip tripModel;
	
	

	public void initialize() {
		tripModel = new Trip(model);
		Administrator admin = model.getLoggedInUser();
		if (admin != null) {
			greetingLabel.setText("Hi, " + admin.getName() + ", welcome to the Prog2 Travel Agency");
		}
	}

	@FXML
	private void exploreFlights(ActionEvent event) {
		try {
			ViewLoader.showStage(model, "/view/Flights/ExploreFlightsView.fxml", "Explore Flight View", flightStage);
			flightStage.getIcons().add(new Image("/image/flights_icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void exploreDestinations(ActionEvent event) {
		try {
			ViewLoader.showStage(model, "/view/Destinations/ExploreDestinationsView.fxml", "Explore Destination View", destinationStage);
			destinationStage.getIcons().add(new Image("/image/destinations_icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void bookTrip(ActionEvent event) {
		try {
			ViewLoader.showStage(tripModel, "/view/Trip/BookTripView.fxml", "Book A Trip", tripStage);
			tripStage.getIcons().add(new Image("/image/trip_icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void exit(ActionEvent event) {
		stage.close();
	}

}

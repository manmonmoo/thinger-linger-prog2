package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;

public class ExploreFlightsController extends Controller<Agency> {
    @FXML
    private Label greetingLabel;

    private Stage flightStage = new Stage();
    private Stage countryStage = new Stage();
    private Stage addfStage = new Stage();
    private Stage removefStage = new Stage();

    public void initialize() {
        Administrator admin = model.getLoggedInUser();
        if (admin != null) {
            greetingLabel.setText("Hi, " + admin.getName() + ", welcome to the Flights section");
        }
    }

    @FXML
    private void viewallFlights(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Flights/DisplayFlightsView.fxml", "Display Flights View", flightStage);
            flightStage.getIcons().add(new Image("/image/flights_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewFlightsCountry(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Flights/DisplayFilteredFlightsView.fxml", "Display Flights by Country", countryStage);
            countryStage.getIcons().add(new Image("/image/destinations_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addFlight(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Flights/AddFlightView.fxml", "Add a Flight", addfStage);
           addfStage.getIcons().add(new Image("/image/flight_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void removeFlight(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Flights/RemoveFlightView.fxml", "Remove a Flight", removefStage);
            removefStage.getIcons().add(new Image("/image/flight_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        stage.close();
    }

}
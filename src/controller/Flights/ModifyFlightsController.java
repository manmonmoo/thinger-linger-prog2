package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;
import model.Exceptions.*;

public class ModifyFlightsController extends Controller<Agency> {
    @FXML
    private TextField airlineTf;
    @FXML
    private TextField flightNumberTf;
    @FXML
    private TextField takeoffTf;
    @FXML
    private TextField landingTf;
    @FXML
    private TextField costTf;

    private Stage flightsStage = new Stage();

    @FXML
    private void handleAddFlight(ActionEvent event) {
        String airline = airlineTf.getText();
        int flightNumber;
        String takeoff = takeoffTf.getText();
        String landing = landingTf.getText();
        double cost;

        try {
            flightNumber = Integer.parseInt(flightNumberTf.getText());
            cost = Double.parseDouble(costTf.getText());
        } catch (NumberFormatException e) {
            new ErrorModel(e, "Flight Number and Cost must be numbers.");
            return;
        }

        try {
            if (model.getFlights().hasFlight(takeoff, landing)) {
                throw new DuplicateItemException();
            }

            model.getFlights().addFlight(new Flight(airline, flightNumber, takeoff, landing, cost));

            Stage currentStage = (Stage) airlineTf.getScene().getWindow();
            currentStage.close();

            ViewLoader.showStage(model, "/view/DisplayFlightsView.fxml", "Display Flights", flightsStage);
            flightsStage.getIcons().add(new Image("/image/flights.png"));
        } catch (DuplicateItemException e) {
            new ErrorModel(e, "Flight with the same takeoff and landing already exists.");
        } catch (IOException e) {
            new ErrorModel(e, "Error loading the Display Flights view.");
        }
    }
    @FXML
    private void handleClose(ActionEvent event) {
        Stage currentStage = (Stage) airlineTf.getScene().getWindow();
        currentStage.close();
    }
}

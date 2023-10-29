package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
            showErrorWindow(e, "Flight Number and Cost must be numbers.");
            return;
        }

        try {
            if (model.getFlights().hasFlight(takeoff, landing)) {
                exit(event);
                throw new DuplicateItemException();
            }
            else {
                model.getFlights().addFlight(new Flight(airline, flightNumber, takeoff, landing, cost));
                exit(event); // Close the window if input is valid
            }
            
        } catch (DuplicateItemException e) {
            showErrorWindow(e, "Flight with the same takeoff and landing already exists.");
        }
    }

    @FXML
    private void handleRemoveFlight(ActionEvent event) {
        String takeoff = takeoffTf.getText();
        String landing = landingTf.getText();

        try {
            Flight flightToRemove = model.getFlights().getFlight(takeoff, landing);
            if (flightToRemove != null) {
                model.getFlights().removeFlight(flightToRemove);
                exit(event); // Close the window if input is valid
            } else {
                throw new ItemNotFoundException();
            }
        } catch (ItemNotFoundException e) {
            showErrorWindow(e, "Flight with the specified takeoff and landing locations was not found.");
        }
    }

    

    private void showErrorWindow(Exception e, String message) {
        // Implement the logic to show the error window with the provided message
    }

    @FXML
    private void exit(ActionEvent event) {
        stage.close();
    }
}

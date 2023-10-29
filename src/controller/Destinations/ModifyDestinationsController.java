package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import model.*;
import model.Exceptions.*;

public class ModifyDestinationsController extends Controller<Agency> {
    @FXML
    private TextField nameTf;
    @FXML
    private TextField countryTf;

    @FXML
    private void handleAddDestination(ActionEvent event) {
        String name = nameTf.getText();
        String country = countryTf.getText();

        try {
            if (model.getDestinations().hasDestination(name, country)) {
                exit(event);
                throw new DuplicateItemException();
            }
            else {
                model.getDestinations().addDestination(new Destination(name, country));
                exit(event); // Close the window if input is valid
            }
            
        } catch (DuplicateItemException e) {
            showErrorWindow(e, "Destination with the same name and country already exists.");
        }
    }

    @FXML
    private void handleRemoveDestination(ActionEvent event) {
        String name = nameTf.getText();
        String country = countryTf.getText();

        try {
            Destination destinationToRemove = model.getDestinations().getDestination(name, country);
            if (destinationToRemove != null) {
                model.getDestinations().removeDestination(destinationToRemove);
                exit(event); // Close the window if input is valid
            } else {
                throw new ItemNotFoundException();
            }
        } catch (ItemNotFoundException e) {
            showErrorWindow(e, "Destination with the specified name and country was not found.");
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

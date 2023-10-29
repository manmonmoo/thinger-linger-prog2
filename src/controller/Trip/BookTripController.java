package controller.Trip;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;
import javafx.scene.control.TextField;
import model.Exceptions.*;
public class BookTripController extends Controller<Agency> {
    private Stage viewTripStage = new Stage();

    @FXML
    private TextField nameTf;
    @FXML
    private TextField countryTf;


    @FXML
    private Label greetingLabel;
    public void initialize() {
        Administrator admin = model.getLoggedInUser();
        if (admin != null) {
            greetingLabel.setText("Hi, " + admin.getName() + ", welcome to the Flights section");
        }
    }

    @FXML
    private void addDestination(ActionEvent event) {
        String name = nameTf.getText();
        String country = countryTf.getText();

        try {
            if (model.getDestinations().hasDestination(name, country)) {
                exit(event);
                throw new DuplicateItemException();
            } else {
                model.getDestinations().addDestination(new Destination(name, country));
                exit(event); // Close the window if input is valid
            }
        } catch (DuplicateItemException e) {
            showErrorWindow(e, "Destination with the same name and country already exists.");
        }
    }

    @FXML
    private void removeDestination(ActionEvent event) {
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
    private void addConnectingFlights(ActionEvent event) {
        // Implement logic to add connecting flights
    }

    @FXML
    private void viewTrip(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Trip/ViewTripView.fxml", "View Trip", viewTripStage);
            viewTripStage.getIcons().add(new Image("/image/trip_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        stage.close();
    }
}

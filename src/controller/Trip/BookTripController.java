package controller.Trip;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.InsufficientDestinationsException;

public class BookTripController extends Controller<Trip> {
    private Agency agency;

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    private Stage viewTripStage = new Stage();
    private Stage addfStage = new Stage();
    private Stage removefStage = new Stage();


    @FXML private ListView<Itinery> itineryListView;

    @FXML
    private Label greetingLabel;
    public void initialize() {
        Administrator admin = model.getAgency().getLoggedInUser();
        if (admin != null) {
            greetingLabel.setText("Hi, " + admin.getName() + ", welcome to the Flights section");
        }
    }

    @FXML
    private void addDestination(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Destinations/AddDestinationView.fxml", "Add a Destination", addfStage);
           addfStage.getIcons().add(new Image("/image/destinations_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void removeDestination(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Destinations/RemoveDestinationView.fxml", "Remove a Destination", removefStage);
            removefStage.getIcons().add(new Image("/image/destinations_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addConnectingFlights(ActionEvent event) throws DuplicateItemException, InsufficientDestinationsException {
        model.addConnectingFlights();
        itineryListView.setItems(model.getItinery());
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

      private void showErrorWindow(Exception e, String message) {
        // Implement the logic to show the error window with the provided message
    }

    @FXML
    private void exit(ActionEvent event) {
        stage.close();
    }
}

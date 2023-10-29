package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;

public class ExploreDestinationsController extends Controller<Agency> {
    @FXML
    private Label greetingLabel;

    private Stage destinationStage = new Stage();
    private Stage countryStage = new Stage();
    private Stage addfStage = new Stage();
    private Stage removefStage = new Stage();

    public void initialize() {
        Administrator admin = model.getLoggedInUser();
        if (admin != null) {
            greetingLabel.setText("Hi, " + admin.getName() + ", welcome to the Destinations section");
        }
    }

    @FXML
    private void viewalldestinations(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations View", destinationStage);
            destinationStage.getIcons().add(new Image("/image/destinations_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewallfiltereddestinations(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Destinations/DisplayFilteredDestinationsView.fxml", "Display Destination by Country", countryStage);
            countryStage.getIcons().add(new Image("/image/destinations_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void adddestination(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Destinations/AddDestinationView.fxml", "Add a Destination", addfStage);
           addfStage.getIcons().add(new Image("/image/destinations_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void removedestination(ActionEvent event) {
        try {
            ViewLoader.showStage(model, "/view/Destinations/RemoveDestinationView.fxml", "Remove a Destination", removefStage);
            removefStage.getIcons().add(new Image("/image/destinations_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        stage.close();
    }

}
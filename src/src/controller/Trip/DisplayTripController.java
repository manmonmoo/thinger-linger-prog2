package controller.Trip;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.*;

public class DisplayTripController extends Controller<Trip> {

    @FXML private ListView<Itinery> itineryListView; // Reference to the ListView

    public void initialize() {
        itineryListView.setItems(model.getItinery());
        itineryListView.setCellFactory(listView -> new ItineryListCell());
    }

    private void showErrorWindow(Exception e, String message) {
        // Implement the logic to show the error window with the provided message
    }

    @FXML
    private void exit(ActionEvent event) {
        stage.close();
    }

    // Inner class to customize the display of Itinery items in the ListView
    private static class ItineryListCell extends javafx.scene.control.ListCell<Itinery> {
        @Override
        protected void updateItem(Itinery item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                setText(null);
            } else {
                setText(item.toString()); // Assuming Itinery has a suitable toString() method
            }
        }
    }
}

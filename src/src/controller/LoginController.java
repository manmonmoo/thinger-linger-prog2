package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.*;
import model.Exceptions.InvalidCredentialsException;

public class LoginController extends Controller<Agency> {
	@FXML
	private TextField usernameTf;
	@FXML
	private PasswordField passwordTf;

	private Stage agencyStage = new Stage();
	
	private Agency getAgency() {
		return model;
	}

	@FXML
	private void handleLogin(ActionEvent event) throws InvalidCredentialsException {
		String username = usernameTf.getText();
		String password = passwordTf.getText();

		if (getAgency().getAdministrators().hasAdministrator(username, password)) {
			getAgency().setLoggedInUser(getAgency().getAdministrators().getAdministrator(username, password));
			Stage currentStage = (Stage) usernameTf.getScene().getWindow();
			currentStage.close();

			try {
				ViewLoader.showStage(getAgency(), "/view/AgencyView.fxml", "Agency View", agencyStage);
				agencyStage.getIcons().add(new Image("/image/agency_icon.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import fr.formation.afpa.model.Etudiant;
import fr.formation.afpa.service.EtudiantService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerAjout implements Initializable {
	@FXML
	Label lblPrenom;
	@FXML
	TextField txtPrenom;
	@FXML
	TextField txtNom;
	@FXML
	Button btnEtuEnregistrer;
	@FXML
	DatePicker dateSelector;
	@FXML
	AnchorPane mainAnchor;
	@FXML
	AnchorPane ongletAjout;
	@FXML
	Button btnParcour;
	@FXML
	ImageView imgView;
	@FXML
	TextField pathPhoto;
	
	String imagepath;

	EtudiantService eserv = new EtudiantService();

	EtudiantDaoFile edao = new EtudiantDaoFile();

	Etudiant etu;

	Controller conMain = new Controller();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void enregisterEtu() {
		Alert alert = new Alert(AlertType.INFORMATION, "Champ vide", ButtonType.OK);
		Alert alert2 = new Alert(AlertType.INFORMATION, "Enregistrement terminé", ButtonType.OK);
		
		
		
		
		if (!txtPrenom.getText().isEmpty() && !txtNom.getText().isEmpty() && !imagepath.isEmpty() && dateSelector.getValue() != null) {
			String date = dateSelector.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			alert2.showAndWait();
		etu = new Etudiant(txtPrenom.getText(), txtNom.getText(), date, imagepath);

		edao.add(etu);
		for (Etudiant etudiant : edao.getAll()) {

			System.out.println("bouton enregistré" + etudiant);
		}
		}else {
			alert.showAndWait();
		}
	}

	public void ongletMenuPrinc(ActionEvent e) {
		AnchorPane onglet = null;
		try {
			onglet = FXMLLoader.load(getClass().getResource("AnchorExo.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ongletAjout.getChildren().setAll(onglet);
		
		
	}

	public void ajoutPhoto() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open File");
		File file = chooser.showOpenDialog(new Stage());
		chooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
		if (file != null) {

			try {
				imagepath = file.toURI().toURL().toString();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pathPhoto.setText(imagepath);
			System.out.println("file:" + imagepath);
			Image image = new Image(imagepath);
			imgView.setImage(image);
		}

	}
	public void close() {
		Platform.exit();
		
	}
}

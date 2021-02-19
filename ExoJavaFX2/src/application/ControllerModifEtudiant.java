package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.management.Notification;

import fr.formation.afpa.model.Etudiant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerModifEtudiant implements Initializable {
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
	AnchorPane ongletModif;
	@FXML
	Button btnParcour;
	@FXML
	ImageView imgView;
	@FXML
	Label lblNom;
	@FXML
	Label affichDate;
	@FXML
	Button btnModifier;

	String imagepath;
	Etudiant etu;
	EtudiantDaoFile edao = new EtudiantDaoFile();
	int testIndex;
	Controller conMain = new Controller();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		testIndex = Controller.selectdIndex;
		etu = EtudiantDaoFile.lEtudiant.get(testIndex);
		System.out.println(etu);

		Image image = new Image(etu.getPathPhoto());
		txtPrenom.setText(etu.getPrenom());
		txtNom.setText(etu.getNom());
		affichDate.setText(etu.getDateDeN());
		imgView.setImage(image);
		imagepath = etu.getPathPhoto();
		for (Etudiant etudiant : EtudiantDaoFile.lEtudiant) {
			System.out.println(etudiant);
		}
	}

	public void enregisterEtu() {
		Alert alert = new Alert(AlertType.INFORMATION, "Vous pouvez modifier", ButtonType.OK);
		Alert alert2 = new Alert(AlertType.INFORMATION, "Changement enregistré", ButtonType.OK);
        
		btnModifier.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				txtPrenom.setEditable(true);
				txtNom.setEditable(true);
				dateSelector.setVisible(true);
				btnModifier.setText("Enregistrer");
				alert.showAndWait();
				btnModifier.setOnAction(new EventHandler<ActionEvent>() 
				
				{

					@Override
					public void handle(ActionEvent event) {

						etu.setNom(txtNom.getText());
						etu.setPrenom(txtPrenom.getText());
						etu.setPathPhoto(imagepath);

					

						etu.setPathPhoto(imagepath);
						edao.update();
						alert2.showAndWait();
						for (Etudiant etudiant : edao.getAll()) {

							System.out.println(etudiant);

						}

					}

				});

			}

		});

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
			System.out.println("file:" + imagepath);
			Image image = new Image(imagepath);
			imgView.setImage(image);
		}

	}

	public void ongletMenuPrinc(ActionEvent e) {
		AnchorPane onglet = null;
		try {
			onglet = FXMLLoader.load(getClass().getResource("AnchorExo.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ongletModif.getChildren().setAll(onglet);
	}

	public void ongletAjoutEtudiant(ActionEvent e) {
		AnchorPane ajetudian = null;
		try {
			ajetudian = FXMLLoader.load(getClass().getResource("AjoutEtudiant.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ongletModif.getChildren().setAll(ajetudian);
	}
	public void close() {
		Platform.exit();
		
	}
}

package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import fr.formation.afpa.model.Etudiant;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.util.Callback;

public class Controller implements Initializable {

	@FXML
	TableView<Etudiant> tableView;
	@FXML
	AnchorPane mainAnchor;
	@FXML
	TableColumn<Etudiant, Long> tblID;
	@FXML
	TableColumn<Etudiant, String> tblPrenom;
	@FXML
	TableColumn<Etudiant, String> tblNom;
	@FXML
	TableColumn<Etudiant, String> tblDdn;
	@FXML
	TableColumn<Etudiant, ImageView> ongletModif;

	Etudiant etu = new Etudiant();

	static Etudiant etuSelect;

	EtudiantDaoFile etuDao = new EtudiantDaoFile();

	static int selectdIndex;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 
		addButtonToTableModif();

		System.out.println("Bonjour");

		tblID.setCellValueFactory(new PropertyValueFactory<Etudiant, Long>("id"));
		tblPrenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
		tblNom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
		tblDdn.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("dateDeN"));

		getPeople(etuDao.recuperationListEtudiant());
//===================================================================================================================================================

//		

	}

	public void initialize2(URL arg0, ResourceBundle arg1l) {

		tblID.setCellValueFactory(new PropertyValueFactory<Etudiant, Long>("id"));
		tblPrenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
		tblNom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
		tblDdn.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("dateDeN"));
		getPeople(etuDao.recuperationListEtudiant());
	}

	private void addButtonToTableModif() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Etes-vous sur de vouloir supprimer", ButtonType.OK , ButtonType.CANCEL );
        

		TableColumn<Etudiant, Void> colBtn = new TableColumn<Etudiant, Void>("Modifier/Supprimer");

		Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>> cellFactory = new Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>>() {
			public TableCell<Etudiant, Void> call(final TableColumn<Etudiant, Void> param) {
				final TableCell<Etudiant, Void> cell = new TableCell<Etudiant, Void>() {

					private final Button btn = new Button();

					{
						File file2 = new File("modifier.jpg");
						Image img2 = new Image(file2.toURI().toString(), 80, 20, true, true);
						ImageView view2 = new ImageView(img2);
						view2.setPreserveRatio(true);
						btn.setGraphic(view2);
						btn.setOnAction((ActionEvent event) -> {
							alert.showAndWait();
							
							selectdIndex = getTableRow().getIndex();
							System.out.println(selectdIndex);
							AnchorPane modifetu = null;
							try {
								modifetu = FXMLLoader.load(getClass().getResource("ModifEtudiant.fxml"));
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							mainAnchor.getChildren().setAll(modifetu);

						});

					}

					private final Button btn2 = new Button();

					{
						

						File file = new File("Supp.jpg");
						Image img = new Image(file.toURI().toString(), 80, 20, true, true);
						ImageView view = new ImageView(img);
						view.setPreserveRatio(true);
						
						btn2.setGraphic(view);
						
						//Le clique de suprimmer
						
				
						btn2.setOnAction((ActionEvent event) -> {
							
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK){
								selectdIndex = getTableRow().getIndex();
								Etudiant etutoto = tableView.getSelectionModel().getSelectedItem();
								tableView.getItems().remove(etutoto);
								EtudiantDaoFile.lEtudiant.remove(selectdIndex);
								etuDao.update();
								System.out.println(selectdIndex);
								
								initialize2(null, null);						} 
							else {
									initialize2(null, null);						}
							


						});

					}

					HBox pane = new HBox(btn, btn2);

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(pane);
						}
					}

				};
				return cell;

			}
		};

		colBtn.setCellFactory(cellFactory);

		tableView.getColumns().add(colBtn);

	}

	public void ongletModifier(ActionEvent e) {

		AnchorPane modifetu = null;
		try {
			modifetu = FXMLLoader.load(getClass().getResource("ModifEtudiant.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mainAnchor.getChildren().setAll(modifetu);
	}

	public void ongletAjoutEtudiant(ActionEvent e) {
		AnchorPane ajetudian = null;
		try {
			ajetudian = FXMLLoader.load(getClass().getResource("AjoutEtudiant.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mainAnchor.getChildren().setAll(ajetudian);
	}

	public void getPeople(List<Etudiant> listEtu) {
		System.out.println("Enter the method");
		ObservableList<Etudiant> data = FXCollections.<Etudiant>observableArrayList();
		data.addAll(listEtu);
		tableView.setItems(data);
	}

	public void close() {
		Platform.exit();
		
	}
}

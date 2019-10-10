package anaofind.lib.anafx.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * classe permettant de faire des choses utiles avec java fx
 * @author anaofind
 *
 */
public class UtilFX {

	/**
	 * methode permettant de fermer l'appliquation
	 * @param code le code de fermeture
	 */
	public static void exit(int code) {
		Platform.exit();
		System.exit(code);
	}
	
	/**
	 * methode permettant de creer une fenetre
	 * @param pathFXML le chemin du fichier fxml
	 * @return la fenetre
	 */
	public static Stage createScreen(String pathFXML) {
		Scene scene = createSceneFXML(pathFXML);
		Stage stage = new Stage();
		stage.setScene(scene);
		return stage;
	}
	
	/**
	 * methode permettant de creer une scene
	 * @return la scene
	 */
	public static Scene createScene() {
		Pane root = new Pane();
		Scene scene = new Scene(root);
		return scene;
	}
	
	/**
	 * methode permettant de creer une scene avec un chemin fxml
	 * @param pathFXML le chemin du fichier fxml
	 * @return la scene
	 */
	public static Scene createSceneFXML(String pathFXML) {
		if (pathFXML != null) {
			String path = getRootPath(pathFXML);
			Pane root;
			try {
				root = FXMLLoader.load(UtilFX.class.getResource(path));
				Scene scene = new Scene(root);
				return scene;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return createScene();
	}
	
	/**
	 * methode permettant de creer une image
	 * @param path le chemin de l'image
	 * @return l'image
	 */
	public static Image createImage(String path) {
		FileInputStream input;
		try {
			input = new FileInputStream(path);
			Image image = new Image(input);
			return image;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * methode permettant de creer une image
	 * @param path le chemin de l'image
	 * @param longueur la longueur
	 * @param largeur la largeur
	 * @return l'image
	 */
	public static Image createImage(String path, int longueur, int largeur) {
		FileInputStream input;
		try {
			input = new FileInputStream(path);
			Image image = new Image(input, longueur, largeur, false, true);
			return image;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * methode permettant d'ajouter du css dans un stage
	 * @param stage le stage
	 * @param pathCSS le chemin du fichier css
	 */
	public static void addCSS(Stage stage, String pathCSS) {
		String path = getRootPath(pathCSS);
		String css = UtilFX.class.getResource(path).toExternalForm(); 
		stage.getScene().getStylesheets().add(css);
	}

	/**
	 * methode permettant d'afficher une info
	 * @param title le titre
	 * @param information l'info
	 */
	public static void showInformation(String title, String information) {
		Alert alerte = new Alert(AlertType.INFORMATION);
		alerte.setTitle("Information");
		alerte.setHeaderText(title);
		alerte.setContentText(information);
		alerte.showAndWait();
	}
	
	/**
	 * methode permettant d'afficher une alerte
	 * @param title le titre
	 * @param information l'info
	 */
	public static void showWarning(String title, String information) {
		Alert alerte = new Alert(AlertType.WARNING);
		alerte.setTitle("Warning");
		alerte.setHeaderText(title);
		alerte.setContentText(information);
		alerte.showAndWait();
	}
	
	/**
	 * methode permettant d'afficher une erreur
	 * @param title le titre
	 * @param information l'info
	 */
	public static void showError(String title, String information) {
		Alert alerte = new Alert(AlertType.ERROR);
		alerte.setTitle("Error");
		alerte.setHeaderText(title);
		alerte.setContentText(information);
		alerte.showAndWait();
	}
	
	/**
	 * methode permettant d'afficher une question ouverte (oui/non)
	 * @param title le titre
	 * @param information l'info
	 * @return la reponse à la question
	 */
	public static int showQuestion(String title, String information) {
		Alert alerte = new Alert(AlertType.CONFIRMATION);
		alerte.setTitle("Question");
		alerte.setHeaderText(title);
		alerte.setContentText(information);
		
		ButtonType oui = new ButtonType("Oui");
		ButtonType non = new ButtonType("Non");
		
		alerte.getButtonTypes().setAll(oui, non);
		alerte.showAndWait();
		
		int reponse = -1;
		
		if (alerte.getResult() == oui) {
			reponse = 1;
		}
		
		return reponse;
	}
	
	/**
	 * methode permettant de recuperer le chemin à partir de la racine du projet 
	 * @param path le path à partir de la racine du projet
	 * @return le chemin à partir de la racine du projet
	 */
	private static String getRootPath(String path) {
		return "/" + path;
	}
}

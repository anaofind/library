package anaofind.lib.anafx.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
	 * @param urlFXML url of fmxl file
	 * @return the screen
	 */
	public static Stage createScreen(URL urlFXML) {
		Scene scene = createSceneFXML(createLoaderFXML(urlFXML));
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
	 * load fxml
	 * @param urlFXML the url of fxml file
	 * @return the fxml loader
	 */
	public static FXMLLoader createLoaderFXML(URL urlFXML) {
		if (urlFXML != null) {
			return new FXMLLoader(urlFXML);	
		} 
		return null;
	}
	
	/**
	 * screate scene fxml
	 * @param fxmlLoader the fxml loader
	 * @return the scene
	 */
	public static Scene createSceneFXML(FXMLLoader fxmlLoader) {
		if (fxmlLoader != null) {
			try {
				Parent root = (Parent) fxmlLoader.load();
				Scene scene = new Scene(root);
				return scene;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return createScene();
	}
	
	/**
	 * get controler of fxml loader
	 * @param fxmlLoader the fxml loader
	 * @return the controler
	 */
	public static Object getControler(FXMLLoader fxmlLoader) {
		if (fxmlLoader != null) {
			return fxmlLoader.getController();	
		}
		return null;
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
	 * add style css to stage
	 * @param stage the stage
	 * @param urlCSS the url of css file
	 */
	public static void addCSS(Stage stage, URL urlCSS) {
		String css = urlCSS.toExternalForm(); 
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
}

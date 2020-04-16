package anaofind.lib.anafx.screen;

import java.io.File;
import java.net.URL;
import java.util.*;

import anaofind.lib.anafx.controler.Controler;
import anaofind.lib.anafx.util.UtilFX;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * classe permettant de representer une fenetre
 * @author anaofind
 *
 */
public abstract class Screen{
	
	/**
	 * le stage
	 */
	private Stage stage;
	
	/**
	 * le chemin du fichier xml
	 */
	private FXMLLoader fxmlLoader;
	
	/**
	 * le titre de la fenetre
	 */
	private String title = "Screen AnaFX";
	
	/**
	 * list url of css
	 */
	private List<URL> listCSS = new ArrayList<URL>();
	
	/**
	 * le chemin de l'image icone
	 */
	private String pathIcon;
	
	/**
	 * boolean redimensionnable
	 */
	private boolean resizable = true;
	
	/**
	 * boolean decoration
	 */
	private boolean decorating = true;
	
	
	/**
	 * methode permettant de realiser les actions d'ouverture
	 */
	public abstract void starting();
	
	/**
	 * methode permettant de realiser les actions de fermeture
	 */
	public abstract void finishing();
		
	/**
	 * methode permettant de creer la fenetre
	 */
	public void create() {	
		starting();
		
		Scene scene = UtilFX.createSceneFXML(fxmlLoader);
		stage = new Stage();
		stage.setScene(scene);
		if (pathIcon != null) {
			stage.getIcons().add(UtilFX.createImage(pathIcon));
		}
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				finishing();
				Controler controler = (Controler) UtilFX.getControler(fxmlLoader);
				if (controler != null) {
					controler.screenClose();
				}
			}
		});
		
		for (URL urlCSS : listCSS) {
			UtilFX.addCSS(stage, urlCSS);
		}
		
		stage.setTitle(title);
		resizable(this.resizable);
		decorating(this.decorating);
	}
	
	/**
	 * methode permettant d'afficher la fenetre
	 */
	public void show() {
		if (! isOpen()) {
			create();
		}
		stage.show();
	}
	
	/**
	 * methode permettant de fermer la fenetre
	 */
	public void close() {
		if (stage != null) {
			stage.close();
		}
	}
	
	/**
	 * get node
	 * @param node the node contained in stage
	 * @return the node | null if element not found
	 */
	public Node node(String node) {
		if (stage != null) {
			Scene scene = this.stage.getScene();
			if (scene != null) {
				Node root = scene.getRoot();
				if (root != null) {
					return root.lookup("#" + node);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * get stage
	 * @return the stage
	 */
	public Stage stage() {
		return this.stage;
	}
	
	/**
	 * get controler
	 * @return the controler
	 */
	public Object controler() {
		return UtilFX.getControler(this.fxmlLoader);
	}
	
	/**
	 * methode permettant de changer le titre
	 * @param title le titre
	 */
	public void changeTitle(String title) {
		this.title = title;
	}
	
	/**
	 * change path of file fxml
	 * @param urlFXML the url of fxml file
	 */
	public void changeFXML(URL urlFXML) {
		this.fxmlLoader = UtilFX.createLoaderFXML(urlFXML);
	}
	
	/**
	 * methode permettant de choisir si on veut que la fenetre d'application soit redimensionnable
	 * @param b boolean indiquant notre choix
	 */
	private void resizable(boolean b) {
		stage.setResizable(b);
	}
	
	/**
	 * methode permettant de choisir si on veut que la fenetre soit décoré
	 * @param b boolean indiquant notre choix
	 */
	private void decorating(boolean b) {
		if (! b) {
			stage.initStyle(StageStyle.UNDECORATED);	
		} else {
			stage.initStyle(StageStyle.DECORATED);
		}
	}
	
	/**
	 * add css style to screen
	 * @param urlCSS the url of css
	 */
	public void addCSS(URL urlCSS) {
		if (!this.listCSS.contains(urlCSS)) {
			this.listCSS.add(urlCSS);	
		}
	}
		
	/**
	 * methode permettant de vider les chemin css
	 */
	public void clearCSS() {
		this.listCSS.clear();
	}
	
	/**
	 * methode permettant de changer le chemin icon
	 * @param pathIcon le chemin icon
	 */
	public void changeIcon(String pathIcon) {
		this.pathIcon = pathIcon;
	}
	
	/**
	 * methode permettant de savoir si la fenetre est ouverte
	 * @return boolean
	 */
	public boolean isOpen() {
		if (stage != null) {
			return stage.isShowing();
		}
		return false;
	}

	/**
	 * setter redimensionnable
	 * @param resizable boolean
	 */
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	/**
	 * setter decoration
	 * @param decorating boolean
	 */
	public void setDecorating(boolean decorating) {
		this.decorating = decorating;
	}
	
	/**
	 * methode permettant d'ouvir un choix de fichier
	 * @param title le titre
	 * @return le chemin du fichier
	 */
	public File openChoiceFile(String title) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		return fileChooser.showOpenDialog(stage);
	}
	
}

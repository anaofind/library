package anaofind.lib.anafx.screen;

import java.io.File;
import java.util.ArrayList;


import anaofind.lib.anafx.util.UtilFX;
import javafx.event.EventHandler;
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
	private String pathFXML;
	
	/**
	 * le titre de la fenetre
	 */
	private String title = "Screen AnaFX";
	
	/**
	 * liste de chemins des fichiers css
	 */
	private ArrayList<String> listPathCSS = new ArrayList<String>();
	
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
		
		Scene scene = UtilFX.createSceneFXML(pathFXML);
		stage = new Stage();
		stage.setScene(scene);
		if (pathIcon != null) {
			stage.getIcons().add(UtilFX.createImage(pathIcon));
		}
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				finishing();
			}
		});
		
		for (String pathCSS : listPathCSS) {
			UtilFX.addCSS(stage, pathCSS);
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
	 * methode permettant de changer le titre
	 * @param title le titre
	 */
	public void changeTitle(String title) {
		this.title = title;
	}
	
	/**
	 * methode permettant de changer le chemin xml
	 * @param pathXML le chemin xml
	 */
	public void changeXML(String pathXML) {
		this.pathFXML = pathXML;
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
	 * methode permettant d'ajouter un chemin css
	 * @param pathCSS le chemin css
	 */
	public void addCSS(String pathCSS) {
		if (!this.listPathCSS.contains(pathCSS)) {
			this.listPathCSS.add(pathCSS);	
		}
	}
	
	/**
	 * methode permettant de supprimer un chemin css
	 * @param pathCSS le chemin css
	 */
	public void removeCSS(String pathCSS) {
		if (this.listPathCSS.contains(pathCSS)) {
			this.listPathCSS.remove(pathCSS);	
		}
	}
	
	/**
	 * methode permettant de vider les chemin css
	 */
	public void clearCSS() {
		this.listPathCSS.clear();
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

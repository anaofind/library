package anaofind.lib.anafx.screen;

import java.net.URL;
import java.util.*;

import anaofind.lib.anafx.controler.Controler;
import anaofind.lib.anafx.util.UtilFX;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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
	 * list of screen listener
	 */
	public List<ScreenListener> screenListeners = new ArrayList<ScreenListener>();
	
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
		
		Scene scene = UtilFX.createSceneFXML(this.fxmlLoader);
		this.stage = new Stage();
		this.stage.setScene(scene);
		if (this.pathIcon != null) {
			this.stage.getIcons().add(UtilFX.createImage(this.pathIcon));
		}
		
		this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				finishing();
				for (ScreenListener screenListener : screenListeners) {
					screenListener.screenClosed();
				}
				screenListeners.clear();
			}
		});
		
		for (URL urlCSS : this.listCSS) {
			UtilFX.addCSS(this.stage, urlCSS);
		}
		
		this.stage.setTitle(this.title);
		this.changeResizable(this.resizable);
		this.changeDecorating(this.decorating);
		
		Controler controler = (Controler) UtilFX.getControler(this.fxmlLoader);
		if (controler != null) {
			this.addScreenListener(controler);
			controler.screenFounded(this);
		}
	}
	
	/**
	 * add screen listener
	 * @param screenListener the screen listener
	 */
	public void addScreenListener(ScreenListener screenListener) {
		this.screenListeners.add(screenListener);
	}
	
	/**
	 * methode permettant d'afficher la fenetre
	 */
	public void show() {
		if (! isOpen()) {
			create();
		}
		this.stage.show();
		for (ScreenListener screenListener : this.screenListeners) {
			screenListener.screenShowed();
		}
	}
	
	/**
	 * methode permettant de fermer la fenetre
	 */
	public void close() {
		if (this.stage != null) {
			this.stage.close();
		}
		for (ScreenListener screenListener : this.screenListeners) {
			screenListener.screenClosed();
		}
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
	 * get node
	 * @param node the node contained in stage
	 * @return the node | null if element not found
	 */
	public Node node(String node) {
		if (this.stage != null) {
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
		if (this.stage != null) {
			this.stage.setTitle(title);
		}
	}
	
	/**
	 * methode permettant de changer le chemin icon
	 * @param pathIcon le chemin icon
	 */
	public void changeIcon(String pathIcon) {
		this.pathIcon = pathIcon;
		if (this.stage != null) {
			this.stage.getIcons().add(UtilFX.createImage(pathIcon));
		}
	}

	/**
	 * change path of file fxml
	 * @param urlFXML the url of fxml file
	 */
	protected void changeFXML(URL urlFXML) {
		this.fxmlLoader = UtilFX.createLoaderFXML(urlFXML);
	}
	
	/**
	 * methode permettant de choisir si on veut que la fenetre d'application soit redimensionnable
	 * @param b boolean indiquant notre choix
	 */
	public void changeResizable(boolean b) {
		this.resizable = b;
		if (this.stage != null) {
			this.stage.setResizable(b);	
		}
	}
	
	/**
	 * methode permettant de choisir si on veut que la fenetre soit décoré
	 * @param b boolean indiquant notre choix
	 */
	public void changeDecorating(boolean b) {
		this.decorating = b;
		if (this.stage != null) {
			if (! b) {
				this.stage.initStyle(StageStyle.UNDECORATED);	
			} else {
				this.stage.initStyle(StageStyle.DECORATED);
			}	
		}
	}
	
	/**
	 * add css style to screen
	 * @param urlCSS the url of css
	 */
	public void addCSS(URL urlCSS) {
		if (!this.listCSS.contains(urlCSS)) {
			this.listCSS.add(urlCSS);
			if (this.stage != null) {
				UtilFX.addCSS(this.stage, urlCSS);
			}
		}
	}
	
	/**
	 * set fullscreen
	 * @param fullscreen the fullscreen
	 */
	public void setFullscreen(boolean fullscreen) {
		if (this.isFullscreen() != fullscreen) {
			this.stage.setFullScreen(fullscreen);
			if (fullscreen) {
				for (ScreenListener screenListener : this.screenListeners) {
					screenListener.screenBeenFullscreen();
				}	
			} else {
				for (ScreenListener screenListener : this.screenListeners) {
					screenListener.screenBeenWindowed();
				}
			}	
		}
	}
	
	/**
	 * is fullscreen
	 * @return boolean : true if fullscreen | false else
	 */
	public boolean isFullscreen() {
		return this.stage.isFullScreen();
	}
	
	/**
	 * get size of screen [width, height] 
	 * @return the size of screen [width, height] 
	 */
	public double[] size() {
		if (this.stage != null) {
			double width = this.stage.getWidth();
			double height = this.stage.getHeight();
			return new double[] {width, height};
		}
		return new double[] {-1, -1};
	}
}

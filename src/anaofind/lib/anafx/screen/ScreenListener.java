package anaofind.lib.anafx.screen;

/**
 * screen listener
 * @author anaofind
 */
public interface ScreenListener {
	
	/**
	 * been show
	 */
	public void screenShowed();
	
	/**
	 * closed
	 */
	public void screenClosed();
	
	/***
	 * been fullscreen
	 */
	public void screenBeenFullscreen();
	
	/**
	 * been windowed
	 */
	public void screenBeenWindowed();
}

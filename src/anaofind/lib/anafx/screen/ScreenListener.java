package anaofind.lib.anafx.screen;

/**
 * screen listener
 * @author anaofind
 */
public interface ScreenListener {
	
	/**
	 * been show
	 */
	public void showed();
	
	/**
	 * closed
	 */
	public void closed();
	
	/***
	 * been fullscreen
	 */
	public void beenFullscreen();
	
	/**
	 * been windowed
	 */
	public void beenWindowed();
}

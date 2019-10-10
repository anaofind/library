package anaofind.lib.anaofile.listenable;

import anaofind.lib.analistener.Listenable;

/**
 * classe permettant de rendre un fichier observable
 * @author anaofind
 *
 */
public abstract class FileListenable extends Listenable{

	/**
	 * la valeur de la progression de lecture
	 */
	private double progressValue = 0;
	
	/**
	 * methode permettant de recuperer la taille du fichier
	 * @return la taille du fichuer
	 */
	public abstract double getLengthDatas();
	
	/**
	 * methode permettant d'initialiser la progression
	 */
	public void initProgress() {
		this.progressValue = 0;
	}
	
	/**
	 * methode permettant d'incrementer la valeur de progression
	 * @param incr la valeur à incrementer
	 */
	public void incrementProgress(double incr) {
		if (incr > 0) {
			progressValue += incr;	
			if (progressValue > getLengthDatas()) {
				progressValue = 1;
			}
		}
		
	}
	
	/**
	 * methode permettant de recuperer la valeur de progression
	 * @return la valeur de progression
	 */
	public double getProgressValue() {
		return this.progressValue/this.getLengthDatas();
	}
	
}

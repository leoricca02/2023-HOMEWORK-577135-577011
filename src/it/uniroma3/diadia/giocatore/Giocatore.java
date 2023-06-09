package it.uniroma3.diadia.giocatore;

/**
 * Classe che gestisce i CFU e memorizza gli attrezzi di un giocatore
 * @author franc
 * @see Borsa
 * @version base
 */
public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
	 this.cfu = CFU_INIZIALI;
	 this.borsa= new Borsa();
	}
	
	public void setCfu(int cfu) {
		this.cfu=cfu;
	}
	
	public Borsa getBorsa() {
		return borsa;
	}
	
	public int getCfu() {
		return this.cfu;
	}
	
	
	
	
}

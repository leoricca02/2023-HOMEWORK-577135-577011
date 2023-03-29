package it.uniroma3.diadia.giocatore;

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;

	public Giocatore() { 
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	public int getCfu() {
		return this.cfu;
	}

	public Borsa getBorsa() {
		return borsa;
	}
}


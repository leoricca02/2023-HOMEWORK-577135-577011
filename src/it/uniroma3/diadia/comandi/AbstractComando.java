package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	
	@SuppressWarnings("unused")
	private String nome = "AbstractComando";
	private String parametro;
	
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	@Override
	public abstract void esegui(Partita partita);
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	/*@Override
	public void setIO(IO io) {
		this.io = io;
	}*/
}

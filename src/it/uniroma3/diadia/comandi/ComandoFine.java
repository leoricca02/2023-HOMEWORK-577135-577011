package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{
	private String nome;
	private IO io;
	
	
	public ComandoFine() {
		this.io = new IOConsole();
		this.nome = new String("fine");
	}

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return null;	
	}
}

package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{
	private String nome;
	private IO io;
	
	public ComandoFine() {
		this.nome = new String("fine");
	}

	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		this.io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	@Override
	public String getNome() {
		return this.nome;
		
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
}
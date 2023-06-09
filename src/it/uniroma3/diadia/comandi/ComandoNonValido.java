package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {
	private IO io;
	private String nome;
	
	public ComandoNonValido() {
		this.nome = new String("nonValido");
	}

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Riprova");		
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
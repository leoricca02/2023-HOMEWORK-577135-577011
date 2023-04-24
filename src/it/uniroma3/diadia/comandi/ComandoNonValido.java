package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private IO io;
	private String nome;
	
	public ComandoNonValido() {
		this.io = new IOConsole();
		this.nome = new String("nonValido");
	}

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Il comando non è valido, riprova");		
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

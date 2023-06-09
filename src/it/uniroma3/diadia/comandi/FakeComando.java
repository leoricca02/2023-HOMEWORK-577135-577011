package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class FakeComando extends AbstractComando {
	
	@SuppressWarnings("unused")
	private IO io;
	private String nome;
	@SuppressWarnings("unused")
	private String parametro;
	
	public FakeComando(String nome, String parametro) {
		this.io = new IOConsole();
		this.nome = nome;
		this.parametro = parametro;
	}
	

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void esegui(Partita partita) {
		this.nome = "VaiCorretto";
		this.setParametro("Norde");
		partita.setFinita();
	}


	@Override
	public void setIO(IO io) {
		this.io = io;
		
	}

}

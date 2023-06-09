package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
	private IO io;
	private String nome;
	private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda","interagisci","saluta", "regala"};
	
	public ComandoAiuto() {
		this.nome = new String("aiuto");
	}

	@Override
	public void esegui(Partita partita) {
		for(int i = 0; i<elencoComandi.length; i++) {
			this.io.mostraMessaggio(elencoComandi[i]+" ");
		}	
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

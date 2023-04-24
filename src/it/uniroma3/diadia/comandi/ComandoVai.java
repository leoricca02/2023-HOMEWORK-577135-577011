package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {
	private String direzione;
	private IO io;
	private String nome;

	public ComandoVai(String direzione) {
		this.direzione = direzione;		
		this.io = new IOConsole();
		this.nome = new String("vai");
	}

	/**
	 * esecuzione del comando
	 */

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;

		if (direzione == null) {
			this.io.mostraMessaggio("Dove vuoi andare? " + "Devi specificare una direzione");
			return;
		}
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.io.mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return direzione;	
	}
}

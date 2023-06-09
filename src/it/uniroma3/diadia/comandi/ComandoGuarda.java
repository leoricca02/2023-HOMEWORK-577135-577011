package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	private IO io;
	private String nome;
	
	
	public ComandoGuarda() {
		this.io = new IOConsole();
		this.nome = new String("guarda");
	}

	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.io.mostraMessaggio("Facciamo l'unboxing della tua borsa! " + partita.getGiocatore().getBorsa().getContenutoOrdinatoPerNome());
		this.io.mostraMessaggio("Quanti cfu ti sono rimasti? " + partita.getGiocatore().getCfu() + "cfu");
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

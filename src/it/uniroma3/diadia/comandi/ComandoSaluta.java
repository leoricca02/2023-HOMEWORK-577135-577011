package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando{


	private IO io;
	private String nome;
	private static final String MSG_CHI = "Chi dovrei salutare?...";

	public ComandoSaluta() {
		this.nome = "saluta";
	}


	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().getPersonaggio()!= null) {
			String saluto = "Ciao! Sono uno studente, tu chi sei?";
			io.mostraMessaggio(saluto);
			io.mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		}
		else {
			io.mostraMessaggio(MSG_CHI);
		}
	}
}

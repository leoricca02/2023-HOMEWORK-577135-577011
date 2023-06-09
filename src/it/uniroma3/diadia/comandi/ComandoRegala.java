package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{

	private String nome;
	private String attrezzoDaRegalare;
	private IO io;

	public ComandoRegala(String nome) {
		this.nome = "regala";
		this.attrezzoDaRegalare = nome;
	}

	public ComandoRegala() {
		this.nome = "regala";
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
		if(this.attrezzoDaRegalare==null)
			this.io.mostraMessaggio("Devi specificare l'attrezzo");
		else {
			Attrezzo daRegalare = partita.getGiocatore().getBorsa().getAttrezzo(this.attrezzoDaRegalare);
			if(daRegalare==null)
				io.mostraMessaggio("Non possiedi questo attrezzo...");
			else {
				if(partita.getStanzaCorrente().getPersonaggio()!=null) 
					partita.getStanzaCorrente().getPersonaggio().riceviRegalo(daRegalare, partita);
				else
					io.mostraMessaggio("A chi dovrei regalarlo?...");
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzoDaRegalare = parametro;
	}



}

package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza {
	private String attrezzoPerVedere;

	public StanzaBuia(String nome, String attrezzoPerVedere) {
		super(nome);
		this.attrezzoPerVedere = attrezzoPerVedere;
	}
	
	@Override
	public String getDescrizione() {
		if (this.attrezzi.containsKey(attrezzoPerVedere)) {
			return this.toString();
		} else {
			return "qui c'è buio pesto";
		}	
	}	
}

package it.uniroma3.diadia.ambienti;

import java.util.Map;
import java.util.TreeMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto{

	private Labirinto labirinto;
	private Map<String, Stanza> stanze;
	private Stanza ultima;


	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.stanze = new TreeMap<String,Stanza>();
		this.ultima = null;
	}



	public LabirintoBuilder addStanzaIniziale(String nome) {
		Stanza stanza = new Stanza(nome);
		this.labirinto.setStanzaIniziale(stanza);
		this.stanze.put(nome,stanza);
		this.ultima = stanza;
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String nome) {
		/*verifico che la stanza ricevuta da impostare come vincente esista già; se esiste
		 * la imposto sennò ne creo una nuova e la aggiungo*/
		if(this.stanze.containsKey(nome)) {
			Stanza stanza = this.stanze.get(nome);
			this.labirinto.setStanzaFinale(stanza);
		}
		else {
			Stanza stanza = new Stanza(nome);
			this.labirinto.setStanzaFinale(stanza);
			this.stanze.put(nome, stanza);
			this.ultima = stanza;
		}
		return this;
	}

	public LabirintoBuilder addStanza(String nome) {
			Stanza stanza = new Stanza(nome);
			this.stanze.put(nome, stanza);
			this.ultima = stanza;
			return this;
	}

	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Stanza stanza = this.stanze.get(this.ultima.getNome());
		stanza.addAttrezzo(nome, peso);
		return this;
	}


	public LabirintoBuilder addAdiacenza(String s1, String s2, String direzione) {
		Stanza stanza1 = this.stanze.get(s1);
		Stanza stanza2 = this.stanze.get(s2);
		stanza1.impostaStanzaAdiacente(direzione, stanza2);
		return this;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public Map<String, Stanza> getListaStanze() {
		return this.stanze;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		Stanza stanza = new StanzaMagica(nome,soglia);
		this.stanze.put(nome, stanza);
		this.ultima = stanza;
		return this;
}

public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		Stanza stanza = new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante);
		this.stanze.put(nome, stanza);
		this.ultima = stanza;
		return this;
	}

public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
		Stanza stanza = new StanzaBuia(nome, attrezzoPerVedere);
		this.stanze.put(nome, stanza);
		this.ultima = stanza;
		return this;
}
}

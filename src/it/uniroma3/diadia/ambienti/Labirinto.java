package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella un labirinto
 *
 * @author  Leonardo Ricca --> matricola 577011
 * @see Stanza
 * @version base
 */

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;

	public Labirinto() {
		creaStanze();
	}

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	private void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		//StanzaBuia stanzaBuia = new StanzaBuia("stanza buia");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
	

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		setStanzaIniziale(atrio);

		//il gioco finisce in biblioteca
		setStanzaFinale(biblioteca);
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaFinale() {
		return stanzaFinale;
	}

	public void setStanzaFinale(Stanza stanzaFinale) {
		this.stanzaFinale = stanzaFinale;
	}
}

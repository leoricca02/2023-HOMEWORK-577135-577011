package it.uniroma3.diadia;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoGuarda;

class ComandoGuardaTest {
	private ComandoGuarda comando;
	private Stanza stanza1;
	private Partita partita;
	private Stanza stanza2;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	

	@BeforeEach
	void setUp(){
		this.partita = new Partita();
		this.stanza1 = new Stanza("stanza1");
		this.partita.setStanzaCorrente(stanza1);
		this.stanza1.impostaStanzaAdiacente("nord", stanza2);
		this.attrezzo1 = new Attrezzo("osso", 1);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo1);
		this.attrezzo2 = new Attrezzo("lanterna", 4);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo2);
		this.partita.getGiocatore().getCfu();
	}

	@Test
	void testGuarda() {
		this.comando = new ComandoGuarda();
		comando.esegui(partita);
	}

}

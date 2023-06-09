package it.uniroma3.diadia.comandi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoGuardaTest {
	private ComandoGuarda comando;
	private Stanza stanza1;
	private Partita partita;
	private Stanza stanza2;
	private Attrezzo attrezzo2;
	private Labirinto labirinto;


	@BeforeEach
	void setUp(){
		this.partita = new Partita( labirinto);
		this.stanza1 = new Stanza("stanza1");
		this.partita.setStanzaCorrente(stanza1);
		this.stanza1.impostaStanzaAdiacente("nord", stanza2);
		this.partita.getStanzaCorrente().addAttrezzo("osso",1);
		this.attrezzo2 = new Attrezzo("lanterna", 4);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo2);
		this.partita.getGiocatore().getCfu();
	}

	@Test
	void testGuarda() {
		this.comando = new ComandoGuarda();
		this.comando.setIO(new IOConsole());
		comando.esegui(partita);
	}

}
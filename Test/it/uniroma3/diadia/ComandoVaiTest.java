package it.uniroma3.diadia;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {
	private Stanza stanza1;
	private Stanza stanza2;
	private ComandoVai comando;
	private Partita partita;
	
	
	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
		this.stanza1 = new Stanza("stanza1");
		this.stanza2 = new Stanza("stanza 2");
		this.partita.setStanzaCorrente(stanza1);
		this.stanza1.impostaStanzaAdiacente("Nord", stanza2);
	}

	@Test
	final void testDirezioneValida() {
		this.comando = new ComandoVai("Nord");
		comando.esegui(partita);
		//assertEquals(stanza2, this.partita.getStanzaCorrente());
	}
	@Test
	final void testDirezioneNonValida() {
		this.comando = new ComandoVai("Sud");
		comando.esegui(partita);
	}
	@Test
	final void testNessunaDirezione() {
		this.comando = new ComandoVai(null);
		comando.esegui(partita);
	}
}

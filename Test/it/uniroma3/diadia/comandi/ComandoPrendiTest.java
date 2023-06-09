package it.uniroma3.diadia.comandi;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoPrendiTest {
	private ComandoPrendi comando;
	private Partita partita;
	private Stanza stanza1;

	private Labirinto labirinto;
	
	@BeforeEach
	void setUp(){
		this.partita = new Partita(labirinto);
		this.stanza1 = new Stanza("stanza1");
		this.stanza1.addAttrezzo("osso",0);
		this.partita.setStanzaCorrente(stanza1);
	}

	@Test
	void testAttrezzoNull() {
		this.comando = new ComandoPrendi(null);
		this.comando.setIO(new IOConsole());
		comando.esegui(partita);
	}
	@Test
	void testAttrezzoValido() {
		this.comando = new ComandoPrendi("osso");
		this.comando.setIO(new IOConsole());
		comando.esegui(partita);
	}
	@Test
	void testAttrezzoNonValido() {
		this.comando = new ComandoPrendi("libro");
		this.comando.setIO(new IOConsole());
		comando.esegui(partita);
	}
}



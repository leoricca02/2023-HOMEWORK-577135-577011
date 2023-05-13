package it.uniroma3.diadia.comandi;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {
	private ComandoPrendi comando;
	private Partita partita;
	private Stanza stanza1;
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp(){
		this.labirinto = new LabirintoBuilder();
		this.partita = new Partita(labirinto);
		this.stanza1 = new Stanza("stanza1");
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getStanzaCorrente().addAttrezzo("osso", 0);
	}

	@Test
	final void testAttrezzoNull() {
		this.comando = new ComandoPrendi(null);
		comando.esegui(partita);
	}
	@Test
	final void testAttrezzoValido() {
		this.comando = new ComandoPrendi("osso");
		comando.esegui(partita);
	}
	@Test
	final void testAttrezzoNonValido() {
		this.comando = new ComandoPrendi("nonvalido");
		comando.esegui(partita);
	}
	

}

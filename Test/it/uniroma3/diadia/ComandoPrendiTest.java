package it.uniroma3.diadia;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {
	private ComandoPrendi comando;
	private Partita partita;
	private Stanza stanza1;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp(){
		this.partita = new Partita();
		this.stanza1 = new Stanza("stanza1");
		this.attrezzo = new Attrezzo("osso", 0);
		this.partita.setStanzaCorrente(stanza1);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
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

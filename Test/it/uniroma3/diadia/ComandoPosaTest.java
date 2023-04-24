package it.uniroma3.diadia;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {
	private ComandoPosa comando;
	private Partita partita;
	private Stanza stanza1;
	private Attrezzo attrezzo;

	
	
	@BeforeEach
	public void setUp(){
		this.partita = new Partita();
		this.stanza1 = new Stanza("stanza1");
		this.attrezzo = new Attrezzo("osso", 0);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		this.partita.setStanzaCorrente(stanza1);
	}

	@Test
	final void testAttrezzoValido() {
		this.comando = new ComandoPosa("osso");
		comando.esegui(partita);
	}
	
	@Test
	final void testAttrezzoNonValido() {
		this.comando = new ComandoPosa("nonvalido");
		comando.esegui(partita);
	}
	
	@Test
	final void testAttrezzoNull() {
		this.comando = new ComandoPosa(null);
		comando.esegui(partita);
	}
	

}

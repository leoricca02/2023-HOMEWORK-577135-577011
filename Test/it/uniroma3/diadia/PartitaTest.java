package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {

	private Partita partita;
	private Giocatore giocatore;
	private Stanza stanzaCorrente1;
	private Stanza stanzaCorrente2;
	private Stanza stanzaCorrente3;
	private Stanza stanzaVincente;
	private Labirinto labirinto;

	@BeforeEach
	public void setUp() {

		//inizializz partita
		this.partita = new Partita(labirinto);
		//inizializzo giocatore
		this.giocatore = new Giocatore();
		//inizializzo le stanze
		this.stanzaCorrente1 = new Stanza("stanza corrente1");
		this.stanzaCorrente2 = new Stanza("stanza corrente2");
		this.stanzaCorrente3 = new Stanza("stanza corrente3");
		this.stanzaVincente = new Stanza("stanza vincente");

	}
	//get stanza corrente con stanza corrente1
	@Test
	final void testGetStanzaCorrente1() {
		this.partita.setStanzaCorrente(stanzaCorrente1);
		assertEquals(stanzaCorrente1, this.partita.getStanzaCorrente());
	}

	//get stanza corrente con stanza corrente2
	@Test
	final void testGetStanzaCorrente2() {
		this.partita.setStanzaCorrente(stanzaCorrente2);
		assertEquals(stanzaCorrente2, this.partita.getStanzaCorrente());
	}

	//get stanza corrente con stanza corrente3
	@Test
	final void testGetStanzaCorrente3() {
		this.partita.setStanzaCorrente(stanzaCorrente3);
		assertEquals(stanzaCorrente3, this.partita.getStanzaCorrente());
	}

	//vinta con due stanze vincenti
	@Test
	final void testVinta() {
		this.partita.setStanzaCorrente(stanzaVincente);
		this.partita.setStanzaVincente(stanzaVincente);
		assertTrue(this.partita.vinta());
	}

	//vinta con stanza corrente1 e stanza vincente
	@Test
	final void testNonVinta() {
		this.partita.setStanzaCorrente(stanzaCorrente1);
		this.partita.setStanzaVincente(stanzaVincente);
		assertFalse(this.partita.vinta());
	}

	//vinta con stanza vincente e stanza corrente1
	@Test
	final void testNonVinta2() {
		this.partita.setStanzaCorrente(stanzaVincente);
		this.partita.setStanzaVincente(stanzaCorrente1);
		assertFalse(this.partita.vinta());
	}

	//isFinita con cfu del giocatore = 0
	@Test
	final void testIsFinitaZeroCfu() {
		this.giocatore.setCfu(0);
		assertFalse(this.partita.isFinita());
	}

	//isFinita con il metodo vinta che ritorna false
	@Test
	final void testIsFinitaNonVinta() {
		this.partita.setStanzaCorrente(stanzaVincente);
		this.partita.setStanzaVincente(stanzaCorrente1);
		this.partita.vinta();
		assertFalse(this.partita.isFinita());
	}
	
	//isFinita con il metodo vinta che ritorna true e con cfu del giocatore > 0
		@Test
		final void testIsFinita() {
			this.partita.setStanzaCorrente(stanzaVincente);
			this.partita.setStanzaVincente(stanzaVincente);
			this.partita.vinta();
			this.giocatore.setCfu(1);
			assertTrue(this.partita.isFinita());
		}

}
package it.uniroma3.diadia.ambienti;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanzaPiena;
	private Stanza stanzaSemiPiena;
	private Stanza stanzaVuota;


	@BeforeEach
	public void setUp() {

		// inizializzo le stanze
		this.stanzaPiena = new Stanza("piena");
		this.stanzaSemiPiena = new Stanza("semipiena");
		this.stanzaVuota = new Stanza ("vuota");
		this.stanzaPiena.impostaStanzaAdiacente("nord", stanzaSemiPiena);
		this.stanzaPiena.impostaStanzaAdiacente("sud", stanzaSemiPiena);
		this.stanzaPiena.impostaStanzaAdiacente("est", stanzaSemiPiena);
		this.stanzaPiena.addAttrezzo("osso", 1);
		this.stanzaPiena.addAttrezzo("libro", 2);
		this.stanzaPiena.addAttrezzo("luce", 3);
		this.stanzaPiena.addAttrezzo("piccone", 4);
		this.stanzaPiena.addAttrezzo("zappa", 5);
		this.stanzaPiena.addAttrezzo("pala", 6);
		this.stanzaPiena.addAttrezzo("ascia", 7);
		this.stanzaPiena.addAttrezzo("flint", 8);
		this.stanzaPiena.addAttrezzo("TNT", 9);
		this.stanzaPiena.addAttrezzo("tree", 11);
		this.stanzaSemiPiena.addAttrezzo("osso", 1);
		this.stanzaSemiPiena.addAttrezzo("libro", 2);
		this.stanzaSemiPiena.addAttrezzo("luce", 3);
		this.stanzaSemiPiena.addAttrezzo("piccone", 4);
		this.stanzaSemiPiena.addAttrezzo("zappa", 5);
	}

	//get stanza adiacente con le 3 direzioni cardinali scelte
	@Test
	final void testGetStanzaAdiacenteNord() {
		assertEquals(stanzaSemiPiena, this.stanzaPiena.getStanzaAdiacente("nord"));
	}
	@Test
	final void testGetStanzaAdiacenteSud() {
		assertEquals(stanzaSemiPiena, this.stanzaPiena.getStanzaAdiacente("sud"));
	}
	@Test
	final void testGetStanzaAdiacenteEst() {
		assertEquals(stanzaSemiPiena, this.stanzaPiena.getStanzaAdiacente("est"));
	}
	@Test
	final void testAddStanzaAdiacentePiena() {
		this.stanzaPiena.impostaStanzaAdiacente("ovest", stanzaSemiPiena);
		this.stanzaPiena.impostaStanzaAdiacente("nord-est", stanzaSemiPiena);
		assertNull(this.stanzaPiena.getStanzaAdiacente("nord-est"));
	}

	//add attrezzo piena
	@Test
	final void testAddAttrezzoPiena() {
		assertFalse(this.stanzaPiena.addAttrezzo("tree", 11));
	}

	//add attrezzo semipiena attrezzo già presente
	@Test
	final void testAddAttrezzoSemiPienaAttrezzoGiaPresente() {
		assertTrue(this.stanzaSemiPiena.addAttrezzo("osso", 1));
	}

	//add attrezzo semipiena attrezzo non presente
	@Test
	final void testAddAttrezzoSemiPienaAttrezzoNonPresente() {
		assertTrue(this.stanzaSemiPiena.addAttrezzo("tree",11));
	}

	//remove attrezzo semipiena attrezzo presente
	@Test
	final void testRemoveAttrezzoStanzaSemiPienaConAttrezzoPresente() {
		assertTrue(this.stanzaSemiPiena.removeAttrezzo("osso"));
	}

	//remove attrezzo semipiena attrezzo non presente
	@Test
	final void testRemoveAttrezzoStanzaSemiPienaConAttrezzoNonPresente() {
		assertFalse(this.stanzaSemiPiena.removeAttrezzo("tree"));
	}

	// remove attrezzo vuota con attrezzo, ovviamente, non presente
	@Test
	final void testRemoveAttrezzoStanzaVuotaConAttrezzoOvviamenteNonPresente() {
		assertFalse(this.stanzaVuota.removeAttrezzo("tree"));
	}

	//has attrezzo semipiena attrezzo presente
	@Test
	final void testHasAttrezzoStanzaSemiPienaAttrezzoPresente() {
		assertTrue(this.stanzaSemiPiena.attrezzi.containsKey("osso"));
	}

	//has attrezzo semipiena attrezzo non presente
	@Test
	final void testHasAttrezzoStanzaSemiPienaAttrezzoNonPresente() {
		assertFalse(this.stanzaSemiPiena.attrezzi.containsKey("tree"));
	}

	//has attrezzo vuota attrezzo, ovviamente, non presente
	@Test
	final void testHasAttrezzoStanzaVuotaConAttrezzoOvviamenteNonPresente() {
		assertFalse(this.stanzaVuota.attrezzi.containsKey("osso"));
	}

	//get attrezzo vuota attrezzo, ovviamente, non presente
	@Test
	final void testGetAttrezzoStanzaVuotaConAttrezzoOvviamenteNonPresente() {
		assertEquals(null, this.stanzaVuota.attrezzi.get("osso"));
	}

	//get attrezzo semipiena attrezzo presente
	@Test
	final void testGetAttrezzoStanzaSemiPienaAttrezzoPresente() {
		Attrezzo att = new Attrezzo("osso", 1);
		assertEquals(att, this.stanzaSemiPiena.attrezzi.get("osso"));
	}

	//get attrezzo semipiena attrezzo non presente
	@Test
	final void testGetAttrezzoStanzaSemiPienaAttrezzoNonPresente() {
		assertEquals(null, this.stanzaSemiPiena.attrezzi.get("tree"));
	}
}	
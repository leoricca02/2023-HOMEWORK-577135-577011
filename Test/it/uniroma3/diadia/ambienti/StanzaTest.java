package it.uniroma3.diadia.ambienti;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanzaPiena;
	private Stanza stanzaSemiPiena;
	private Stanza stanzaVuota;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Attrezzo attrezzo4;
	private Attrezzo attrezzo5;
	private Attrezzo attrezzo6;
	private Attrezzo attrezzo7;
	private Attrezzo attrezzo8;
	private Attrezzo attrezzo9;
	private Attrezzo attrezzo10;
	private Attrezzo attrezzo11;


	@BeforeEach
	public void setUp() {

		// inizializzo gli attrezzi
		this.attrezzo1 = new Attrezzo("osso", 1);
		this.attrezzo2 = new Attrezzo("libro", 2);
		this.attrezzo3 = new Attrezzo("luce", 3);
		this.attrezzo4 = new Attrezzo("piccone", 4);
		this.attrezzo5 = new Attrezzo("zappa", 5);
		this.attrezzo6 = new Attrezzo("pala", 6);
		this.attrezzo7 = new Attrezzo("ascia", 7);
		this.attrezzo8 = new Attrezzo("flint", 8);
		this.attrezzo9 = new Attrezzo("TNT", 9);
		this.attrezzo10 = new Attrezzo("torcia", 10);
		this.attrezzo11 = new Attrezzo("tree", 11);

		// inizializzo le stanze
		this.stanzaPiena = new Stanza("piena");
		this.stanzaSemiPiena = new Stanza("semipiena");
		this.stanzaVuota = new Stanza ("vuota");
		this.stanzaPiena.impostaStanzaAdiacente("nord", stanzaSemiPiena);
		this.stanzaPiena.impostaStanzaAdiacente("sud", stanzaSemiPiena);
		this.stanzaPiena.impostaStanzaAdiacente("est", stanzaSemiPiena);
		this.stanzaPiena.addAttrezzo(attrezzo1);
		this.stanzaPiena.addAttrezzo(attrezzo2);
		this.stanzaPiena.addAttrezzo(attrezzo3);
		this.stanzaPiena.addAttrezzo(attrezzo4);
		this.stanzaPiena.addAttrezzo(attrezzo5);
		this.stanzaPiena.addAttrezzo(attrezzo6);
		this.stanzaPiena.addAttrezzo(attrezzo7);
		this.stanzaPiena.addAttrezzo(attrezzo8);
		this.stanzaPiena.addAttrezzo(attrezzo9);
		this.stanzaPiena.addAttrezzo(attrezzo10);
		this.stanzaSemiPiena.addAttrezzo(attrezzo1);
		this.stanzaSemiPiena.addAttrezzo(attrezzo2);
		this.stanzaSemiPiena.addAttrezzo(attrezzo3);
		this.stanzaSemiPiena.addAttrezzo(attrezzo4);
		this.stanzaSemiPiena.addAttrezzo(attrezzo5);



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

	//add attrezzo piena
	@Test
	final void testAddAttrezzoPiena() {
		assertFalse(this.stanzaPiena.addAttrezzo(attrezzo11));
	}

	//add attrezzo semipiena attrezzo già presente
	@Test
	final void testAddAttrezzoSemiPienaAttrezzoGiaPresente() {
		assertTrue(this.stanzaSemiPiena.addAttrezzo(attrezzo1));
	}

	//add attrezzo semipiena attrezzo non presente
	@Test
	final void testAddAttrezzoSemiPienaAttrezzoNonPresente() {
		assertTrue(this.stanzaSemiPiena.addAttrezzo(attrezzo11));
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
		assertTrue(this.stanzaSemiPiena.hasAttrezzo("osso"));
	}

	//has attrezzo semipiena attrezzo non presente
	@Test
	final void testHasAttrezzoStanzaSemiPienaAttrezzoNonPresente() {
		assertFalse(this.stanzaSemiPiena.hasAttrezzo("tree"));
	}

	//has attrezzo vuota attrezzo, ovviamente, non presente
	@Test
	final void testHasAttrezzoStanzaVuotaConAttrezzoOvviamenteNonPresente() {
		assertFalse(this.stanzaVuota.hasAttrezzo("osso"));
	}

	//get attrezzo vuota attrezzo, ovviamente, non presente
	@Test
	final void testGetAttrezzoStanzaVuotaConAttrezzoOvviamenteNonPresente() {
		assertEquals(null, this.stanzaVuota.getAttrezzo("osso"));
	}

	//get attrezzo semipiena attrezzo presente
	@Test
	final void testGetAttrezzoStanzaSemiPienaAttrezzoPresente() {
		assertEquals(attrezzo1, this.stanzaSemiPiena.getAttrezzo("osso"));
	}

	//get attrezzo semipiena attrezzo non presente
	@Test
	final void testGetAttrezzoStanzaSemiPienaAttrezzoNonPresente() {
		assertEquals(null, this.stanzaSemiPiena.getAttrezzo("tree"));
	}
}

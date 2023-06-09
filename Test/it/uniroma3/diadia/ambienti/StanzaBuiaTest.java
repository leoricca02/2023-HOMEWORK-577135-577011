package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StanzaBuiaTest {
	
	private StanzaBuia stanza;
	
	@BeforeEach
	void setUp() {
		stanza = new StanzaBuia("stanza buia","osso");
	}

	@Test
	void testSenzaAttrezzoCercato() {
		assertEquals("Qui c'è buio pesto", this.stanza.getDescrizione());
		System.out.println(this.stanza.getDescrizione());
	}
	
	@Test
	void testConAttrezzoCercato() {
		this.stanza.addAttrezzo("osso",1);
		this.stanza.getDescrizione();
		System.out.println(this.stanza.getDescrizione());
	}

}

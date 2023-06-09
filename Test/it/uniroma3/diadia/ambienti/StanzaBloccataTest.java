package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StanzaBloccataTest {

	private StanzaBloccata stanza1;
	private Stanza stanza2;

	@BeforeEach
	void setUp() {
		this.stanza1 = new StanzaBloccata("stanza bloccata", "nord", "osso");
		this.stanza2 = new Stanza("stanza normale");
	}

	@Test
	void testComportamentoNormale() {
		this.stanza1.impostaStanzaAdiacente("sud", stanza2);
		assertEquals(stanza2, this.stanza1.getStanzaAdiacente("sud"));
	}
	
	@Test
	void testDirezioneBloccataSenzaAttrezzo() {
		this.stanza1.impostaStanzaAdiacente("nord", stanza2);
		assertEquals(stanza1, this.stanza1.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testDirezioneBloccataConAttrezzo() {
		this.stanza1.impostaStanzaAdiacente("nord", stanza2);
		this.stanza1.addAttrezzo("osso",1);
		assertEquals(stanza2, this.stanza1.getStanzaAdiacente("nord"));
	}

}

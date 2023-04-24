package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata stanza1;
	private Stanza stanza2;
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp() throws Exception {
		this.stanza1 = new StanzaBloccata("stanza bloccata");
		this.stanza2 = new Stanza("stanza normale");
		this.attrezzo = new Attrezzo ("osso", 1);
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
		this.stanza1.addAttrezzo(attrezzo);
		assertEquals(stanza2
				, this.stanza1.getStanzaAdiacente("nord"));
	}
}

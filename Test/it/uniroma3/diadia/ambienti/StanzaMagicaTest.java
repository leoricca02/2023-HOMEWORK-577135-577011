package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {
	private StanzaMagica stanzaMagica;
	private int sogliaMagica;
	private Attrezzo attrezzo;

	@BeforeEach
	void setUp() throws Exception{
		this.stanzaMagica = new StanzaMagica("stanza");
		this.sogliaMagica = 3;
		this.attrezzo = new Attrezzo("lanterna", 1);
	}

	@Test
	final void testIsNotMagic() {
		assertTrue(this.stanzaMagica.addAttrezzo("lanterna",1));
		assertEquals(this.stanzaMagica.attrezzi.get("lanterna"), this.attrezzo);
	}

	@Test
	final void testAddAttrezzo() {
		assertTrue(this.stanzaMagica.addAttrezzo("lanterna",1));
	}

	@Test
	final void testIsMagic() {
		for(int i=0; i<StanzaMagica.SOGLIA_MAGICA_DEFAULT; i++) {
			assertTrue(this.stanzaMagica.addAttrezzo("attrezzo",1));
		}
		this.stanzaMagica.addAttrezzo("magico",1);
		assertNull(this.stanzaMagica.attrezzi.get("magico"));
		Attrezzo ocigam = this.stanzaMagica.attrezzi.get("ocigam");
		assertNotNull(ocigam);
		assertEquals(new Attrezzo("ocigam", 2), ocigam);
	}

	@Test
	final void testTroppiAttrezzi() {
		for(int i=0; i<StanzaMagica.NUMERO_MASSIMO_ATTREZZI; i++) {
			assertTrue(this.stanzaMagica.addAttrezzo("attrezzo"+i,1));
		}
		assertFalse(this.stanzaMagica.addAttrezzo("lanterna",1));
	}	
}
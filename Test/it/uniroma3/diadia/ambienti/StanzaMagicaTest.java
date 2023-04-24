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
		assertTrue(this.stanzaMagica.addAttrezzo(attrezzo));
		assertSame(this.stanzaMagica.getAttrezzo("lanterna"), this.attrezzo);
	}
	
	@Test
	final void testAddAttrezzo() {
		for(int i=0; i<StanzaMagica.SOGLIA_MAGICA_DEFAULT-1; i++) {
			assertTrue(this.stanzaMagica.addAttrezzo(attrezzo));
		}
		assertTrue(this.stanzaMagica.addAttrezzo(attrezzo));
	}
	
	@Test
	final void testIsMagic() {
		for(int i=0; i<StanzaMagica.SOGLIA_MAGICA_DEFAULT; i++) {
			Attrezzo attrezzoI = new Attrezzo("attrezzo", 1);
			assertTrue(this.stanzaMagica.addAttrezzo(attrezzoI));
		}
		Attrezzo magico = new Attrezzo("magico", 1); 
		this.stanzaMagica.addAttrezzo(magico);
		assertNull(this.stanzaMagica.getAttrezzo("magico"));
		Attrezzo ocigam = this.stanzaMagica.getAttrezzo("ocigam");
		assertNotNull(ocigam);
		assertEquals(new Attrezzo("ocigam", 2), ocigam);
	}
	
	@Test
	final void testTroppiAttrezzi() {
		for(int i=0; i<StanzaMagica.NUMERO_MASSIMO_ATTREZZI; i++) {
			Attrezzo attrezzoI = new Attrezzo("attrezzo"+i, 1);
			assertTrue(this.stanzaMagica.addAttrezzo(attrezzoI));
		}
		assertFalse(this.stanzaMagica.addAttrezzo(attrezzo));
	}	
}

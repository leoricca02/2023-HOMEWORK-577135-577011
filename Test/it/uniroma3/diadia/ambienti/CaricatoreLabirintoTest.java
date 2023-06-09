package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirintoTest {

	private final String monolocale = 
			"Stanze:biblioteca\n"+
			"Magica:\n"+
			"Buia:\n"+
			"Bloccata:\n"+
			"Inizio:biblioteca\n"+
			"Vincente:biblioteca\n"+
			"Mago:\n"+
			"Cane:\n"+
			"Strega:\n"+
			"Attrezzi:\n"+
			"Uscite:\n";

	private final String bilocale = 
			"Stanze:N12,N11\n"+
			"Magica:\n"+
			"Buia:\n"+
			"Bloccata:\n"+
			"Inizio:N12\n"+
			"Vincente:N11\n"+
			"Mago:\n"+
			"Cane:\n"+
			"Strega:\n"+
			"Attrezzi:martello 3 N12\n"+
			"Uscite:\n";
	
	private CaricatoreLabirinto cl;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMonolocale() throws Exception {
		cl = new CaricatoreLabirinto(monolocale);
		cl.carica();
		assertEquals("biblioteca", this.cl.getStanzaIniziale().getNome());
		assertEquals("biblioteca", this.cl.getStanzaVincente().getNome());
		}
	
	@Test
	public void testBilocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(bilocale));
		cl.carica();
		assertEquals("N12", this.cl.getStanzaIniziale().getNome());
		assertEquals("N11", this.cl.getStanzaVincente().getNome());
		}
	
	
	@Test
	public void testBilocaleAttrezzo() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(bilocale));
		cl.carica();
		Attrezzo expected = new Attrezzo("martello", 3);
		assertEquals(expected, this.cl.getStanzaIniziale().getAttrezzo("martello"));
		}
}
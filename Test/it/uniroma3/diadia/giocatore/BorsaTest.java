package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Attrezzo attrezzo4;
	private Attrezzo attrezzo5;


	private Borsa borsaPiena;
	private Borsa borsaVuota;
	private Borsa borsaSemiVuota;
	private Borsa borsaSorted;

	@BeforeEach
	void setUp() {

		/* crea le borse */
		this.borsaPiena = new Borsa();
		this.borsaVuota = new Borsa();
		this.borsaSemiVuota = new Borsa();
		this.borsaSorted = new Borsa();


		/* crea gli attrezzi */
		this.attrezzo1 = new Attrezzo("Piccone", 4);
		this.attrezzo2 = new Attrezzo("Ascia", 10);
		this.attrezzo3 = new Attrezzo("Foglio", 1);
		this.attrezzo4 = new Attrezzo("Penna", 1);
		this.attrezzo5 = new Attrezzo("Calamita", 1);



		/* aggiungi gli attrezzi alle borse */
		this.borsaPiena.addAttrezzo(attrezzo2);


		this.borsaSemiVuota.addAttrezzo(attrezzo1);
		this.borsaSemiVuota.addAttrezzo(attrezzo3);

		this.borsaSorted.addAttrezzo(attrezzo1);
		this.borsaSorted.addAttrezzo(attrezzo3);
		this.borsaSorted.addAttrezzo(attrezzo5);
		this.borsaSorted.addAttrezzo(attrezzo4);
	}

	@Test
	void testAddAttrezzoBorsaVuota() {
		assertEquals(true, this.borsaVuota.addAttrezzo(attrezzo1));
	}

	@Test
	void testAddAttrezzoBorsaPiena() {
		assertEquals(false, this.borsaPiena.addAttrezzo(attrezzo1));
	}

	@Test
	void testAddAttrezzoBorsaSemiPiena() {
		assertEquals(true, this.borsaSemiVuota.addAttrezzo(attrezzo1));
	}

	@Test
	void testRemoveAttrezzoBorsaVuota() {
		assertNull(this.borsaVuota.removeAttrezzo("Piccone"));
	}

	@Test
	void testRemoveAttrezzoUnAttrezzo() {
		assertNotNull(this.borsaPiena.removeAttrezzo("Ascia"));
	}

	@Test
	void testRemoveAttrezzoPiuDiUno() {
		assertNotNull(this.borsaSemiVuota.removeAttrezzo("Foglio"));
	}

	@Test
	void testRemoveAttrezzoBorsaPiena() {
		assertNotNull(this.borsaPiena.removeAttrezzo("Ascia"));
	}

	@Test
	void testRemoveAttrezzoBorsaPienaAttezzoSbagliato() {
		assertNull(this.borsaPiena.removeAttrezzo("Chiave"));
	}

	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		List<Attrezzo> atteso = Arrays.asList(new Attrezzo("Foglio", 1), new Attrezzo("Piccone", 4));
		assertEquals(atteso.toString(), this.borsaSemiVuota.getContenutoOrdinatoPerPeso().toString());
	}

	@Test
	public void testGetContenutoOrdinatoPerNome() {
		Set<Attrezzo> atteso = new TreeSet<>(Arrays.asList(new Attrezzo("Foglio", 1), new Attrezzo("Piccone", 4)));
		assertEquals(atteso.toString(), this.borsaSemiVuota.getContenutoOrdinatoPerNome().toString());
	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> atteso = new HashMap<>();
		atteso.put(1, Collections.singleton(new Attrezzo("Foglio", 1)));
		atteso.put(4, Collections.singleton(new Attrezzo("Piccone", 4)));
		assertEquals(atteso.toString(), this.borsaSemiVuota.getContenutoRaggruppatoPerPeso().toString());
	}

	@Test
	public void testGetSortedSetOrdinatoPerPeso() {
		List<Attrezzo> atteso = Arrays.asList(new Attrezzo("Calamita", 1), new Attrezzo("Foglio", 1),new Attrezzo("Penna", 1), new Attrezzo("Piccone", 4));
		assertEquals(atteso.toString(), this.borsaSorted.getSortedSetOrdinatoPerPeso().toString());
	}
}


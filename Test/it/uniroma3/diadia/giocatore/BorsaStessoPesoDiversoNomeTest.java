package it.uniroma3.diadia.giocatore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaStessoPesoDiversoNomeTest {
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Borsa borsa;
	
	@BeforeEach
	void setUp() {
		this.attrezzo1 = new Attrezzo("suit",1);
		this.attrezzo2 = new Attrezzo("up",1);
		this.borsa = new Borsa();
		this.borsa.addAttrezzo(attrezzo2);
		this.borsa.addAttrezzo(attrezzo1);
	}

	@Test
	void testgetSortedSetOrdinatoPerPeso() {
		System.out.println(this.borsa);
		this.borsa.getSortedSetOrdinatoPerPeso();
		System.out.println(this.borsa.getSortedSetOrdinatoPerPeso());
	}

}

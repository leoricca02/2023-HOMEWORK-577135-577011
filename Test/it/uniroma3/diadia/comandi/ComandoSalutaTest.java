package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

class ComandoSalutaTest {
	
	private Mago mago;
	private Cane doggo;
	private Strega strega;
	private Partita partita;
	private Attrezzo attrezzo;
	private Labirinto lab;
	
	@BeforeEach
	void setUp() throws Exception {
		this.attrezzo = new Attrezzo("Osso",1);
		this.mago = new Mago("Gandalf","Fuggite sciocchi",attrezzo);
		this.doggo = new Cane("Fuffi","Wof Wof");
		this.strega = new Strega("Pina", "Sono permalosa");
		
		this.lab = new LabirintoBuilder()
				.addStanzaIniziale("Lab")
				.addPersonaggio(mago)
				
				.addStanza("Atrio")
				.addPersonaggio(doggo)
				.addAdiacenza("Lab","Atrio","nord")
				
				.addStanza("DS1")
				.addPersonaggio(strega)
				.addAdiacenza("Lab", "DS1", "est")
				
				.addStanza("DS2")
				.addAdiacenza("Lab", "DS2", "ovest")
				.getLabirinto();
				
		this.partita = new Partita(lab);
	}
	

	@Test
	void testSalutaMago() {
		Comando saluto = new ComandoSaluta();
		saluto.setIO(new IOConsole());
		saluto.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().getPersonaggio().haSalutato());
	}
	
	@Test
	void testSalutaCane() {
		Comando c = new ComandoVai("nord");
		c.setIO(new IOConsole());
		c.esegui(partita);
		Comando saluto = new ComandoSaluta();
		saluto.setIO(new IOConsole());
		saluto.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().getPersonaggio().haSalutato());
	}
	
	@Test
	void testSalutaStrega() {
		Comando c1 = new ComandoVai("est");
		c1.setIO(new IOConsole());
		c1.esegui(partita);
		Comando saluto = new ComandoSaluta();
		saluto.setIO(new IOConsole());
		saluto.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().getPersonaggio().haSalutato());
	}
	
	@Test
	void testSalutaNessuno() {
		Comando c1 = new ComandoVai("ovest");
		c1.setIO(new IOConsole());
		c1.esegui(partita);
		Comando saluto = new ComandoSaluta();
		saluto.setIO(new IOConsole());
		saluto.esegui(partita);
		assertNull(this.partita.getStanzaCorrente().getPersonaggio());
	}
	
	
	

}

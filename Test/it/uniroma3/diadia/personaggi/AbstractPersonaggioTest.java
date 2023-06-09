package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

class AbstractPersonaggioTest {
	private Cane cane;
	private Mago magoConAttrezzo;
	private Strega strega;
	private Attrezzo attrezzo;
	private Labirinto labirinto;
	private Partita partita;

	@BeforeEach
	void setUp() throws Exception {
		this.attrezzo = new Attrezzo("lanterna", 3);	
		this.cane = new Cane("cane", " Woff");
		this.magoConAttrezzo = new Mago("mago", " Sono magico", attrezzo);
		this.strega = new Strega("strega", " Sono cattiva");
		this.labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Lab")
				.addPersonaggio(cane)
				.addAttrezzo("osso", 1)
				
				.addStanza("Atrio")
				.addPersonaggio(magoConAttrezzo)
				.addAttrezzo("osso", 1)
				.addAttrezzo("scudo", 1)
				.addAttrezzo("spada", 3)
				.addAdiacenza("Lab", "Atrio", "est")
				.addAdiacenza("Atrio", "Lab", "ovest")
				
				.addStanza("DS1")
				.addAttrezzo("pallone", 3)
				.addAttrezzo("guanti", 1)
				
				.addStanza("DS2")
				.addPersonaggio(strega)
				.addAttrezzo("spada", 3)
				.addAttrezzo("scudo", 1)
				.addAdiacenza("DS2", "Lab", "est")
				.addAdiacenza("Lab", "DS2", "ovest")	
				.addAdiacenza("DS2", "DS1", "nord")
				.addAdiacenza("DS1", "DS2", "sud")

				.addStanzaVincente("Biblioteca")
				.addAttrezzo("spada", 3)
				.addAttrezzo("scudo", 1)
				.addAttrezzo("osso", 1)
				.addAttrezzo("diamante", 1)
				.addAdiacenza("Lab", "Biblioteca", "nord")
				.addAdiacenza("Biblioteca", "Lab", "sud")
				.getLabirinto();
		this.partita = new Partita(labirinto);
	}

	@Test
	void testCaneAgisci() {
		String msg = new String ("Wof! Wof! (tradotto): Andare avanti non potrai più perchè ti rubo i CFU!");
		assertEquals(msg, this.cane.agisci(partita));
		this.cane.agisci(partita);
		this.cane.agisci(partita);
		assertEquals(17, this.partita.getGiocatore().getCfu());
	}
	
	@Test
	void testMagoAgisci() {
		String msgDono = new String ("Sei un vero simpaticone, " +
				"con una mia magica azione, troverai un nuovo oggetto " +
				"per il tuo borsone!");
		assertEquals(msgDono, this.magoConAttrezzo.agisci(partita));
		String msgSenzaDono = new String ("Mi spiace, ma non ho piu' nulla...");
		assertEquals(msgSenzaDono, this.magoConAttrezzo.agisci(partita));
	}
	
	@Test
	void testStregaAgisci() {
		String msgNonSalutato = new String ("Non mi hai salutato e l'educazione"
				+ "non ha prezzo, non ti meriti neanche un attrezzo");
		Comando c = new ComandoVai("ovest");
		c.setIO(new IOConsole());
		c.esegui(partita);
		assertEquals("DS2", partita.getStanzaCorrente().getNome());
		String msgAttuale = this.strega.agisci(partita);
		assertEquals(msgNonSalutato, msgAttuale);
		assertEquals("Lab", partita.getStanzaCorrente().getNome());
		Comando c1 = new ComandoVai("ovest");
		c1.setIO(new IOConsole());
		c1.esegui(partita);
		@SuppressWarnings("unused")
		String saluta = this.strega.saluta();
		String msgSalutato = new String ("Siccome mi hai salutato e non c'è volta "
				+ "che io non lo apprezzi, ti trasferirò nella stanza con più attrezzi");
		assertEquals(msgSalutato, this.strega.agisci(partita));
		assertEquals("DS1", partita.getStanzaCorrente().getNome());
	}

}

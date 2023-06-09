package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

class ComandoRegalaTest {
	private Mago mago;
	private Cane doggo;
	private Strega strega;
	private Partita partita;
	private Attrezzo attrezzo;
	private Labirinto lab;
	private Comando regala;

	@BeforeEach
	void setUp() throws Exception {
		this.attrezzo = new Attrezzo("Osso",2);
		this.mago = new Mago("Gandalf","Fuggite sciocchi",attrezzo);
		this.doggo = new Cane("Fuffi","Wof Wof");
		this.strega = new Strega("Pina", "Sono permalosa");
		this.regala = new ComandoRegala("Osso");
		this.regala.setIO(new IOConsole());
		this.lab = Labirinto.newBuilder("Labirinto1");
				/*.addStanzaIniziale("Lab")
				.addPersonaggio(mago)

				.addStanza("Atrio")
				.addPersonaggio(doggo)
				.addAdiacenza("Lab","Atrio","nord")

				.addStanza("DS1")
				.addPersonaggio(strega)
				.addAdiacenza("Lab", "DS1", "est")

				.addStanza("DS2")
				.addAdiacenza("Lab", "DS2", "ovest")
				.getLabirinto();*/

		this.partita = new Partita(lab);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}

	@Test
	void testRegalaMago() {
		this.regala.esegui(partita);
		String msg = "Ti vedo affaticato! Dimezzerò il peso dell'oggetto che mi hai dato! Ups a terra mi è scivolato!";
		assertEquals(msg,this.partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzo, partita));
		assertNull(this.partita.getGiocatore().getBorsa().getAttrezzo("osso"));
		//assertEquals(new Attrezzo("Osso",1), this.partita.getStanzaCorrente().getAttrezzi().get(0));
		assertTrue(this.partita.getStanzaCorrente().getAttrezzi().contains(new Attrezzo("Osso",1)));
	}

	@Test
	void testRegalaCanePreferito() {
		Comando vai = new ComandoVai("nord");
		vai.setIO(new IOConsole());
		vai.esegui(partita);
		this.regala.esegui(partita);
		String msg = "*Il cane scodinzola e lascia un oggetto per terra*";
		assertEquals(msg,this.partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzo, partita));
		assertNull(this.partita.getGiocatore().getBorsa().getAttrezzo("osso"));
		assertTrue(this.partita.getStanzaCorrente().getAttrezzi().contains(new Attrezzo("Pallina",3)));
	}

	@Test
	void testRegalaCaneNonPreferito() {
		Comando vai = new ComandoVai("nord");
		vai.setIO(new IOConsole());
		vai.esegui(partita);
		Attrezzo regaloBrutto = new Attrezzo("Pesce",4);
		this.partita.getGiocatore().getBorsa().addAttrezzo(regaloBrutto);
		this.regala = new ComandoRegala("Pesce");
		String msg = "GRRRR! (tradotto) Non mi piace!";
		assertEquals(msg,this.partita.getStanzaCorrente().getPersonaggio().riceviRegalo(regaloBrutto, partita));
		assertEquals(regaloBrutto,this.partita.getGiocatore().getBorsa().getAttrezzo("Pesce"));
		assertFalse(this.partita.getStanzaCorrente().getAttrezzi().contains(new Attrezzo("Pallina",3)));
	}

	@Test
	void testRegalaStrega() {
		Comando vai = new ComandoVai("est");
		vai.setIO(new IOConsole());
		vai.esegui(partita);
		this.regala.esegui(partita);
		String msg = "IHIHIHI"+ this.attrezzo.getNome()+ "è davvero ciò che mi hai dato? Pensi davvero che ti verrà riconsegnato?";
		assertEquals(msg,this.partita.getStanzaCorrente().getPersonaggio().riceviRegalo(attrezzo, partita));
		assertNull(this.partita.getGiocatore().getBorsa().getAttrezzo("osso"));
	}
	
	@Test
	 //test giusto
	void testRegalaNessunPersonaggio() {
		Comando c = new ComandoVai("ovest");
		c.setIO(new IOConsole());
		c.esegui(partita);
		Comando c1 = new ComandoRegala("Osso");
		c1.setIO(new IOConsole());
		c1.esegui(partita);
		assertEquals(this.attrezzo,partita.getGiocatore().getBorsa().getAttrezzo("Osso"));
	}


}

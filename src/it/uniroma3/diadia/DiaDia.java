package it.uniroma3.diadia;
import java.io.Console;
import java.util.Scanner;


import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca(IOConsole console) {
		String istruzione; 
		Scanner scannerDiLinee;

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do		
			istruzione = console.leggiRiga();
		while (!processa(istruzione, console));
	}

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */

	private boolean processa(String istruzione, IOConsole console) {
		Comando daEseguire = new Comando(istruzione);

		if (daEseguire.getNome().equals("fine")) {
			this.fine(console); 
			return true;	
		} else if (daEseguire.getNome().equals("vai"))
			this.vai(daEseguire.getParametro(), console);
		else if (daEseguire.getNome().equals("aiuto"))
			this.aiuto(console);
		else if (daEseguire.getNome().equals("prendi"))
			this.prendi(daEseguire.getParametro(), console);
		else if (daEseguire.getNome().equals("posa"))
			this.posa(daEseguire.getParametro(), console);
		else
			console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */

	private void aiuto(IOConsole console) {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+" ");

	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione, IOConsole console) {
		if(direzione==null)
			console.mostraMessaggio("Dove vuoi andare?");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				console.mostraMessaggio("Direzione inesistente");
			else {
				this.partita.setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getGiocatore().getCfu();
				this.partita.getGiocatore().setCfu(cfu--);
			}
		}	
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/** 
	 * Funzione che prende un attrezzo dalla stanza e lo mette nella borsa
	 * @param nomeAttrezzo
	 * @return true se l'azione è avvenuta, false altrimenti
	 */

	private void prendi (String nomeAttrezzo, IOConsole console) {
		if(nomeAttrezzo==null)
			console.mostraMessaggio("Quale attrezzo vuoi prendere?");
		else {
			Attrezzo toTakeAttrezzo = null;
			toTakeAttrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if (toTakeAttrezzo == null)
				console.mostraMessaggio("Attrezzo inesistente");
			else {
				if (this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo))
					this.partita.getGiocatore().getBorsa().addAttrezzo(toTakeAttrezzo);
				console.mostraMessaggio("Attrezzo preso");
			}
		}
	}

	private void posa (String nomeAttrezzo, IOConsole console) {
		if(nomeAttrezzo==null)
			console.mostraMessaggio("Quale attrezzo vuoi posare?");
		else {
			Attrezzo toDropAttrezzo = null;
			toDropAttrezzo = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if (toDropAttrezzo == null)
				console.mostraMessaggio("Attrezzo inesistente");
			else {
				if (this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo) != null)
					this.partita.getStanzaCorrente().addAttrezzo(toDropAttrezzo);
				console.mostraMessaggio("Attrezzo posato");
			}
		}
	}	


	/**
	 * Comando "Fine".
	 */

	private void fine(IOConsole console) {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		IOConsole console = new IOConsole();
		gioco.gioca(console);

	}
}
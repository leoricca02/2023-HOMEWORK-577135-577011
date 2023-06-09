package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso di una singola riga contenente il nome della stanza bloccata */
	private static final String STANZA_BLOCCATA_MARKER = "Bloccata:";  
	
	/* prefisso di una singola riga contenente il nome della stanza bloccata */
	private static final String STANZA_MAGICA_MARKER = "Magica:";  
	
	/* prefisso di una singola riga contenente il nome della stanza bloccata */
	private static final String STANZA_BUIA_MARKER = "Buia:";  
	
	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeMago> <presentazione> <attrezzo> */
	private static final String MAGO_MARKER = "Mago:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeStrega> <presentazione> */
	private static final String STREGA_MARKER = "Strega:";

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeCane> <presentazione> */
	private static final String CANE_MARKER = "Cane:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Bloccata: Atrio Nord Tonno
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws Exception {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiEImpostaUscite();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaMaghi();
			this.leggiECollocaStreghe();
			this.leggiECollocaCani();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.creaLabirinto();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private void creaLabirinto() {

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZA_BLOCCATA_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String direzioneBloccata = null;
			String attrezzoSbloccante = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza"+nomeStanza+"."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata"+direzioneBloccata+"."));
				direzioneBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo sbloccante"+attrezzoSbloccante+"."));
				attrezzoSbloccante = scannerLinea.next();
			}	
			Stanza stanza = new StanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZA_MAGICA_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String sogliaStringa = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza"+nomeStanza+"."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la soglia"+sogliaStringa+"."));
				sogliaStringa = scannerLinea.next();
			}	
			int soglia = Integer.parseInt(sogliaStringa);
			Stanza stanza = new StanzaMagica(nomeStanza, soglia);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZA_BUIA_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String attrezzoPerVedere = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza"+nomeStanza+"."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo per vedere"+attrezzoPerVedere+"."));
				attrezzoPerVedere = scannerLinea.next();
			}	
			Stanza stanza = new StanzaBuia(nomeStanza, attrezzoPerVedere);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			result.add(scannerDiParole.next());
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void leggiECollocaMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGO_MARKER);

		for(String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			String nomeMago = null;
			String presentazioneMago = null;
			String attrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaMago)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del mago "+nomeMago+"."));
				presentazioneMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo del mago "+nomeMago+"."));
				attrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il personaggio "+nomeMago+"."));
				nomeStanza = scannerLinea.next();
			}			
			AbstractPersonaggio mago = new Mago(nomeMago, presentazioneMago, new Attrezzo(attrezzo,2));
			Stanza stanzaAttuale = this.nome2stanza.get(nomeStanza);
			check(stanzaAttuale.getPersonaggio()==null, msgTerminazionePrecoce("La stanza ha già un personaggio!"));
			stanzaAttuale.setPersonaggio(mago);
		}
	}
	
	private void leggiECollocaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(STREGA_MARKER);

		for(String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nomeStrega = null;
			String presentazioneStrega = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaStrega)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di una strega."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione della strega "+nomeStrega+"."));
				presentazioneStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il personaggio "+nomeStrega+"."));
				nomeStanza = scannerLinea.next();
			}			
			AbstractPersonaggio strega = new Strega(nomeStrega, presentazioneStrega);
			Stanza stanzaAttuale = this.nome2stanza.get(nomeStanza);
			check(stanzaAttuale.getPersonaggio()==null, msgTerminazionePrecoce("La stanza ha già un personaggio!"));
			stanzaAttuale.setPersonaggio(strega);
		}
	}
	
	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(CANE_MARKER);

		for(String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			String nomeCane = null;
			String presentazioneCane = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaCane)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del cane "+nomeCane+"."));
				presentazioneCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il personaggio "+nomeCane+"."));
				nomeStanza = scannerLinea.next();
			}			
			AbstractPersonaggio cane = new Cane(nomeCane, presentazioneCane);
			Stanza stanzaAttuale = this.nome2stanza.get(nomeStanza);
			check(stanzaAttuale.getPersonaggio()==null, msgTerminazionePrecoce("La stanza ha già un personaggio!"));
			stanzaAttuale.setPersonaggio(cane);
		}
	}


	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo.getNome(), attrezzo.getPeso());
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next();

				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
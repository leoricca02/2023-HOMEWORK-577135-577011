package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */


public class StanzaProtected {

	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final public int NUMERO_MASSIMO_ATTREZZI = 10;

	protected String nome;
	protected HashSet<Attrezzo> attrezzi;
	//private int numeroAttrezzi;
	protected ArrayList<Stanza> stanzeAdiacenti;
	//private int numeroStanzeAdiacenti;
	protected ArrayList<String> direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */


	public StanzaProtected(String nome) {
		this.nome = nome;
		//this.numeroStanzeAdiacenti = 0;
		//this.numeroAttrezzi = 0;
		this.direzioni = new ArrayList<String>(NUMERO_MASSIMO_DIREZIONI);
		this.stanzeAdiacenti = new ArrayList<Stanza>(NUMERO_MASSIMO_DIREZIONI);
		this.attrezzi = new HashSet<Attrezzo>(NUMERO_MASSIMO_ATTREZZI);
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione -direzione in cui sara' posta la stanza adiacente.
	 * @param stanza -stanza adiacente nella direzione indicata dal primo parametro.
	 */

	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;
		Iterator<Stanza> iS = this.stanzeAdiacenti.iterator();
		Iterator<String> iD = this.direzioni.iterator();
		while(iD.hasNext() && iS.hasNext()) {
			@SuppressWarnings("unused")
			Stanza stanzaAttuale = iS.next();
			String direzioneAttuale = iD.next();
			if(direzione.equals(direzioneAttuale)) {
				iS.remove();
				iD.remove();
				this.direzioni.add(direzione);
				this.stanzeAdiacenti.add(stanza);
				aggiornato = true;
				break;
			}
		}
		if (!aggiornato) {
			//direzioneAttuale = direzione;
			//stanzaAttuale = stanza;
			this.direzioni.add(direzione);
			this.stanzeAdiacenti.add(stanza);
			//this.numeroStanzeAdiacenti++;
		}
	}
	/*for(int i=0; i<this.direzioni.length; i++)
			if (direzione.equals(this.direzioni[i])) {
				this.stanzeAdiacenti[i] = stanza;
				aggiornato = true;
			}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}*/

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		Iterator<Stanza> iS = this.stanzeAdiacenti.iterator();
		Iterator<String> iD = this.direzioni.iterator();
		while(iS.hasNext() && iD.hasNext()) {
			Stanza stanzaAttuale = iS.next();
			String direzioneAttuale = iD.next();
			if(direzioneAttuale.equals(direzione)) {
				return stanzaAttuale;
			}
		}
		/*for(int i=0; i<this.numeroStanzeAdiacenti; i++)
			if (this.direzioni[i].equals(direzione))
				stanza = this.stanzeAdiacenti[i];*/
		return null;
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public HashSet<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.attrezzi.size()< NUMERO_MASSIMO_ATTREZZI)
			return this.attrezzi.add(attrezzo);
		else
			return false;
	}
	/*if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else {
			return false;
		}*/

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		Iterator<String> iD = this.direzioni.iterator();
		Iterator<Attrezzo> iA = this.attrezzi.iterator();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		while(iD.hasNext()) {
			risultato.append(" "+ iD.next());
		}
		risultato.append("\nAttrezzi nella stanza: ");
		while(iA.hasNext()) {
			risultato.append(iA.next().toString()+" ");
		}
		return risultato.toString();
		/*for (int i=0; i<NUMERO_MASSIMO_DIREZIONI; i++) {
			if(direzioni[i]!=null)
				risultato.append(" " + this.direzioni[i]);
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (int j=0; j<this.numeroAttrezzi; j++) {
			risultato.append(attrezzi[j].toString()+" ");
		}
		return risultato.toString();*/
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato = false;
		Iterator<Attrezzo> iA = this.attrezzi.iterator();
		while(iA.hasNext()) {
			if(iA.next().getNome().equals(nomeAttrezzo))
				trovato = true;
		}
		/*for (int i=0; i<this.numeroAttrezzi; i++) {
			if (attrezzi[i].getNome().equals(nomeAttrezzo))
				trovato = true;
		}*/
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato = null;
		Iterator<Attrezzo> iA = this.attrezzi.iterator();
		while(iA.hasNext()) {
			Attrezzo a = iA.next();
			if(a.getNome().equals(nomeAttrezzo))
				attrezzoCercato = a;
		}
		/*for (int i=0; i<this.numeroAttrezzi; i++) {
			if (attrezzi[i].getNome().equals(nomeAttrezzo))
				attrezzoCercato = attrezzi[i];
		}*/
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> iA = this.attrezzi.iterator();
		while (iA.hasNext()) {
			Attrezzo a = iA.next();
			if (a.getNome().equals(nomeAttrezzo)) {
				iA.remove(); // rimuovi l'oggetto tramite l'iteratore
				return true;
			}
		}
		return false;
	}
	/*for(int i=0; i<this.numeroAttrezzi; i++) {
				if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					for(int j=i; j<this.numeroAttrezzi; j++)
						this.attrezzi[i] = this.attrezzi[j+1];
					this.numeroAttrezzi--;
				}
			}
		}
		return fatto; */

	public ArrayList<String> getDirezioni() {
		return this.direzioni;	
	}
	/*for(int i=0; i<this.numeroStanzeAdiacenti; i++)
			direzioni[i] = this.direzioni[i];
		return direzioni;
	}*/
}
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

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final public int NUMERO_MASSIMO_ATTREZZI = 10;

	protected String nome;

	protected HashSet<Attrezzo> attrezzi;
	//protected int numeroAttrezzi;

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
	 * @param direzione in cui sara' posta la stanza adiacente.
	 * @param stanza adiacente nella direzione indicata dal primo parametro.
	 */

	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
	    boolean aggiornato = false;
	    Iterator<Stanza> iS = this.stanzeAdiacenti.iterator();
	    Iterator<String> iD = this.direzioni.iterator();
	    while (iD.hasNext() && iS.hasNext()) {
	        Stanza stanzaAttuale = iS.next();
	        String direzioneAttuale = iD.next();
	        if (direzione.equals(direzioneAttuale)) {
	            iS.remove(); // rimuovi la stanza esistente
	            iD.remove(); // rimuovi la direzione esistente
	            this.direzioni.add(direzione); // aggiungi la nuova direzione
	            this.stanzeAdiacenti.add(stanza); // aggiungi la nuova stanza
	            aggiornato = true;
	            break; // esci dal ciclo poiché la stanza è stata aggiornata
	        }
	    }
	    if (!aggiornato) {
	        this.direzioni.add(direzione); // aggiungi la nuova direzione
	        this.stanzeAdiacenti.add(stanza); // aggiungi la nuova stanza
	    }
	}

	/* public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		boolean aggiornato = false;
		for (int i=0; i<this.direzioni.length; i++)
			if (direzione.equals(this.direzioni[i])) {
				this.stanzeAdiacenti[i] = stanza;
				aggiornato = true;
			}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}
	}*/

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */

	public Stanza getStanzaAdiacente(String direzione) {
		Iterator<String> iteratoreDirezioni = this.direzioni.iterator();
		Iterator<Stanza> iteratoreStanzeAdiacenti = this.stanzeAdiacenti.iterator();
		while (iteratoreDirezioni.hasNext()) {
			Stanza stanzaAdiacente = iteratoreStanzeAdiacenti.next();
			String d = iteratoreDirezioni.next();
			if (d.equals(direzione)) {
				return stanzaAdiacente;
			}
		}
		return null;
	}	

	/*public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
			if (this.direzioni[i].equals(direzione))
				stanza = this.stanzeAdiacenti[i];
		return stanza;
	} */

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
		if (this.attrezzi.size()< NUMERO_MASSIMO_ATTREZZI) {
			return this.attrezzi.add(attrezzo);
		} else {
			return false;
		}
	}	
	
	/*public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else {
			return false;
		}
	}*/

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	
	public String toString() {
		Iterator<String> iteratoreDirezioni = this.direzioni.iterator();
		Iterator<Attrezzo> iteratoreAttrezzi = this.attrezzi.iterator();
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		while(iteratoreDirezioni.hasNext()) {
			String d = iteratoreDirezioni.next();
				risultato.append(" " + d);
		}		
		risultato.append("\nAttrezzi nella stanza: ");
		while(iteratoreAttrezzi.hasNext()) {
			Attrezzo a = iteratoreAttrezzi.next();
			risultato.append(a + " ");
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> iteratoreAttrezzi = this.attrezzi.iterator();
		boolean trovato;
		trovato = false;
		while(iteratoreAttrezzi.hasNext()) {
			Attrezzo a = iteratoreAttrezzi.next();
				if (a.getNome().equals(nomeAttrezzo))
					trovato = true;
		}
		return trovato;
	}
	
	/*public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (int j = 0; j<this.numeroAttrezzi; j++) {
			if(this.attrezzi[j]!=null)
				if (this.attrezzi[j].getNome().equals(nomeAttrezzo))
					trovato = true;
		}
		return trovato;
	}*/

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> iteratoreAttrezzi = this.attrezzi.iterator();
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		while(iteratoreAttrezzi.hasNext()) {
			Attrezzo a = iteratoreAttrezzi.next();
				if (a.getNome().equals(nomeAttrezzo)) {
					return a;
				}	
		}
		return null;	
	}
	
	/*public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (int j = 0; j<this.numeroAttrezzi; j++) {
			if(this.attrezzi[j]!=null)
				if (this.attrezzi[j].getNome().equals(nomeAttrezzo)) {
					attrezzoCercato = this.attrezzi[j];
				}	
		}
		return attrezzoCercato;	
	}*/

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */

	public boolean removeAttrezzo(String nomeAttrezzo) {
	    Iterator<Attrezzo> iteratoreAttrezzi = this.attrezzi.iterator();
	    while (iteratoreAttrezzi.hasNext()) {
	        Attrezzo a = iteratoreAttrezzi.next();
	        if (a.getNome().equals(nomeAttrezzo)) {
	            iteratoreAttrezzi.remove();
	            return true;
	        }
	    }
	    return false;
	}	
	/* public boolean removeAttrezzo(String nomeAttrezzo) {
		boolean fatto = false;
		if (hasAttrezzo(nomeAttrezzo) == true) {
			for (int i=0; i<this.numeroAttrezzi; i++) {
				if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
					for (int j=i; j<this.numeroAttrezzi; j++) {
						this.attrezzi[j]=this.attrezzi[j+1];
					}	
					this.numeroAttrezzi--;
					fatto = true;
				} 
			}
		} 
		return fatto;
	}*/

	public ArrayList<String> getDirezioni() {
		return this.direzioni;
	}
	
	/*public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
			direzioni[i] = this.direzioni[i];
		return direzioni;
	}*/

}
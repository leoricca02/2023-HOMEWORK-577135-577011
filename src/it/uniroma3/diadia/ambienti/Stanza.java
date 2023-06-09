package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

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


public class Stanza {

	@Override
	public boolean equals(Object obj) {
		Stanza that = (Stanza) obj;
		return this.getNome().equals(that.getNome()) && this.getAttrezzi().equals(that.getAttrezzi()) && 
				this.getDirezioni().equals(that.getDirezioni()) && 
				this.getMapStanzeAdiacenti().equals(that.getMapStanzeAdiacenti());
	}

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final public int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	public Map<String, Attrezzo> attrezzi;
	//private int numeroAttrezzi;
	private Map<String,Stanza> stanzeAdiacenti;
	private List<String> direzioni;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */


	public Stanza(String nome) {
		this.nome = nome;
		//this.numeroStanzeAdiacenti = 0;
		//this.numeroAttrezzi = 0;
		this.direzioni = new ArrayList<String>(NUMERO_MASSIMO_DIREZIONI);
		this.stanzeAdiacenti = new HashMap<String,Stanza>(NUMERO_MASSIMO_DIREZIONI);
		this.attrezzi = new HashMap<String, Attrezzo>(NUMERO_MASSIMO_ATTREZZI);
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione -direzione in cui sara' posta la stanza adiacente.
	 * @param stanza -stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		if(this.stanzeAdiacenti.size()<4) {
			this.stanzeAdiacenti.put(direzione, stanza);
			this.direzioni.add(direzione);
		}
	}
	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
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
	public List<Attrezzo> getAttrezzi() {
		Collection<Attrezzo> lista = this.attrezzi.values();
		List<Attrezzo> lista1 = new ArrayList<Attrezzo>();
		lista1.addAll(lista);
		return lista1;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(String attrezzo, int peso) {
		if(this.attrezzi.size()< NUMERO_MASSIMO_ATTREZZI) {
			Attrezzo att = new Attrezzo(attrezzo, peso);
			this.attrezzi.put(attrezzo, att);
			return true;
		}
		else
			return false;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		Collection<Attrezzo> attrezzi = this.attrezzi.values();
		Iterator<String> iD = this.direzioni.iterator();
		Iterator<Attrezzo> iA = attrezzi.iterator();
		risultato.append(this.nome);
		risultato.append("\nUscite:");
		while(iD.hasNext()) {
			risultato.append(" "+ iD.next());
		}
		risultato.append("\nAttrezzi nella stanza: ");
		while(iA.hasNext()) {
			risultato.append(iA.next().toString()+" ");
		}
		return risultato.toString();
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		Attrezzo att = this.attrezzi.get(nomeAttrezzo);
		return this.attrezzi.remove(nomeAttrezzo, att);
	}

	public List<String> getDirezioni() {
		return this.direzioni;	
	}

	public Map<String, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	@Override
	public int hashCode() {
		return Objects.hash(attrezzi, direzioni, nome, stanzeAdiacenti);
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}



}
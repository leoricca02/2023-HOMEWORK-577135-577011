package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerPeso;


public class Borsa  {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	protected List<Attrezzo> attrezzi;
	//private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
		//this.numeroAttrezzi = 0;
	}

	/**funzione che aggiunge un attrezzo se possibile
	 * @param attrezzo
	 * @return true se l'aggiunta è andata a buon fine, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		else
			return this.attrezzi.add(attrezzo);

		/*if(this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;*/
	}
	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> iA = this.attrezzi.iterator();
		while(iA.hasNext()) {
			Attrezzo att = iA.next();
			if(att.getNome().equals(nomeAttrezzo))
				return att;
		}
		return null;
		/*for(int i=0; i<this.numeroAttrezzi; i++)
				if(this.attrezzi[i].getNome().equals(nomeAttrezzo))
					a = attrezzi[i];
		return a;*/
	}
	public int getPeso() {
		int pesoTotale = 0;
		Iterator<Attrezzo> iteratore =
				this.attrezzi.iterator();
		while (iteratore.hasNext()) {
			Attrezzo a = iteratore.next();
			pesoTotale += a.getPeso();
		}
		return pesoTotale;
		/*int peso = 0;
		for(int i=0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;*/
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	/*public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}*/


	/**funzione che rimuove un attrezzo dalla borsa
	 * @param nomeAttrezzo
	 * @return variabile di tipo Attrezzo a con il riferimento all'attrezzo rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iA = this.attrezzi.iterator();
		while (iA.hasNext()) {
			Attrezzo att = iA.next();
			if (att.getNome().equals(nomeAttrezzo)) {
				a = att;
				iA.remove(); // rimuovi l'oggetto tramite l'iteratore
				return a;
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if(!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"
					+this.getPesoMax()+"kg): ");
			Iterator<Attrezzo> iA = this.attrezzi.iterator();
			while(iA.hasNext()) {
				s.append(iA.next().toString()+" ");
			}
			/*for(int i=0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");*/
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	// Restituisce il contenuto della borsa ordinato per peso e poi per nome
    public List<Attrezzo> getContenutoOrdinatoPerPeso() {
    	Comparator<Attrezzo> c = new ComparatoreAttrezziPerPeso();
        List<Attrezzo> contenutoOrdinato = new ArrayList<Attrezzo>(this.attrezzi);
        Collections.sort(contenutoOrdinato, c);
        return contenutoOrdinato;
    }
	
    public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
        TreeSet<Attrezzo> contenutoOrdinato = new TreeSet<Attrezzo>();
        contenutoOrdinato.addAll(this.attrezzi);
        return contenutoOrdinato;
    }
	
    public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
        HashMap<Integer, Set<Attrezzo>> contenutoRaggruppato = new HashMap<Integer, Set<Attrezzo>>();
        for (Attrezzo attrezzo : this.attrezzi) {
            int peso = attrezzo.getPeso();
            Set<Attrezzo> attrezziStessoPeso = contenutoRaggruppato.get(peso);
            if (attrezziStessoPeso == null) {
                attrezziStessoPeso = new HashSet<Attrezzo>();
                contenutoRaggruppato.put(peso, attrezziStessoPeso);
            }
            attrezziStessoPeso.add(attrezzo);
        }
        return contenutoRaggruppato;
    }
    public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
    	Comparator<Attrezzo> c = new ComparatoreAttrezziPerPeso();
        TreeSet<Attrezzo> contenutoOrdinato = new TreeSet<Attrezzo>(c);
        contenutoOrdinato.addAll(this.attrezzi);
        return contenutoOrdinato;
    }
}

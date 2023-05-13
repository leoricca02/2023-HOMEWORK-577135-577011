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

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA=10;
	protected List<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.add(attrezzo);
	}
	


	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Iterator<Attrezzo> iteratoreAttrezzi = this.attrezzi.iterator();
		while(iteratoreAttrezzi.hasNext()) {
			Attrezzo a = iteratoreAttrezzi.next();
				if (a.getNome().equals(nomeAttrezzo)) {
					return a;
				}	
		}
		return null;	
	}


	public int getPeso(){
		int pesoTotale = 0;
		Iterator<Attrezzo> iteratoreAttrezzi = this.attrezzi.iterator();
		while (iteratoreAttrezzi.hasNext()) {
			Attrezzo a = iteratoreAttrezzi.next();
			pesoTotale += a.getPeso();
		}
		return pesoTotale;
	}


	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	/*public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}*/


	/** Funzione che rimuove oggetti dalla borsa
	 * @param nomeAttrezzo
	 * @return una variabile "a" con riferimento all'oggetto attrezzo rimosso dalla borsa
	 * 		   se la cancellazione non avviene, viene restituito "null"
	 */

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
	    Iterator<Attrezzo> iteratoreAttrezzi = this.attrezzi.iterator();
	    while (iteratoreAttrezzi.hasNext()) {
	        Attrezzo b = iteratoreAttrezzi.next();
	        if (b.getNome().equals(nomeAttrezzo)) {
	        	a = b;
	            iteratoreAttrezzi.remove();
	            return a;
	        }
	    }
	    return null;
	}


	public String toString() {
		StringBuilder s = new StringBuilder();
		Iterator<Attrezzo> iteratoreAttrezzi = this.attrezzi.iterator();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			while (iteratoreAttrezzi.hasNext()) {
				Attrezzo a = iteratoreAttrezzi.next();
				s.append(a.toString()+" ");
			}	
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	// Restituisce il contenuto della borsa ordinato per peso e poi per nome
    public List<Attrezzo> getContenutoOrdinatoPerPeso() {
    	Comparator<Attrezzo> c =new ComparatoreAttrezziPerPeso();
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
    
 // Restituisce il contenuto della borsa ordinato per peso e poi per nome
    public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
    	Comparator<Attrezzo> c = new ComparatoreAttrezziPerPeso();
        TreeSet<Attrezzo> contenutoOrdinato = new TreeSet<Attrezzo>(c);
        contenutoOrdinato.addAll(this.attrezzi);
        return contenutoOrdinato;
    }
	
}

package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatoreAttrezziPerPeso implements Comparator<Attrezzo> {
	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		int peso1 = a1.getPeso();
		int peso2 = a2.getPeso();
		if (peso1 == peso2) {
			return a1.getNome().compareTo(a2.getNome());
		}
		return peso1 - peso2;
	}
}

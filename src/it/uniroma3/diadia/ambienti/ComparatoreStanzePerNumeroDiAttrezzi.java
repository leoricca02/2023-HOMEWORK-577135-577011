package it.uniroma3.diadia.ambienti;

import java.util.Comparator;

public class ComparatoreStanzePerNumeroDiAttrezzi implements Comparator<Stanza>{

	@Override
	public int compare(Stanza o1, Stanza o2) {
		int attrezzi1 = o1.getAttrezzi().size();
		int attrezzi2 = o2.getAttrezzi().size();
		if(attrezzi1==attrezzi2)
			return o1.getNome().compareTo(o2.getNome());
		return attrezzi1-attrezzi2;
	}
}

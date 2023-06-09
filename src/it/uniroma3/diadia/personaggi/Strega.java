package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.ComparatoreStanzePerNumeroDiAttrezzi;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	private static final String MESSAGGIO_SALUTATO = "Siccome mi hai salutato e non c'è volta "
			+ "che io non lo apprezzi, ti trasferirò nella stanza con più attrezzi";

	private static final String MESSAGGIO_NON_SALUTATO = "Non mi hai salutato e l'educazione"
			+ "non ha prezzo, non ti meriti neanche un attrezzo";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {

		List<Stanza> ordinata = new ArrayList<Stanza>(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
		Collections.sort(ordinata, new ComparatoreStanzePerNumeroDiAttrezzi());

		if(!this.haSalutato()) {
			partita.setStanzaCorrente(ordinata.get(0));
			return MESSAGGIO_NON_SALUTATO;
		}
		else {
			partita.setStanzaCorrente(ordinata.get(ordinata.size()-1));
			return MESSAGGIO_SALUTATO;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Attrezzo preso = attrezzo;
		String msg = "IHIHIHI"+preso.getNome()+"è davvero ciò che mi hai dato? Pensi davvero che ti verrà riconsegnato?";
		return msg;
	}
}


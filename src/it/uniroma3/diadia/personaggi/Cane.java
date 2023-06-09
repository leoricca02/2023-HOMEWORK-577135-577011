package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private static final String MESSAGGIO = "Wof! Wof! (tradotto): Andare avanti "
			+ "non potrai più perchè ti rubo i CFU!";

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String preferito = "Osso";
		if(attrezzo.getNome().equals(preferito)) {
			partita.getStanzaCorrente().addAttrezzo("Pallina", 3);
			String msg = "*Il cane scodinzola e lascia un oggetto per terra*";
			return msg;
		}
		else {
			this.agisci(partita);
			String msg = "GRRRR! (tradotto) Non mi piace!";
			return msg;
		}
	}

}

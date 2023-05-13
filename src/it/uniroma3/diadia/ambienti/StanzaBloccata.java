package it.uniroma3.diadia.ambienti;


public class StanzaBloccata extends Stanza {
	
	private String direzioneBloccata;
	private String attrezzoChiave;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoChiave) {
		super(nome);
		this.attrezzoChiave = attrezzoChiave;
		this.direzioneBloccata = direzioneBloccata;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(this.direzioneBloccata) && this.attrezzi.containsKey(attrezzoChiave)==false)
			return this;
		else
			return super.getStanzaAdiacente(direzione);
	}
}

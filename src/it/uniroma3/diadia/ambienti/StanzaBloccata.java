package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	private String direzioneBloccata;
	private String attrezzoChiave;

	public StanzaBloccata(String nome) {
		super(nome);
		this.attrezzoChiave = "osso";
		this.direzioneBloccata = "nord";
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(this.direzioneBloccata) && this.hasAttrezzo(attrezzoChiave)==false)
			return this;
		else
			return super.getStanzaAdiacente(direzione);
	}
}

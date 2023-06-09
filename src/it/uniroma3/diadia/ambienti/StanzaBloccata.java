package it.uniroma3.diadia.ambienti;


public class StanzaBloccata extends Stanza{

	private String direzioneBloccata;
	private String attrezzoRichiesto;

	public StanzaBloccata(String nome, String direzioneBlocc, String attrezzoSblocc) {
		super(nome);
		this.attrezzoRichiesto = attrezzoSblocc;
		this.direzioneBloccata = direzioneBlocc;
	}
	
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(this.direzioneBloccata) && this.attrezzi.containsKey(attrezzoRichiesto)==false) 
			return this;
		else
			return super.getStanzaAdiacente(direzione);
	}
}

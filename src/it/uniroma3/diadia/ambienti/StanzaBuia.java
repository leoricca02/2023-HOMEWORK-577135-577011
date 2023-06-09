package it.uniroma3.diadia.ambienti;


public class StanzaBuia extends Stanza{
	
	private String attrezzoRichiesto;
	
	
	public StanzaBuia(String nome, String attrezzoPerVedere) {
		super(nome);
		this.attrezzoRichiesto = attrezzoPerVedere;
	}
	
	@Override
	public String getDescrizione() {
		if(attrezzi.containsKey(attrezzoRichiesto))
			return super.toString();
		else
			return "Qui c'è buio pesto";
	}
}

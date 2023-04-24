package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String attrezzoVoluto;

	public StanzaBuia(String nome) {
		super(nome);
		this.attrezzoVoluto = new String("osso");
	}
	
	@Override
	public String getDescrizione() {
		if (hasAttrezzo(attrezzoVoluto)) {
			return this.toString();
		} else {
			return "qui c'è buio pesto";
		}	
	}	
}

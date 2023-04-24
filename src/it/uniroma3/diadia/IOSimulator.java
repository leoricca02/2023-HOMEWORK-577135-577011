package it.uniroma3.diadia;

public class IOSimulator implements IO {
	String comandiLetti[];
	private int indiceProxComando;
	private IO io;
	
	public IOSimulator(String...comandiLetti) {
		this.comandiLetti=comandiLetti;
		this.indiceProxComando = 0;
	}
		
	@Override
	public void mostraMessaggio(String messaggio) {
		this.io.mostraMessaggio(messaggio);	
	}

	@Override
	public String leggiRiga() {
		if (this.comandiLetti.length == 0) {
			return null;
		} else return this.comandiLetti[this.indiceProxComando++];
	}

}

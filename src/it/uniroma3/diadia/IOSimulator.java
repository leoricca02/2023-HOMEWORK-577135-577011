package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;


public class IOSimulator implements IO {
	List<String> comandiLetti;
	private int indiceProxComando;
	private IO io;
	
	public IOSimulator(List<String> comandiLetti) {
		this.comandiLetti = new ArrayList<String>(comandiLetti);
		this.indiceProxComando = 0;
	}
		
	@Override
	public void mostraMessaggio(String messaggio) {
		this.io.mostraMessaggio(messaggio);	
	}

	@Override
	public String leggiRiga() {
		if (this.comandiLetti.size() == 0) {
			return null;
		} else return this.comandiLetti.get(indiceProxComando++);
	}

}

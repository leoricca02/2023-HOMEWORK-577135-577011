package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	private IO io;
	private String nomeAttrezzo;
	private String nome;
	
	public ComandoPosa(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;		
		this.io = new IOConsole();
		this.nome = new String("posa");
	}
	
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null)
			this.io.mostraMessaggio("Quale attrezzo vuoi posare?");
		else {
			Attrezzo toDropAttrezzo = null;
			toDropAttrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if (toDropAttrezzo == null)
				this.io.mostraMessaggio("Attrezzo inesistente");
			else {
				if (partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo) != null)
					partita.getStanzaCorrente().addAttrezzo(toDropAttrezzo.getNome(), toDropAttrezzo.getPeso());
				this.io.mostraMessaggio("Attrezzo posato");
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}

package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	private String nome;
	private IO io;
	private String nomeAttrezzo;

	public ComandoPosa(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
		this.nome = new String("posa");
	}
	
	public ComandoPosa() {
		this.nome = new String("posa");
	}


	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null)
			this.io.mostraMessaggio("Quale attrezzo vuoi posare?");
		else {
			Attrezzo toDropAttrezzo = null;
			toDropAttrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(toDropAttrezzo == null)
				this.io.mostraMessaggio("Attrezzo inesistente");
			else {
				if(partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo) != null)
					partita.getStanzaCorrente().addAttrezzo(toDropAttrezzo.getNome(),toDropAttrezzo.getPeso());
				this.io.mostraMessaggio("Attrezzo posato");
			}
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;

	}

	@Override
	public String getParametro() {
	return nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}
}

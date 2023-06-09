package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Questa classe modella un labirinto
 * 
 * @author franc
 * @see Stanza
 * @version base
 * 
 */
public class Labirinto {

	private Stanza stanzaIniziale;

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaFinale() {
		return stanzaFinale;
	}


	public void setStanzaFinale(Stanza stanzaFinale) {
		this.stanzaFinale = stanzaFinale;
	}

	private Stanza stanzaFinale;


	public Labirinto(String nomeFile) throws FileNotFoundException, Exception {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaFinale = c.getStanzaVincente();
	}
	
	public static LabirintoBuilder newBuilder(String labirinto) throws Exception {
		return new LabirintoBuilder(labirinto);
	}

}
	

	 class LabirintoBuilder extends Labirinto{

		private Labirinto labirinto;
		private Map<String, Stanza> stanze;
		private Stanza ultima;


		public LabirintoBuilder(String nomeFile) throws FileNotFoundException, Exception {
			super(nomeFile);
			this.labirinto = new LabirintoBuilder(nomeFile);
			this.stanze = new TreeMap<String,Stanza>();
			this.ultima = null;
		}

		public LabirintoBuilder addStanzaIniziale(String nome) {
			Stanza stanza = new Stanza(nome);
			this.labirinto.setStanzaIniziale(stanza);
			this.stanze.put(nome,stanza);
			this.ultima = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nome) {
			/*verifico che la stanza ricevuta da impostare come vincente esista già; se esiste
			 * la imposto sennò ne creo una nuova e la aggiungo*/
			if(this.stanze.containsKey(nome)) {
				Stanza stanza = this.stanze.get(nome);
				this.labirinto.setStanzaFinale(stanza);
			}
			else {
				Stanza stanza = new Stanza(nome);
				this.labirinto.setStanzaFinale(stanza);
				this.stanze.put(nome, stanza);
				this.ultima = stanza;
			}
			return this;
		}

		public LabirintoBuilder addStanza(String nome) {
			/*Se la stanza è già presente restituisco null, altrimenti aggiungo la stanza alla lista*/
			if(this.stanze.containsKey(nome))
				return this;
			else {
				Stanza stanza = new Stanza(nome);
				this.stanze.put(nome, stanza);
				this.ultima = stanza;
				return this;
			}
		}

		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			Stanza stanza = new StanzaMagica(nome,soglia);
			this.stanze.put(nome, stanza);
			this.ultima = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
			Stanza stanza = new StanzaBloccata(nome, direzioneBloccata, attrezzoSbloccante);
			this.stanze.put(nome, stanza);
			this.ultima = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
			Stanza stanza = new StanzaBuia(nome, attrezzoPerVedere);
			this.stanze.put(nome, stanza);
			this.ultima = stanza;
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			Stanza stanza = this.stanze.get(this.ultima.getNome());
			stanza.addAttrezzo(nome, peso);
			return this;
		}

		public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo attrezzo) {
			Mago mago = new Mago(nome, presentazione, attrezzo);
			Stanza stanza = this.stanze.get(this.ultima.getNome());
			stanza.setPersonaggio(mago);
			return this;
		}

		public LabirintoBuilder addCane(String nome, String presentazione) {
			Cane cane = new Cane(nome, presentazione);
			Stanza stanza = this.stanze.get(this.ultima.getNome());
			stanza.setPersonaggio(cane);
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome, String presentazione) {
			Strega strega = new Strega(nome, presentazione);
			Stanza stanza = this.stanze.get(this.ultima.getNome());
			stanza.setPersonaggio(strega);
			return this;
		}
		


		public LabirintoBuilder addAdiacenza(String stanzaIniziale, String stanzaAdiacente, String direzione) {
			Stanza stanza1 = this.stanze.get(stanzaIniziale);
			Stanza stanza2 = this.stanze.get(stanzaAdiacente);

			stanza1.impostaStanzaAdiacente(direzione, stanza2);
			return this;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public Map<String, Stanza> getListaStanze() {
			return this.stanze;
		}

	}




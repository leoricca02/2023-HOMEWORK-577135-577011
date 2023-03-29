package it.uniroma3.diadia.IOConsole;
import java.util.Scanner;

/* creare una sola istanza di questa classe nell’unico metodo main() dell’intero gioco di ruolo DiaDia.main()*/
/* Rifattorizzare tutto il codice per far arrivare tale unica istanza ove serve: 
   Può essere necessario aggiungere costruttori e/o modificare quelli già esistenti in alcune classi */
/* Eliminare tutte le stampe e le letture dirette dal resto del codice rimpiazzandole con un appropriato uso dei metodi
   della classe IOConsole */



public class IOConsole {

	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}

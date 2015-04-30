import java.util.ArrayList;
import java.util.Collections;

/**
* Das Programm simuliert einen Medienversand, welcher Buecher, CDs und DVDs vertreibt.
* Das Programm wird in folgender Form aufgerufen: java MedienversandMain "option" "parameter".
* Als "option" kann zwischen "generiere" und "zeige" gewaehlt werden.
* Bei Aufruf von "generiere" bestimmt "parameter" die Anzahl der zu erzeugenden Medien (100 bis 1000).
* Es werden zwei Dateien Namens Medien.dat und Verkauf.dat generiert.
* Medien.dat enthaelt zufaellig erzeugte Medien, Verkauf.dat Verkaufsdaten fuer ein Jahr.
* Pro Tag finden 50 bis 300 Verkaeufe statt, wobei jeweils eines der 
* zuvor erzeugten Medien bis zu 10 Mal verkauft werden kann.
* Bei Aufruf von "zeige" bestimmt "parameter" einen Tag (1 bis 365).
* Fuer diesen Tag wird eine Uebersichtstabelle der Verkaufsauswertung auf dem Bildschirm ausgegeben.
* Die Daten hierfuer werden aus den zuvor erzeugten Dateien Medien.dat und Verkauf.dat gezogen.
* Wird das Programm in einer falschen Form aufgerufen, wird ein Hilfetext zur Benutzung des Programms auf dem Bilschirm ausgegeben.
* @author Sophia Muehling
*/
public class MedienversandMain
{
	/** Die gewaehlte Option zum Aufruf des Programms. */
	private static String option = "";
	/** Die Anzahl der zu erzeugenden Medien. */
	private static int anzahlMedien = 0;
	/** Der Tag des Jahres fuer den die Verkaufsauswertung ausgegeben werden soll. */
	private static int tagImJahr = 0;

	/**
	* Programm zur Simulation eines Medienversands.
	* @param args  Die Parameter, die beim Programmaufruf uebergeben wurden.
	*/
	public static void main(String[] args)
	{
		if (checkParameter(args))
		{
			switch (option)
			{
				case "generiere":
					Medienversand simulation = new Medienversand();
					simulation.generiereMedien(anzahlMedien);
					if (simulation.schreibeMediumInDatei())
					{
						System.out.println("\nDie Datei Medien.dat wurde erfolgreich erstellt.\n\nBitte warten...");
					}
					else
					{
						System.out.println("\nFehler! Die Datei Medien.dat konnte nicht erstellt werden.");
					}
					simulation.generiereTagesVerkaeufe();
					if (simulation.schreibeTagesVerkaeufeInDatei())
					{
						System.out.println("\nDie Datei Verkauf.dat wurde erfolgreich erstellt.");
					}
					else
					{
						System.out.println("\nFehler! Die Datei Verkauf.dat konnte nicht erstellt werden.");
					}
					break;
				case "zeige":
					System.out.println("\nVerkaufsuebersicht fuer den " + tagImJahr + ". Tag.\n=====================================\n");
					generiereAuswertung();
					break;
			}
		}
	}

	/**
	* Prueft die beim Programmstart uebergebenen Parameter auf Richtigkeit.
	* @param args 	die Parameter, die beim Programmaufruf uebergeben wurden.
	* @return Boolescher Wert, ob die uebergebenen Parameter richtig sind.
	*/
	public static boolean checkParameter(String[] args)
	{
		boolean gueltig = false;
		if (args.length == 2)
		{
			try
			{
				option = args[0].toLowerCase();
				switch (option)
				{
					case "generiere":
						try
						{
							anzahlMedien = Integer.parseInt(args[1]);
							if (anzahlMedien >= 100 && 1000 >= anzahlMedien)
							{
								gueltig = true;
							}
						}
						catch (NumberFormatException nfe)
						{
						}
						break;
					case "zeige":
						try
						{
							tagImJahr = Integer.parseInt(args[1]);
							if (0 < tagImJahr && tagImJahr <= 365)
							{
								gueltig = true;
							}
						}
						catch (NumberFormatException nfe)
						{
						}
						break;
				}
			}
			catch (Exception e)
			{
			}
		}
		if (!gueltig)
		{
			schreibeHilfe();
		}
		return gueltig;
	}

	/** Gibt einen Hilfehinweis zur Benutzung des Programms auf dem Bildschirm aus. */
	public static void schreibeHilfe()
	{
		System.out.println("\nFehler!\n\nBenutzung: java MedienversandMain <option> <parameter>\n\n" +
		"<option>:\n"+
		"\"generiere\":\tGeneriert zwei Dateien: Medien.dat und Verkauf.dat."
		+ "\n\t\tMedien.dat enthaelt zufaellig erzeugte Medien, Verkauf.dat Verkaufsdaten fuer ein Jahr.\n" +
		"\"zeige\":\tGibt eine Auswertung der Verkaufe aus, indem die Informationen aus den beiden Dateien gelesen werden.\n\n" +
		"<parameter>:\n"+
		"Wird \"generiere\" als <option> gewaehlt, dann bestimmt dieser Parameter die Anzahl der zu erzeugenden Medien.\n" +
		"Dieser Wert sollte im Bereich von 100 bis 1000 liegen.\n"+
		"Wird \"zeige\" als <option> gewaehlt, dann bestimmt dieser Parameter den Tag, fuer den die Verkaufsuebersicht ausgegeben werden soll.\n" +
		"Der Tag sollte im Bereich von 1 bis 365 liegen.\n");
	}
	
	/**
	* Erzeugt und gibt die Auswertung fuer einen Verkaufstag auf dem Bildschirm aus.
	*/
	public static void generiereAuswertung()
	{
		try
		{
			ArrayList<Object> tempListe = new ArrayList<Object>();
			tempListe = SchreibeUndLeseInDatei.leseArrayListAusDatei("Verkauf.dat");
			Verkaufstag verkaufstag = null;
			for (Object derTagesverkauf : tempListe)
			{
				if (((Verkaufstag) derTagesverkauf).getTagImJahr() == tagImJahr)
				{
					verkaufstag = (Verkaufstag) derTagesverkauf;
				}
			}
			ArrayList<Verkauf> verkaeufeListe = verkaufstag.getVerkaufsTagListe();
			Collections.sort(verkaeufeListe);
			int zaehler = 0;
			for (Verkauf derVerkauf : verkaeufeListe)
			{
				System.out.printf("%3d) ", ++zaehler);
				System.out.printf("%-80s", derVerkauf.getMedium().getInfo());
				System.out.printf(":  %3d * %7.2f EUR = %8.2f EUR\n", derVerkauf.getAnzahlVerkaeufe(), derVerkauf.getMedium().getPreis(), derVerkauf.getUmsatz());
			}
		}
		catch (Exception e)
		{
			System.out.println("Fuer diesen Tag existieren keine Verkaeufe.");
		}
	}
}
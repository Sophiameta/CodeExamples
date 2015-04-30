import java.util.ArrayList;
import java.util.Random;

/**
 * Repraesentiert einen Medienversand, der aus einem Zufallsgenerator, einer Liste mit den erzeugten Medien 
 * und einer Liste mit den erzeugten Tagesverkaeufen besteht.
 * Die Informationen aus der Medien- und Tagesverkaufsliste werden in die Dateien Medien.dat und Verkauf.dat geschrieben.
 * @author Sophia Muehling
 */
public class Medienversand
{
	/** Der Zufallsgenerator. */
	private static Random random = new Random();
	/** Die Liste mit den erzeugten Medien. */
	private ArrayList<Medium> medienListe = null;
	/** Die Liste mit den erzeugten Tagesverkaeufen. */
	private ArrayList<Verkaufstag> tagesVerkaufsListe;

	/**
	 * Erzeugt den Medienversand mit Medienliste und der Tagesverkaufsliste.
	 */
	public Medienversand()
	{
		medienListe = new ArrayList<Medium>();
		tagesVerkaufsListe = new ArrayList<Verkaufstag>();
	}

	/**
	 * Fuegt zufaellige Medien zur Medienliste hinzu.
	 * @param anzahlMedien	Die Anzahl der Medien.
	 * @throws IllegalArgumentException 	wenn die Anzahl der Medien kleiner als 0 ist.
	 */
	public void generiereMedien(int anzahlMedien)
	{
		if (anzahlMedien < 0)
		{
			throw new IllegalArgumentException();
		}
		Medium neuesMedium;
		while (medienListe.size() < anzahlMedien)
		{
			neuesMedium = generiereRandomMedium();
			if (!medienListe.contains(neuesMedium))
			{
				medienListe.add(neuesMedium);
			}
		}
	}

	/**
	 * Erzeugt ein zufaelliges Medium (Buch, CD, DVD).
	 * @return das zufaellig erzeugte Medium
	 */
	public Medium generiereRandomMedium()
	{
		Medium randomMedium = new Medium();
		switch(random.nextInt(2 + 1))
		{
			case 0:
				randomMedium = new Buch();
				break;
			case 1:
				randomMedium = new CD();
				break;
			case 2:
				randomMedium = new DVD();
				break;
		}
		return randomMedium;
	}

	/**
	 * Fuegt zufaellige Tagesverkaeufe fuer ein Jahr (365 Tage) zu der Tagesverkaufsliste hinzu.
	 */
	public void generiereTagesVerkaeufe()
	{
		Verkaufstag tagesVerkaufEinTag;
		for (int i = 1; i <= 365; i++)
		{
			tagesVerkaufEinTag = new Verkaufstag(i);
			tagesVerkaufEinTag.generiereRandomVerkaufstag();
			tagesVerkaufsListe.add(tagesVerkaufEinTag);
		}
	}

	/**
	 * Schreibt die erstellte Medienliste in die Datei 'Medien.dat'.
	 * @return Boolescher Wert, ob die Datei erzeugt und geschrieben werden konnte oder nicht.
	 */
	public boolean schreibeMediumInDatei()
	{
		boolean erzeugt = false;
		if (SchreibeUndLeseInDatei.schreibeInDatei(medienListe, "Medien.dat"))
		{
			erzeugt = true;
		}
		return erzeugt;
	}

	/**
	 * Schreibt die erstellte Tagesverkaufsliste in die Datei 'Verkauf.dat'.
	 * @return Boolescher Wert, ob die Datei erzeugt und geschrieben werden konnte oder nicht.
	 */
	public boolean schreibeTagesVerkaeufeInDatei()
	{
		boolean erzeugt = false;
		if (SchreibeUndLeseInDatei.schreibeInDatei(tagesVerkaufsListe, "Verkauf.dat"))
		{
			erzeugt = true;
		}
		return erzeugt;
	}
}
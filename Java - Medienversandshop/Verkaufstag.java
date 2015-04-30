import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;

/**
 * Repraesentiert einen Verkaufstag, der aus einem Zufallsgenerator, 
 * einer Liste von allen durchgefuehrten Verkaeufen eines Tages und dem Tag, 
 * an dem die Verkaeufe stattfinden, besteht.
 * @author Sophia Muehling
 */
public class Verkaufstag implements Serializable
{
	/** Der Zufallsgenerator. */
	private static Random random = new Random();
	/** Die Liste von allen Verkaeufen an einem Tag. */
	private ArrayList<Verkauf> verkaufsTagListe = new ArrayList<>();
	/** Der Tag, an dem die Verkaeufe stattfinden. */
	private int tagImJahr = 0;
	
	/**
	 * Erzeugt einen Verkaufstag mit einem als Parameter uebergebenem Tag. 
	 * @param tagImJahr 	Der Tag an dem die Verkaeufe stattfanden.
	 * @throws IllegalArgumentException 	wenn der Tag nicht im Bereich von 1 bis 365 liegt.
	 */
	public Verkaufstag(int tagImJahr)
	{ 
		if (tagImJahr < 1 && tagImJahr > 365)
		{
			throw new IllegalArgumentException("Der Tag muss im Bereich von 1 bis 365 liegen.");
		}
		this.tagImJahr = tagImJahr;
	}
	
	/**
	 * Liefert eine Liste mit allen Verkaeufen des Tages.
	 * @return die Liste mit den Verkaeufen des Tages.
	 */
	public ArrayList<Verkauf> getVerkaufsTagListe()
	{
		return verkaufsTagListe;
	}	
	
	/**
	 * Liefert den Tag.
	 * @return der Tag.
	 */
	public int getTagImJahr()
	{
		return tagImJahr;
	}	
	
	/**
	 * Erzeugt eine Liste mit zufaelligen Verkaeufen (50 - 300 Verkaeufe mit bis zu 10 Verkaeufen eines Mediums pro Verkauf) an einem Tag.
	 * Die verkauften Medien werden aus der Medien.dat gelesen und dann zufaellig daraus ausgewaehlt.
	 */
	public void generiereRandomVerkaufstag()
	{
		ArrayList<Object> medienListe = SchreibeUndLeseInDatei.leseArrayListAusDatei("Medien.dat");
		Verkauf einVerkauf = new Verkauf((Medium) medienListe.get(random.nextInt(medienListe.size())), random.nextInt(10) + 1);
		verkaufsTagListe.add(einVerkauf);
		boolean istEnthalten;
		Verkauf enthaltenerVerkauf;
		for (int i = 0; i < random.nextInt(300 - 50 + 1) + 50 -1; i++)
		{
			istEnthalten = false;
			enthaltenerVerkauf = null;
			einVerkauf = new Verkauf((Medium) medienListe.get(random.nextInt(medienListe.size())), random.nextInt(10) + 1);
			for (Verkauf bestehendeVerkaeufe : verkaufsTagListe)
			{
				if (bestehendeVerkaeufe.getMedium().equals(einVerkauf.getMedium()))
				{
					istEnthalten = true;
					enthaltenerVerkauf = bestehendeVerkaeufe;
				}
			}
			if (istEnthalten)
			{
				enthaltenerVerkauf.addAnzahlVerkaeufe(einVerkauf.getAnzahlVerkaeufe());
			}
			else 
			{
				verkaufsTagListe.add(einVerkauf);	
			}
		}
	}
}
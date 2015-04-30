import java.io.Serializable;

/**
 * Repraesentiert den Verkauf eines Mediums, der aus einem verkauften Medium und	
 * der Anzahl, wie oft das Medium verkauft wurde, besteht.
 * @author Sophia Muehling
 */
public class Verkauf implements Serializable, Comparable<Verkauf>
{
	/** Das Verkaufte Medium. */
	private Medium medium;
	/** Die Anzahl, wie oft das Medium verkauft wurde. */
	private int anzahlVerkaeufe;

	/**
	 * Erzeugt einen Verkauf mit als Parameter uebegebenem Medium, das verkauft wurde und
	 * der Anzahl der Verkaeufe des Mediums.
	 * @param medium					 Das Medium, das verkauft wurde.
	 * @param anzahlVerkaeufe	 Die Anzahl der Verkaeufe.
	 * @throws IllegalArgumentException 	wenn die Anzahl der Verkaeufe kleiner 1 ist.
	 */
	public Verkauf(Medium medium, int anzahlVerkaeufe)
	{
		if (anzahlVerkaeufe < 1)
		{
			throw new IllegalArgumentException("Die Anzahl der Verkaufe darf nicht kleiner 1 sein.");
		}
		this.medium = medium;
		this.anzahlVerkaeufe = anzahlVerkaeufe;
	}

	/**
	 * Addiert eine Anzahl von Verkaeufen zu einer bestehenden hinzu.
	 * @param anzahlVerkaeufe 	Die Anzahl der zu addierenden Verkaeufe.
	 * @throws IllegalArgumentException 	wenn die Anzahl der Verkaeufe kleiner 0 ist.
	 */
	public void addAnzahlVerkaeufe(int anzahlVerkaeufe)
	{
		if (anzahlVerkaeufe < 0)
		{
			throw new IllegalArgumentException("Die Anzahl der Verkaeufe darf nicht kleiner 0 sein.");
		}
		this.anzahlVerkaeufe += anzahlVerkaeufe;
	}

	/**
	 * Liefert die Anzahl der Verkaeufe.
	 * @return die Anzahl der Verkaeufe
	 */
	public int getAnzahlVerkaeufe()
	{
		return anzahlVerkaeufe;
	}

	/**
	 * Liefert das verkaufte Medium.
	 * @return das verkaufte Medium
	 */
	public Medium getMedium()
	{
		return medium;
	}

	/**
	 * Liefert den Umsatz, der beim Verkauf erzielt wurde.
	 * @return der Umsatz des Verkaufs
	 */
	public double getUmsatz()
	{
		return medium.getPreis() * anzahlVerkaeufe;
	}

	/**
	 * Liefert das Vergleichsergebnis des aktuellen Umsatzes mit einem anderen Umsatz nach der Wertigkeit. 
	 * @param verkauf		Der zu vergleichende Verkauf.
	 * @return das Vergleichsergebnis der Umsaetze
	 */
	@Override
	public int compareTo(Verkauf verkauf)
	{
		int ergebnis;
		if (getUmsatz() > verkauf.getUmsatz())
		{
			ergebnis = -1;
		}
		else if (getUmsatz() < verkauf.getUmsatz())
		{
			ergebnis = 1;
		}
		else
		{
			ergebnis = 0;
		}
		return ergebnis;
	}
}
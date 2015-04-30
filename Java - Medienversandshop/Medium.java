import java.io.Serializable;
import java.util.Random;

/**
 * Repraesentiert ein Medium, dass aus einem Zufallsgenerator, sowie dem Medientyp, dem Titel, 
 * dem Urheber, einer zusaetzlichen Eigenschaft und dem Preis des Mediums besteht.
 * @author Sophia Muehling
 */
public class Medium implements Serializable 
{
	/** Initialisiert den Zufallgenerator. */
	private static Random random = new Random();
	/** Der Medientyp. */
	private String medientyp;	
	/** Der Titel des Mediums. */
	private String titel;
	/** Der Urheber des Mediums. */
	private String urheber;
	/** Die zusaetzliche Eigenschaft des Mediums.*/
	private String eigenschaft;
	/** Der Preis des Mediums. */
	private double preis;

	/**
	 * Erzeugt ein Medium mit zufaelligen Werten fuer den Medientyp, den Titel, den Urheber, 
	 * die zusaetzliche Eigenschaft und den Preis des Mediums. 
	 */
	public Medium()
	{
		medientyp = "Medium";
		titel = "Titel" + " " + (random.nextInt(999 - 100 + 1) + 100);
		urheber = "Urheber";
		eigenschaft = "Eigenschaft";
		preis = (10 + (100 - 10) * random.nextDouble());
	}

	/**
	 * Erzeugt ein Medium mit als Parameter uebergebenen Medientyp, Titeltyp, Urhebertyp
	 * und dem Array aus zusaetzlichen Eigenschaften des Mediums. 
	 * @param medientyp				Der Medientyp des Mediums.
	 * @param titeltyp 				Der Titeltyp des Mediums.
	 * @param urhebertyp			Der Urhebertyp des Mediums.
	 * @param eigenschaften 	Array aus moeglichen zusaetzlichen Eigenschaften des Mediums.
	 * @throws NullPointerException 	wenn kein Medientyp, Titeltyp, Urhebertyp oder Array aus zusaetzlichen Eigenschaften vorhanden ist.
	 */
	public Medium(String medientyp, String titeltyp, String urhebertyp, String[] eigenschaften)
	{
		if (medientyp == null || titeltyp == null || urhebertyp == null || eigenschaften == null)
		{
			throw new NullPointerException();
		}
		else
		{
			this.medientyp = medientyp;
			titel = titeltyp + " " + (random.nextInt(999 - 10 + 1) + 100);
			urheber = urhebertyp + " " + (random.nextInt(999 - 10 + 1) + 100);
			eigenschaft = eigenschaften[random.nextInt(eigenschaften.length)];
			preis = (10 + (100 - 10) * random.nextDouble());
		}
	}

	/**
	 * Liefert den Titel des Mediums.
	 * @return der Titel des Mediums
	 */
	public String getTitel()
	{
		return titel;
	}
	
	/**
	 * Liefert den Preis des Mediums.
	 * @return der Preis des Mediums
	 */
	public double getPreis()
	{
		return preis;
	}
	
	/**
	 * Liefert den Urheber des Mediums.
	 * @return der Urheber des Mediums 
	 */
	
	public String getUrheber()
	{
		return urheber;
	}

	/**
	 * Liefert den Medientyp.
	 * @return der Medientyp 
	 */
	
	public String getMedientyp()
	{
		return medientyp;
	}

	/**
	 * Liefert die zusaetzliche Eigenschaft des Mediums.
	 * @return die zusaetzliche Eigenschaft des Mediums
	 */
	public String getEigenschaft()
	{
		return eigenschaft;
	}
	
	/**
	* Liefert den Informationstext zum Medientyp, Titel, Urheber und der zusaetzlichen Eigenschaft des Mediums.
	* @return der Informationstext zum Medientyp, Titel, Urheber und der zusaetzlichen Eigenschaft des Mediums.
	*/
	public String getInfo()
	{
		return getMedientyp() + " '" + getTitel() + "' von '" + getUrheber() + "' " + getEigenschaft();
	}

	/**
	 * Vergleicht das aktuelle Medium mit einem Vergleichsmedium, ob sie gleich sind.
	 * @param vergleichsMedium	 Das zu vergleichende Medium.
	 * @return Boolescher Wert, ob das aktuelle Medium und das Vergleichsmedium gleich sind oder nicht.
	 */

	public boolean equals(Object vergleichsMedium)
	{
		boolean gleich = false;
		if (this == vergleichsMedium)
			gleich = true;
		else if (vergleichsMedium == null || getClass() != vergleichsMedium.getClass())
			gleich = false;
		else
		{
			Medium medium = (Medium) vergleichsMedium;
			if (getTitel().equals(medium.getTitel()))
			{
				gleich = true;
			}
		}
		return gleich;
	}
}
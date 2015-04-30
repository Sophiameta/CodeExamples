/**
 * Repraesentiert eine DVD, die von der Klasse Medium erbt und 
 * aus einem Array aus moeglichen zusaetzlichen Eigenschaften 
 * (frei ab 0, 6, 12, 18 oder 21 Jahren) und der zusaetzlichen Eigenschaft besteht.
 * @author Sophia Muehling
 */
public class DVD extends Medium
{
	/** Array aus moeglichen zusaetzlichen Eigenschaften. */
	private static String[] eigenschaften = {"frei ab 0 Jahren", "frei ab 6 Jahren", "frei ab 12 Jahren", "frei ab 18 Jahren", "frei ab 21 Jahren"};
	/** Die zusaetzliche Eigenschaft der DVD. */
	private String eigenschaft = "frei ab 6 Jahren";
		
	/** Erzeugt eine DVD.	*/
	public DVD()
	{
		super("DVD", "Filmtitel", "Regisseur", eigenschaften);
		eigenschaft = super.getEigenschaft();
	}

	@Override
	/**
	 * Liefert die zusaetzliche Eigenschaft.
	 * @return die zusaetzliche Eigenschaft
	 */
	public String getEigenschaft()
	{
		return "(" + eigenschaft + ")";
	}
}
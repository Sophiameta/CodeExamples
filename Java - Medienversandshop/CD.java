/**
 * Repraesentiert eine CD, die von der Klasse Medium erbt und 
 * aus einem Array aus moeglichen zusaetzlichen Eigenschaften 
 * (Rock, Pop, Klassik, HipHop, Metal, Techno oder Jazz) und 
 * der zusaetzlichen Eigenschaft besteht.
 * @author Sophia Muehling
 */
public class CD extends Medium
{
	/** Array aus moeglichen zusaetzlichen Eigenschaften. */
	private static String[] eigenschaften = {"Rock", "Pop", "Klassik", "HipHop", "Metal", "Techno", "Jazz"};
	/** Die zusaetzliche Eigenschaft der CD. */
	private String eigenschaft = "Rock";
		
	/** Erzeugt eine CD. */
	public CD()
	{
		super("CD", "CD Titel", "Interpret", eigenschaften);
		eigenschaft = super.getEigenschaft();
	}

	@Override
	/**
	 * Liefert die zusaetzliche Eigenschaft.
	 * @return die zusaetzliche Eigenschaft
	 */
	public String getEigenschaft()
	{
		return "[" + eigenschaft + "]";
	}
}
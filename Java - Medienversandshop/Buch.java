/**
 * Repraesentiert ein Buch, das von der Klasse Medium erbt und 
 * aus einem Array aus moeglichen zusaetzlichen Eigenschaften (Hardcover oder nicht) besteht.
 * @author Sophia Muehling
 */
public class Buch extends Medium
{
	/** Array aus moeglichen zusaetzlichen Eigenschaften. */
	private static String[] eigenschaften = {" ", "[HARDCOVER]"};
	
	
	/** Erzeugt ein Buch. */
	public Buch()
	{
		super("Buch", "Titel", "Autor", eigenschaften);
	}
}
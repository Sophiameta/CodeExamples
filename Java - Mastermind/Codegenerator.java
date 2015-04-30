import java.util.Set;
import java.util.TreeSet;

/**
 * Repräsentiert einen Codegenerator, der aus der Stellenanzahl der erzeugenden Codes besteht.
 * @author Sophia Mühling
 */
public class Codegenerator
{
	/** Die Stellenanzahl des zu erzeugenden Codes. */
	private int stellenanzahl = 4;
	
	/** 
	 * Erzeugt einen Codegenerator mit als Parameter übergebener Stellenanzahl (1-9) des Codes.
	 * @param 	stellenanzahl 	Die Stellenanzahl des Codes.
	 * @throws 	IllegalArgumentException 	Die Stellenanzahl des Codes darf nicht kleiner gleich 0 oder größer als 9 sein.
	 */
	public Codegenerator(int stellenanzahl)
	{
		if (stellenanzahl <= 0 || stellenanzahl > 9)
		{
			throw new IllegalArgumentException( "Die Stellenanzahl des Codes darf nicht kleiner gleich 0 oder größer als 9 sein." );
		}
		this.stellenanzahl = stellenanzahl;
		
    }

	/** 
	 * Liefert einen Code aus Ziffern von 0 bis 9, wobei jede Ziffer nur einmal im Code vorkommen darf.
	 * @return 	Der Code aus Ziffern von 0 bis 9, wobei jede Ziffer nur einmal im Code vorkommen darf.
	 */
	public String getCodeString()
	{
		int zahl = (int)(Math.random() * 9) + 1;
		TreeSet<Integer> code = new TreeSet<Integer>();
		String codeString = "";
		while (code.size() < stellenanzahl)
    {
			zahl = (int) (Math.random() * 9);
			if (code.add(zahl))
			{
				codeString = codeString + zahl;
			}
    }
		return codeString;
	}

	/** 
	 * Liefert einen Code aus Ziffern von 0 bis 9, die je nach Stellenanzahl der Reihe nach zum Code hinzugefügt werden
	 * (zum Beispiel 0123 bei 4 Stellen, 012345 bei 6 Stellen, usw.).
	 * @return 	Der Code aus Ziffern von 0 bis 9, die je nach Stellenanzahl der Reihe nach zum Code hinzugefügt werden.
	 */
	public String getStartCodeString()
	{
		String startCodeString = "";
		for (int i = 0; i < stellenanzahl; i++)
		{
			startCodeString = startCodeString + i;
		}
		return startCodeString;
	}
}

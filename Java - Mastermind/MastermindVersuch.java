/** 
 * Repräsentiert einen Rateversuch, der aus einem Rateversuchcode, der Angabe, 
 * wie viele der Ziffern des Rateversuchs sich an derselben Stelle befinden,
 * wie im gesuchten Code, und wie viele weitere Ziffern des Rateversuchs im gesuchten Code vorkommen,
 * sich dort aber an anderer Stelle befinden besteht.
 * @author Sophia Mühling
 */
public class MastermindVersuch
{
	/** Der Rateversuchcode. */
	private String tippString = "";
	/** Die Angabe, wie viele der Ziffern des Rateversuchs sich an derselben Stelle befinden, wie im gesuchten Code. */
	private int richtigeStelle = 0;
	/** Die Angabe, wie viele Ziffern des Rateversuchs im gesuchten Code vorkommen, sich dort aber an anderer Stelle befinden. */
	private int richtigeZahl = 0;

	/** 
	 * Erzeugt einen Rateversuch mit als Parameter übergebenem zu erratendem Code und einem Rateversuchscode.
	 * @param 	codeString 	Der zu erratende Code.
	 * @param 	tippString 	Der Rateversuchscode.
	 */
	public MastermindVersuch(String codeString, String tippString)
	{
		this.tippString = tippString;
	    for (int i = 0; i < codeString.length(); i++)
		{			
			if(tippString.charAt(i) == codeString.charAt(i))
			{
				richtigeStelle++;
			}
			else
			{
				String tippzahlAnStelle = tippString.charAt(i) + "";
				if(codeString.contains(tippzahlAnStelle))
				{
					richtigeZahl++;
				}
			}
		}
	}

	/** 
	 * Liefert den Rateversuchcode. 
	 * @return Der Rateversuchcode.
	 */
	public String getTippString()
	{
		return tippString;
	}

	/** 
	 * Liefert die Angabe, wie viele der Ziffern des Rateversuchs sich an derselben Stelle befinden, wie im gesuchten Code.
	 * @return Die Angabe, wie viele der Ziffern des Rateversuchs sich an derselben Stelle befinden, wie im gesuchten Code.
	 */
	public int getRichtigeStelle()
	{
		return richtigeStelle;
	}

	/** 
	 * Liefert die Angabe, wie viele Ziffern des Rateversuchs im gesuchten Code vorkommen, sich dort aber an anderer Stelle befinden.
	 * @return Die Angabe, wie viele Ziffern des Rateversuchs im gesuchten Code vorkommen, sich dort aber an anderer Stelle befinden.
	 */
	public int getRichtigeZahl()
	{
		return richtigeZahl;
	}

}

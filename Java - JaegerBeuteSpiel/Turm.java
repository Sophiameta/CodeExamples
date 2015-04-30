/** 
 * Repräsentiert einen Turm, der von der Klasse Spielfigur erbt.
 * @author Sophia Mühling
 */
public class Turm extends Spielfigur 
{
	/** 
	 * Erzeugt eine Turm-Spielfigur mit der als Parameter übergebenen Position (Zeile und Spalte).
	 * @param 	zeile 	Die Zeile, in der der Turm positioniert ist.
	 * @param 	spalte 	Die Spalte, in der der Turm positioniert ist.
	 */
	public Turm(int zeile, int spalte) 
	{
		super(zeile, spalte, "Der Turm");
	}
	
	/** 
	 * Prüft mit Hilfe der Beuteposition (Zeile und Spalte), 
	 * ob der Turm die Beute im nächsten Zug treffen könnte.
   * @param 	beutezeile 	 	Die Zeile, in der sich die Beute befindet.
   * @param 	beutespalte 	Die Spalte, in der sich die Beute befindet.
	 * @return Die Aussage, ob der Turm die Beute im nächsten Zug treffen könnte.
	 */
	@Override
	public boolean kannBeuteTreffen(int beutezeile, int beutespalte) 
	{
		return (getSpalte() == beutespalte || getZeile() == beutezeile);
	}
	
	/** Bewegt den Turm auf dem Spielfeld. */
	@Override
	public void ziehe() 
	{
		String richtung = MeineEingabe.erfasseText("Wie moechten Sie ziehen (-, |):\t", "[-|]");
		int anzahlfelder = MeineEingabe.erfasseInt("Wie viele Felder (> 0: nach rechts oben; < 0: nach links unten):\t", -7, 7);
		
		if (richtung.equals("-"))
		{
			setSpalte(getSpalte() + anzahlfelder);
		}
		else
		{
			setZeile(getZeile() + anzahlfelder);
		}
		korrigiere(richtung, anzahlfelder < 0);
	}
}

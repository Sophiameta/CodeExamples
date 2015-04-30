/** 
 * Repräsentiert eine Dame, die von der Klasse Spielfigur erbt.
 * @author Sophia Mühling
 */
public class Dame extends Spielfigur 
{
	/** 
	 * Erzeugt eine Dame-Spielfigur mit der als Parameter übergebenen Position (Zeile und Spalte).
	 * @param 	zeile 	Die Zeile, in der die Dame positioniert ist.
	 * @param 	spalte 	Die Spalte, in der die Dame positioniert ist.
	 */
	public Dame(int zeile, int spalte) 
	{
		super(zeile, spalte, "Die Dame");
	}
	
	/** 
	 * Prüft mit Hilfe der Beuteposition (Zeile und Spalte), 
	 * ob die Dame die Beute im nächsten Zug treffen könnte.
	 * @param 	beutezeile 	 	Die Zeile, in der sich die Beute befindet.
	 * @param 	beutespalte 	Die Spalte, in der sich die Beute befindet.
	 * @return Die Aussage, ob die Dame die Beute im nächsten Zug treffen könnte.
	 */
	@Override
	public boolean kannBeuteTreffen(int beutezeile, int beutespalte) 
	{
		boolean treffbar = (getSpalte() == beutespalte || getZeile() == beutezeile);
		int cursorZeile = getZeile();
		int cursorSpalte = getSpalte();
		while (++cursorSpalte < 9 && ++cursorZeile < 9 && !treffbar) 
		{
			if (beutezeile == cursorZeile && beutespalte == cursorSpalte)
			{
				treffbar = true;
			}
		}
		
		cursorZeile = getZeile();
		cursorSpalte = getSpalte();
		while (--cursorSpalte > 0 && ++cursorZeile < 9 && !treffbar) 
		{
			if (beutezeile == cursorZeile && beutespalte == cursorSpalte)
				treffbar = true;
		}
		
		cursorZeile = getZeile();
		cursorSpalte = getSpalte();
		while (++cursorSpalte < 9 && --cursorZeile > 0 && !treffbar) 
		{
			if (beutezeile == cursorZeile && beutespalte == cursorSpalte)
				treffbar = true;
		}
		
		cursorZeile = getZeile();
		cursorSpalte = getSpalte();
		while (--cursorSpalte > 0 && --cursorZeile > 0 && !treffbar) 
		{
			if (beutezeile == cursorZeile && beutespalte == cursorSpalte)
				treffbar = true;
		}
		return treffbar;
	}
	
	/** Bewegt die Dame auf dem Spielfeld. */
	@Override
	public void ziehe() 
	{
		String richtung = MeineEingabe.erfasseText("Wie moechten Sie ziehen (-, |, /, \\):\t", "[-|/\\\\]");
		int anzahlfelder = MeineEingabe.erfasseInt("Wie viele Felder (> 0: nach rechts oben; < 0: nach links unten):\t", -7, 7);
		
		if (richtung.equals("-"))
		{
			setSpalte(getSpalte() + anzahlfelder);
		}
		else if (richtung.equals("|"))
		{
			setZeile(getZeile() + anzahlfelder);
		}
		else if (richtung.equals("/")) 
		{
			setSpalte(getSpalte() + anzahlfelder);
			setZeile(getZeile() + anzahlfelder);
		} 
		else 
		{
			setSpalte(getSpalte() - anzahlfelder);
			setZeile(getZeile() + anzahlfelder);
		}
		korrigiere(richtung, anzahlfelder < 0);
	}
}

/** 
 * Repräsentiert einen Läufer, der von der Klasse Spielfigur erbt.
 * @author Sophia Mühling
 */
public class Laeufer extends Spielfigur 
{
	/** 
	 * Erzeugt eine Läufer-Spielfigur mit als Parameter übergebener Position (Zeile und Spalte) 
	 * und der Aussage, ob der Läufer auf einem schwarzen Feld steht.
	 * @param 	zeile 	Die Zeile, in der der Läufer positioniert ist.
	 * @param 	spalte 	Die Spalte, in der der Läufer positioniert ist.
	 * @param 	schwarz 	Aussage, ob der Läufer auf einem schwarzen Feld steht.
	 */
	public Laeufer(int zeile, int spalte, boolean schwarz) 
	{
		super(zeile, spalte, "Der Laeufer " + (schwarz ? "(schwarz)" : "(weiss)"));
	}

	/** 
	 * Prüft mit Hilfe der Beuteposition (Zeile und Spalte), 
	 * ob der Läufer die Beute im nächsten Zug treffen könnte.
	 * @param 	beutezeile 	 	Die Zeile, in der sich die Beute befindet.
	 * @param 	beutespalte 	Die Spalte, in der sich die Beute befindet.
	 * @return Die Aussage, ob der Läufer die Beute im nächsten Zug treffen könnte.
	 */
	@Override
	public boolean kannBeuteTreffen(int beutezeile, int beutespalte) 
	{
		boolean treffbar = false;
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
			{
				treffbar = true;
			}
		}
		
		cursorZeile = getZeile();
		cursorSpalte = getSpalte();
		while (++cursorSpalte < 9 && --cursorZeile > 0 && !treffbar) 
		{
			if (beutezeile == cursorZeile && beutespalte == cursorSpalte)
			{
				treffbar = true;
			}
		}
		
		cursorZeile = getZeile();
		cursorSpalte = getSpalte();
		while (--cursorSpalte > 0 && --cursorZeile > 0 && !treffbar) 
		{
			if (beutezeile == cursorZeile && beutespalte == cursorSpalte)
			{
				treffbar = true;
			}
		}
		return treffbar;
	}
	
	/** Bewegt den Läufer auf dem Spielfeld. */
	@Override
	public void ziehe() 
	{
		String richtung = MeineEingabe.erfasseText("Wie moechten Sie ziehen (/, \\):\t", "[/\\\\]");
		int anzahlfelder = MeineEingabe.erfasseInt("Wie viele Felder (> 0: nach rechts oben; < 0: nach links unten):\t", -7, 7);
		
		if (richtung.equals("/")) 
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

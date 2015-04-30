/** 
 * Repräsentiert eine Spielfigur, die aus ihrer Position (Zeile und Spalte) und ihrem Namen besteht.
 * @author Sophia Mühling
 */
public abstract class Spielfigur
{	
	/** Die Zeile, in der die Spielfigur positioniert ist.*/
	private int zeile = 1;
	/** Die Spalte, in der die Spielfigur positioniert ist.*/
	private int spalte = 1;
	/** Der Name der Spielfigur.*/
	private String name = " Die Dame";

	/** 
	 * Erzeugt eine Spielfigur mit als Parameter übergebener Position 
	 * (Zeile und Spalte, beide mit Werten zwischen 0 und 8) und einem Namen.
	 * @param 	zeile			Die Zeile, in der die Spielfigur positioniert ist.
	 * @param 	spalte		Die Spalte, in der die Spielfigur positioniert ist.
	 * @param 	name			Der Name der Spielfigur.
	 * @throws 	IllegalArgumentException 	Der Wert der Zeile, in der die Spielfigur positioniert ist, muss zwischen 1 und 8 liegen.
	 * @throws 	IllegalArgumentException 	Der Wert der Spalte, in der die Spielfigur positioniert ist, muss zwischen A und H, bzw. 1 und 8 liegen.
	 * @throws 	IllegalArgumentException	Der Name der Spielfigur muss vorhanden sein.
	 */
	public Spielfigur(int zeile, int spalte, String name)
	{
		if (zeile > 8 || zeile < 1)
		{
			throw new IllegalArgumentException("Der Wert der Zeile, in der die Spielfigur positioniert ist, muss zwischen 1 und 8 liegen.");
		}
		this.zeile = zeile;
		
		if (spalte > 8 || spalte < 1)
		{
			throw new IllegalArgumentException("Der Wert der Spalte, in der die Spielfigur positioniert ist, muss zwischen A und H, bzw. 1 und 8 liegen.");
		}
		this.spalte = spalte;
		if (name == null || name.isEmpty())
		{
			throw new IllegalArgumentException("Der Name der Spielfigur muss vorhanden sein.");
		}
		this.name = name;
	}
	
	/** 
	 * Liefert den Namen der Spielfigur.
	 * @return Der Name der Spielfigur.
	 */
	public String getName() 
	{
		return name;
	}
	
	/** 
	 * Liefert die Spalte, in der die Spielfigur positioniert ist.
	 * @return Die Spalte, in der die Spielfigur positioniert ist.
	 */
	public int getSpalte() 
	{
		return spalte;
	}
	
	/** 
	 * Ändert die Spalte, in der die Spielfigur positioniert ist, 
	 * in eine als Parameter übergebene Spalte.
	 * @param spalte	Die Spalte, in der die Spielfigur positioniert ist.
	 */
	public void setSpalte(int spalte) 
	{
		this.spalte = spalte;
	}
	
	/** 
	 * Liefert die Zeile, in der sich die Spielfigur befindet.
	 * @return Die Zeile, in der sich die Spielfigur befindet.
	 */
	public int getZeile() 
	{
		return zeile;
	}
	
	/** 
	 * Ändert die Zeile, in der sich die Spielfigur befindet, 
	 * in eine als Parameter übergebene Zeile.
	 * @param zeile		Die neue Zeile, in der sich die Spielfigur befindet.
	 */
	public void setZeile(int zeile) 
	{
		this.zeile = zeile;
	}
	
	/** 
	 * Liefert die Position (Zeile und Spalte) der Spielfigur.
	 * @return Die Position (Zeile und Spalte) der Spielfigur.
	 */
	public String getPosition() 
	{
		return String.format("%c%d", spalte + 64, zeile);
	}
	
	/** Bewegt die Figur auf dem Spielfeld. */
	abstract void ziehe();

	/** 
	 * Prüft mit Hilfe der Beuteposition (Zeile und Spalte), 
	 * ob die Spielfigur die Beute im nächsten Zug treffen könnte.
	 * @param 	beutezeile 	 	Die Zeile, in der sich die Beute befindet.
	 * @param 	beutespalte 	Die Spalte, in der sich die Beute befindet.
	 * @return Die Aussage, ob die Spielfigur die Beute im nächsten Zug treffen könnte.
	 */
	abstract boolean kannBeuteTreffen(int beutezeile, int beutespalte);

	/** 
	 * Prüft mit Hilfe der Beuteposition (Zeile und Spalte), 
	 * ob die Spielfigur die Beute getroffen hat.
	 * @param 	beutezeile 	 	Die Zeile, in der sich die Beute befindet.
	 * @param 	beutespalte 	Die Spalte, in der sich die Beute befindet.
	 * @return Die Aussage, ob die Spielfigur die Beute getroffen hat.
	 */
	public boolean hatBeuteGetroffen(int beutezeile, int beutespalte) 
	{
		return (getSpalte() == beutespalte && getZeile() == beutezeile);
	}
	
	/** 
	 * Korrigiert die Position (Zeile und Spalte) der Spielfigur auf eine Position innerhalb des Spielfeldes (8 x 8 Felder)
	 * mit Hilfe der Angaben, in welche Richtung und mit welcher Orientierung (positive oder negative Anzahl der gezogenen Felder) 
	 * die Spielfigur bewegt wurde.
	 * @param 	richtung 	 			Richtung, in die die Spielfigur bewegt wurde.
	 * @param 	orientierung		Die Orientierung (positive oder negative Anzahl der gezogenen Felder) mit der die Spielfigur bewegt wurde.
	 */
	public void korrigiere(String richtung, boolean orientierung) 
	{
		if (richtung.equals("-")) 
		{
			if (getSpalte() > 8)
			{
				setSpalte(8);
			}
			else if (getSpalte() < 1)
			{
				setSpalte(1);
			}
		} 
		else if (richtung.equals("|")) 
		{
			if (getZeile() > 8)
			{
				setZeile(8);
			}
			else if (getZeile() < 1)
			{
				setZeile(1);
			}
		} 
		else 
		{
			while (!positionOkay()) 
			{
				if (richtung.equals("/")) 
				{
					setSpalte(getSpalte() + (orientierung ? 1 : -1));
					setZeile(getZeile() + (orientierung ? 1 : -1));
				} 
				else 
				{
					setSpalte(getSpalte() - (orientierung ? 1 : -1));
					setZeile(getZeile() + (orientierung ? 1 : -1));
				}
			}
		}
	}
	
	/** 
	 * Prüft, ob sich die Spielfigur an einer Position innerhalb des Spielfeldes (8 x 8 Felder) befindet.
	 * @return Die Aussage, ob sich die Spielfigur an einer Position innerhalb des Spielfeldes (8 x 8 Felder) befindet.
	 */
	private boolean positionOkay() 
	{
		return getZeile() < 9 && getZeile() > 0 && getSpalte() > 0 && getSpalte() < 9; 
	}	
} 

import java.util.Random;
import java.util.ArrayList;

/** 
 * Repräsentiert ein Jäger-Beute-Spiel, das aus der Liste der Jäger-Spielfiguren besteht.
 * @author Sophia Mühling
 */
public class JaegerBeuteSpiel
{
	/** Liste der Jäger-Spielfiguren */
	private ArrayList<Spielfigur> jaeger = new ArrayList<Spielfigur>();
	
	/** Erzeugt ein Jäger-Beute-Spiel */
	public JaegerBeuteSpiel() 
	{
		Random r = new Random();
		int beute_spalte = r.nextInt(8) + 1;
		int beute_zeile = r.nextInt(8) + 1;		
		initialisiereJaeger();
		//System.out.printf("\nBeuteposition: %c%d\n", beute_spalte + 64, beute_zeile);
		boolean spielende = false;
		int zug = 0;
		while (!spielende) 
		{
			for (Spielfigur j : jaeger) 
			{
				if (zug > 0)
				{
					System.out.printf("\n\nIhr %d.Zug.\n", zug);
					j.ziehe();
				}
				System.out.println(j.getName() + " befindet sich im Feld " + j.getPosition() + ".");
				if (j.hatBeuteGetroffen(beute_zeile, beute_spalte))
				{
					System.out.printf("\nHerzlichen Glueckwunsch! Sie haben die Beute auf Position %c%d getroffen.\n", beute_spalte + 64, beute_zeile);
					spielende = true;
				}
				else if (zug >= 10)
				{
					System.out.printf("\nSchade! Sie haben die Beute auf Position %c%d in %d Zuegen nicht getroffen.\n", beute_spalte + 64, beute_zeile, zug);
					spielende = true;
				}
				else 
				{
					if (j.kannBeuteTreffen(beute_zeile, beute_spalte))
					{
						System.out.println("Die Beute kann im naechsten Zug getroffen werden.");			
					}
					else
					{
						System.out.println("Die Beute ist woanders.");
					}
				}
				zug++;
			}
		}
	}
	
	/** Initialisiert den Jäger (Spielfigurauswahl und erste Positionsangabe). */
	private void initialisiereJaeger() 
	{
		int jaegerTyp = MeineEingabe.erfasseInt("\nBitte waehlen Sie Ihren Jaeger:\n(1) Dame\n(2) Turm\n(3) Laeufer\nAuswahl:\t", 1, 3);
		String stringSpalte = MeineEingabe.erfasseText("\nBitte setzen Sie den Jaeger:\nSpalte [A-H]:\t", "[ABCDEFGH]");
		int spalte = stringSpalte.charAt(0) - 64;
		int zeile = MeineEingabe.erfasseInt("Zeile [1-8]:\t", 1, 8);
		
		switch(jaegerTyp) 
		{
			case 1: 
				jaeger.add(new Dame(zeile, spalte));
				break;
			case 2: 
				jaeger.add(new Turm(zeile, spalte));
				break;
			case 3: 
				jaeger.add(new Laeufer(zeile, spalte, true));
				int zeile1 = zeile;
				int spalte1= spalte;
				boolean hatAndereFarbe = false;
				while (!hatAndereFarbe)
				{
					stringSpalte = MeineEingabe.erfasseText("\nBitte setzen Sie den Jaeger:\nSpalte [A-H]:\t", "[ABCDEFGH]");
					spalte = stringSpalte.charAt(0) - 64;
					zeile = MeineEingabe.erfasseInt("Zeile [1-8]:\t", 1, 8);
					if (((spalte + spalte1) % 2 != 0 && (zeile + zeile1) % 2 == 0) || ((spalte + spalte1) % 2 == 0 && (zeile + zeile1) % 2 != 0))
					{
						hatAndereFarbe = true;
					}
					else
					{
						System.out.println("Der zweite Laufer muss sich auf einem andersfarbigen Feld befinden.");
					}
				}
				jaeger.add(new Laeufer(zeile, spalte, false));
				break;
				
			default: 
				jaeger.add(new Dame(zeile, spalte));
				break;
		}
	}
}

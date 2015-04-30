import java.io.*;

/**
 * Programm zum Ausführen eines Jäger-Beute-Spiels:
 * Auf einem unsichtbaren Schachbrett wird die Beute per Zufall auf eines der Felder positioniert.
 * Anschließend wird der Benutzer aufgefordert, über die Tastatur den (oder die) Jäger auszuwählen 
 * (Dame, Turm oder zwei Läufer) und ihn über Eingabe der Spalte (A-H) und Zeile (1-8) auf dem 
 * unsichtbaren Schachbrett zu positionieren.
 * Falls die Beute noch nicht getroffen wurde, wird der Benutzer aufgefordert, durch Eingabe 
 * einer Ziehrichtung und der Anzahl der zu ziehenden Felder die Figur(en) des Jägers zu ziehen,
 * um die Beute zu treffen. Dabei ist zu beachten, dass die Beute "übersprungen" werden kann, 
 * die Position des Jägers korrigiert wird falls er "aus dem Spielfeld hinaus gezogen" wird und 
 * der Jäger je nach Wahl der Spielfigur nur in bestimmte Richtungen ziehen kann 
 * (Läufer : / oder \; Turm: - oder |; Dame: in alle Richtungen /,\,- und |).
 * Zu Beginn und für jeden neuen Versuch, die Beute zu treffen wird auf dem Bildschirm die aktuelle Position des Jägers 
 * und eine Mitteilung ausgegeben, ob die Beute getroffen wurde oder ob der Jäger die Beute 
 * im nächsten Zug treffen kann.
 * Das Spiel endet, wenn der Jäger die Beute trifft oder in 10 Zügen nicht erreicht wurde.
 * Nach Ende eines Spiels wird der Benutzer aufgefordert, über die Tastatur einzugeben,
 * ob er ein weiteres Spiel spielen oder das Programm beenden möchte.
 */
public class JaegerBeuteMain
{
	/** Programm zum Ausführen eines Jäger-Beute-Spiels.*/
	public static void main(String[] args) 
	{
		boolean beendeProgramm = false;
		while (!beendeProgramm)
		{
			System.out.println("\nJaeger-Beute-Spiel:");
			JaegerBeuteSpiel jaegerBeuteSpiel = new JaegerBeuteSpiel();
			int auswahl = MeineEingabe.erfasseInt("\nMoechten Sie nochmal spielen (1) oder das Programm beenden (2)?\t", 1, 2);
			if (auswahl == 2)
			{
				beendeProgramm = true;
			}
		}
	}
}	

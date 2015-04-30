import java.io.*;
import java.lang.String;
/**
 * Programm zum Ausführen eines Mastermindspiels:
 * Der Benutzer wird aufgefordert, die Stellenanzahl (1-9) der zu erratenden Zahl 
 * und die Anzahl der Rateversuche (1-20) über die Tastatur einzugeben.
 * Desweiteren kann der Benutzer über die Tastatur wählen, ob er einen per Zufallszahlengenerator erzeugten Code erraten (1)
 * oder den Computer eine über die Tastatur eingegebene Zahl erraten lassen möchte (2).
 * Der zu erratende Code darf nur aus den Ziffern 0-9 bestehen, wobei jede Ziffer nur einmal im Code vorkommen darf.
 * Bei Wahl der Spielvariante 1 wird ein Zufallscode mit gewünschter Stellenanzahl generiert. 
 * Danach wird der Benutzer über die Tastatur aufgefordert, diesen Code zu erraten. Dabei werden nur Eingaben akzeptiert, die die gewünschte Stellenanzahl haben.
 * Bei Wahl der Spielvariante 2 wird der Benutzer über die Tastatur aufgefordert, einen Code mit gewünschter Stellenanzahl einzugeben.
 * Dieser Code wird nun vom Computer erraten. Dabei verfolgt der Computer folgende Strategie: 
 * Der Reihe nach, von 0001 bis 9999, wird nach dem nächsten "guten" Kandidat für einen Rateversuch gesucht,
 * der als nächster Versuch zum Raten des gesuchten Codes benutzt wird. Dabei ist ein Kandidat für den n-ten Versuch genau dann "gut", 
 * wenn jede Ziffer von 0-9 im Kandidaten höchstens einmal vorkommt und für jeden vorangehenden Rateversuch 
 * das Ergebnis des Vergleichs von dem Kandidaten mit allen vorangehenden Rateversuchen identisch mit dem Ergebnis des Vergleichs 
 * von allen vorangehenden Rateversuchen mit dem gesuchten Code ist.
 * Auf dem Bildschirm werden vor jedem Rateversuch die vorhergehenden Rateversuche und ihr Ergebnis ausgeben. 
 * Das Ergebnis eines Rateversuchs besteht aus der Angabe, wie viele der Ziffern des Rateversuchs sich an derselben Stelle befinden,
 * wie im gesuchten Code, und wie viele weitere Ziffern des Rateversuchs im gesuchten Code vorkommen,
 * sich dort aber an anderer Stelle befinden.
 * Wenn der Benutzer (bzw. der Computer) nach der gewünschten Anzahl der Rateversuche keinen Code rät, 
 * der mit dem gesuchten Code an allen Stellen übereinstimmt, ist das Spiel beendet und auf dem Bildschirm wird ein tröstender Satz ausgegeben.
 * Falls der Benutzer (bzw. der Computer) vorher den Code rät, der mit dem gesuchten Code an allen Stellen übereinstimmt,
 * ist das Spiel beendet und auf dem Bildschirm wird ein Glückwunschsatz mit Angabe der Anzahl der benötigten Versuche ausgegeben.
 * Wenn ein Spiel beendet ist, wird der Benutzer aufgefordert über die Tastatur zu wählen, ob er ein weiteres Spiel spielen oder
 * das Programm beenden möchte.
 * 
 */
public class MastermindMain
{
	/** Programm zum Ausführen eines Mastermindspiels.*/

	public static void main(String[] args)
	{		
		boolean beendeProgramm = false;
		while (!beendeProgramm)
		{
			System.out.println("\nMastermind:");
			int stellenanzahl = MeineEingabe.erfasseInt("\nWie viele Stellen soll die zu erratende Zahl haben? [1 - 9]\t", 1, 9);
			int versuche = MeineEingabe.erfasseInt("\nWie viele Rateversuche sollen zur Verfügung stehen? [1 - 20]\t", 1, 20);
			int spielvariante = MeineEingabe.erfasseInt("\nMöchten Sie eine vom Computer generierte Zahl raten [1]\noder möchten Sie den Computer eine von Ihnen eingegebene Zahl erraten lassen [2]?\t", 1, 2);
			MastermindSpiel mastermind = new MastermindSpiel(spielvariante, versuche, stellenanzahl);
			int beenden = MeineEingabe.erfasseInt("\n\nMöchten Sie das Programm beenden [1] oder ein neues Spiel beginnen [2]?\t", 1, 2);
			if (beenden == 1)
			{
				beendeProgramm = true;
			}			
		}
	}
}

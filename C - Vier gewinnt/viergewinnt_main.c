#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "eingabe.h"
#include "viergewinnt_funktionen.h"

/**Vier gewinnt:
 *
 * Die Benutzer werden aufgefordert, über die Tastatur einzugeben ob sie ein neues Spiel spielen (1) oder das Spiel beenden möchten (2).
 * Solange die Benutzer spielen möchten, werden sie zunächst aufgefordert, ihre beiden Nutzernamen einzugeben. Danach wird eingegeben,
 * welche Größe (Breite und Höhe) das Spielfeld haben soll, wobei die Breite mindestens 7 Spalten und die Höhe mindestens 6 Zeilen umfassen sollte.
 * Mit den eingegebenen Werten zur Breite und Höhe wird ein Spielfeld erstellt, dessen Werte auf dem Heap abgelegt werden.
 * Das zunächst leere Spielfeld wird auf dem Bildschirm ausgegeben.
 * Abwechselnd werden die beiden Benutzer aufgefordert, eine Spielfeldspalte auszuwählen, in der sie ihren nächsten Spielstein platzieren möchten.
 * Es wird überprüft, ob in dieser Spalte noch eine Zeile zur Platzierung eines Spielsteins frei ist und die erste freie Stelle für die Platzierung ausgewählt.
 * Falls keine Stelle in der ausgewählten Spalte mehr frei ist, wird der Benutzer, der gerade am Zug ist, aufgefordert,
 * eine andere Spaltennummer einzugeben, in der sein Spielstein platziert werden soll.
 * Nach jedem Spielzug wird das neu befüllte Spielfeld auf dem Bildschirm ausgegeben und überprüft, ob vier Spielsteine eines Benutzers
 * horizontal, vertikal oder diagonal nebeneinander platziert wurden.
 * Ist dies der Fall, wird das Spiel abgebrochen und der Name des Siegers wird in einem Glückwunschsatz ausgegeben.
 * Falls kein Sieger gefunden wird, wechseln sich die Benutzer solange mit der Spielstein-Eingabe ab, bis das Spielfeld vollständig gefüllt ist.
 * In diesem Fall wird ein Satz ausgegeben, der signalisiert das das Spiel unentschieden ist.
 * Wenn ein Glückwunsch- oder "Unentschieden"-Satz ausgegeben wurde, werden die Benutzer erneut aufgefordert, einzugeben, 
 * ob sie ein neues Spiel spielen oder das Spiel beenden möchten.
 * Wenn sie das Spiel beenden möchten, wird ein Abschiedssatz auf dem Bildschirm ausgegeben und das Programm beendet.
 *
 * @author	Sophia Mühling
 * @version	1.0
 */

int main(void)
{
	bool beende_programm = false;
	while (!beende_programm)
	{
		int option = int_eingabe("Neues Spiel [1] oder Programm beenden [2]:");
		char *spieler1= malloc(40 * sizeof (char));
		char *spieler2= malloc(40 * sizeof (char));
		if (option == 1)
		{
			erfasse_spieler_namen(spieler1, spieler2);
			int spielfeld_breite = int_eingabe_minmax("Wie breit soll das Spielfeld sein?", 7, 20);
			int spielfeld_hoehe = int_eingabe_minmax("Wie hoch soll das Spielfeld sein?", 6, 20);
			int **spielfeld = erstelle_spielfeld(spielfeld_breite, spielfeld_hoehe);
			int spielverlauf = 0;
			intitalisiere_spielfeld(spielfeld_breite, spielfeld_hoehe, spielfeld);
			ausgabe_spielfeld(spielfeld_breite, spielfeld_hoehe, spielfeld);
			int zug = 0;
			int maximale_zuganzahl = spielfeld_breite * spielfeld_hoehe;
			int reihe = 0;
			int spieler_stein = 0;
			while ((spielverlauf == 0) && (zug < maximale_zuganzahl))
			{
				if ((zug % 2) == 0)
				{
					printf("%s: ", spieler1);
					spieler_stein = 1;
				}
				else
				{
					printf("%s: ", spieler2);
					spieler_stein = -1;
				}
				int eingabe_spalte = int_eingabe_minmax("In welcher Spalte möchten Sie ihren Stein platzieren?", 1, spielfeld_breite);
				reihe = ermittle_freie_spaltenstelle(eingabe_spalte - 1, spielfeld_hoehe, spielfeld);
				if (reihe >= 0)
				{
					spielfeld[eingabe_spalte -1][reihe] = spieler_stein;
					ausgabe_spielfeld(spielfeld_breite, spielfeld_hoehe, spielfeld);
					spielverlauf = pruefe_auf_gewinner(spielfeld_breite, spielfeld_hoehe, spielfeld);
					zug++;
				}
				else
					printf("Spalte %d ist voll. Bitte eine Andere auswählen.\n", eingabe_spalte);
			}
			ausgabe_sieger(spielverlauf, spieler1, spieler2);
			free(spieler1);
			free(spieler2);
		}
		if (option == 2)
		{
			printf("Bis zum nächsten Mal!\n");
			beende_programm = true;
		}
		else
			printf("Bitte wählen Sie zwischen Option 1 und 2.");
	}
}



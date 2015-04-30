#include <stdio.h>
#include <stdlib.h>
#include "viergewinnt_funktionen.h"
#include "eingabe.h"

/**Erfasst die Spielernamen, die über die Tastatur eingegeben werden.
 *
 * @param spieler1 Name des 1. Spielers
 * @param spieler2 Name des 2. Spielers
 */

void erfasse_spieler_namen(char *spieler1, char *spieler2)
{
	printf("\nName Spieler 1: ");
	scanf("%s", spieler1);   
	printf("\nName Spieler 2: ");
	scanf("%s", spieler2);
}

/**Stellt dynamischen Speicher für das Spielfeld bereit.
 *
 * @param	spielfeld_breite	die Anzahl der Spalten
 * @param	spielfeld_hoehe		die Anzahl der Zeilen
 *
 * @return der freigebene Speicher für das Spielfeld
 */

int **erstelle_spielfeld(int spielfeld_breite, int spielfeld_hoehe)
{
	int **spielfeld = (int**) malloc(spielfeld_breite * sizeof(int *));
	for (int i = 0; i < spielfeld_breite; i++)
		spielfeld[i] = (int*) malloc(spielfeld_hoehe * sizeof(int));
	return spielfeld;
}

/**Die einzelnen Felder des Spielfeldes werden mit dem Wert Null initialisiert.
 *
 * @param	spielfeld_breite	die Anzahl der Spalten
 * @param	spielfeld_hoehe		die Anzahl der Zeilen
 * @param	spielfeld					Koordinate auf dem Spielfeld
 */

void intitalisiere_spielfeld(int spielfeld_breite, int spielfeld_hoehe, int **spielfeld)
{
	for (int i = 0; i < spielfeld_breite; i++)
	{
		for (int k = 0; k < spielfeld_hoehe; k++)
			spielfeld[i][k] = 0;
	}
}

/**Gibt das Spielfeld auf dem Bildschirm aus.
 *
 * @param	spielfeld_breite	die Anzahl der Spalten
 * @param	spielfeld_hoehe		die Anzahl der Zeilen
 * @param	spielfeld					Koordinate auf dem Spielfeld
 */

void ausgabe_spielfeld(int spielfeld_breite, int spielfeld_hoehe, int **spielfeld)
{
	printf("\n");
	for (int i = 0; i < ((2 * spielfeld_hoehe) + 1); i++)
	{
		if (i % 2 == 0)
			schreibe_trennzeichen(spielfeld_breite);
		else
			schreibe_spielfeld(spielfeld_breite, (i - 1) / 2, spielfeld);
	}
	schreibe_spalten_nummer(spielfeld_breite);
	printf("\n");
}

/**Gibt die horizontale Trennzeichenlinie für das Spielfeld auf dem Bildschirm aus.
 *
 * @param	spielfeld_breite	die Anzahl der Spalten
 */

void schreibe_trennzeichen(int spielfeld_breite)
{
	for (int i = 0; i < spielfeld_breite; i++)
		printf("+---");
	printf("+\n");
}

/**Gibt die einzelnen Felder des Spielfeldes auf dem Bildschirm aus.
 *
 * @param	spielfeld_breite	die Anzahl der Spalten
 * @param	spielfeld_hoehe		die Anzahl der Zeilen
 * @param	spielfeld					Koordinate auf dem Spielfeld
 */

void schreibe_spielfeld(int spielfeld_breite, int spielfeld_hoehe, int **spielfeld)
{
	for (int i = 0; i < ((spielfeld_breite * 4) + 1); i++)
	{
		if (i % 4 == 0)
		{
			printf("|");
		}
		else if ((i % 4 == 1) || (i % 4 == 3))
		{
			printf(" ");
		}
		else if (i % 4 == 2)
		{
			if (spielfeld[(i - 2) / 4][spielfeld_hoehe] == 0)
				printf(" ");
			else if(spielfeld[(i - 2) / 4][spielfeld_hoehe] == 1)
				printf("X");
			else
				printf("O");
		}
	}
	printf("\n");
}

/**Gibt die Spaltennummern des Spielfelds auf dem Bildschirm aus.
 *
 * @param	spielfeld_breite	die Anzahl der Spalten
 */

void schreibe_spalten_nummer(int spielfeld_breite)
{
	for (int i = 1; i < spielfeld_breite + 1; i++)
	{
		printf("| ");
		if (i < 10)
			printf("%d ", i);
		else
			printf("%d", i);
	}
	printf("|\n");
}

/**Ermittelt, die erste Stelle von unten in der angegebenen Spalte, in der noch kein Stein gesetzt wurde.
 *
 * @param	eingabe_spalte		die vom Benutzer angegebene Spalte
 * @param	spielfeld_hoehe		die Anzahl der Zeilen
 * @param	spielfeld					Koordinate auf dem Spielfeld
 *
 * @return die freie Spaltenstelle
 */

int ermittle_freie_spaltenstelle(int eingabe_spalte, int spielfeld_hoehe, int **spielfeld)
{
	int freie_spaltenstelle = spielfeld_hoehe - 1;
	for (int i = spielfeld_hoehe - 1; i >= 0; i--)
	{
		if (spielfeld[eingabe_spalte][i] == 1 || spielfeld[eingabe_spalte][i] == -1)
			freie_spaltenstelle--;
	}
	return freie_spaltenstelle;
}

/**Prüft, ob vier Felder, die vertikal, horizontal oder diagonal nebeneinander liegen, den gleichen Wert haben und ermittelt daraus den weiteren Spielverlauf.
 *
 * @param	spielfeld_breite	die Anzahl der Spalten
 * @param	spielfeld_hoehe		die Anzahl der Zeilen
 * @param	spielfeld					Koordinate auf dem Spielfeld
 *
 * @return den Spielverlauf
 */

int pruefe_auf_gewinner(int spielfeld_breite, int spielfeld_hoehe, int **spielfeld)
{
	int spielverlauf = 0;

	// vertikal
	for (int i = 0; i < spielfeld_breite; i++)
	{
		for(int j = 0; j < spielfeld_hoehe - 3;j++)
		{
			if (spielfeld[i][j] == spielfeld[i][j + 1] && spielfeld[i][j + 1] == spielfeld[i][j + 2] && spielfeld[i][j + 2] == spielfeld[i][j + 3] && spielfeld[i][j] != 0)
				spielverlauf = spielfeld[i][j];
		}
	}

	// horizontal
	for(int i = 0; i < spielfeld_breite - 3; i++)
	{
		for(int j = 0; j < spielfeld_hoehe; j++)
		{
			if (spielfeld[i][j] == spielfeld[i + 1][j] && spielfeld[i + 1][j] == spielfeld[i + 2][j] && spielfeld[i + 2][j] == spielfeld[i + 3][j] && spielfeld[i][j] != 0)
				spielverlauf = spielfeld[i][j];
		}
	}

	// diagonal nw-so
	for(int i = 0; i < spielfeld_breite - 3; i++)
	{
		for(int j = 0; j < spielfeld_hoehe - 3;j++)
		{
			if (spielfeld[i][j] == spielfeld[i + 1][j + 1] && spielfeld[i + 1][j + 1] == spielfeld[i + 2][j + 2] && spielfeld[i + 2][j + 2] == spielfeld[i + 3][j + 3] && spielfeld[i][j] != 0)
				spielverlauf = spielfeld[i][j];
		}
	}

	// diagonal nw-so
	for(int i = spielfeld_breite - 1; i >= 3; i--)
	{
		for(int j = 0;j < spielfeld_hoehe - 3; j++)
		{
			if (spielfeld[i][j] == spielfeld[i - 1][j + 1] && spielfeld[i - 1][j + 1] == spielfeld[i - 2][j + 2] && spielfeld[i - 2][j + 2] == spielfeld[i - 3][j + 3] && spielfeld[i][j] != 0)
				spielverlauf = spielfeld[i][j];
		}
	}
	return spielverlauf;
}

/**Wenn es nach einem bestimmten Spielverlauf einen Sieger gibt, wird eine Glückwunschmeldung mit dem Namen des Siegers auf dem Bildschirm ausgegeben.
 * Gibt es zum Spielende keinen Sieger, wird eine Untentschiedenmeldung auf dem Bildschirm ausgegeben.
 *
 * @param	spielverlauf	der Spielverlauf
 * @param	spieler1 			Name des 1. Spielers
 * @param	spieler2 			Name des 2. Spielers
 */

void ausgabe_sieger(int spielverlauf, char *spieler1, char *spieler2)
{
	if (spielverlauf == 1)
		printf("Glückwunsch! %s hat gewonnen!\n", spieler1);
	else if (spielverlauf == -1)
		printf("Glückwunsch! %s hat gewonnen!\n", spieler2);
	else
		printf("Unentschieden!\n");
}

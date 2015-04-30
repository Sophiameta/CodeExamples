#include <stdio.h>
#include <stdbool.h>
#include "eingabe.h"

/** Leert die Eingabe bis zum Zeichen '\\n'. */
void leere_buffer(void)
{
	while (getchar()!='\n')
		;
}

/**
 * Über die Tastatur eingegebene Integerwerte werden über Eingabeaufforderungen erfasst.
 *
 * @param	 eingabeaufforderung		die Eingabeaufforderung
 *
 * @return			der eingegebene Wert
 */

int int_eingabe(char *eingabeaufforderung)
{
	printf ("\n%s ", eingabeaufforderung);
	int eingabewert = 0;
	int pruefung = 0;
	pruefung = scanf("%d", &eingabewert);
	leere_buffer();
	while (pruefung == 0)
	{
		printf("Bitte wiederholen Sie ihre Eingabe! Sie war nicht im korrekten Bereich: ");
		pruefung = scanf("%d", &eingabewert);
		leere_buffer();
	}
	return eingabewert;
}

/**
 * Über die Tastatur eingegebene Integerwerte werden über Eingabeaufforderungen erfasst und geprüft, ob sie im korrekten Bereich liegen.
 * Der minimale und maximale Wert des Zahlenbereichs wird in die Eingabeaufforderung miteingebunden.
 *
 * @param	 eingabeaufforderung		die Eingabeaufforderung
 * @param	 min		minimaler Wert im Zahlenbereich
 * @param	 max		maximaler Wert im Zahlenbereich
 *
 * @return			der eingegebene Wert
 */

int int_eingabe_minmax(char *eingabeaufforderung, int min, int max)
{
	printf ("\n%s [%d - %d]:\t", eingabeaufforderung, min, max);
	int eingabewert = 0;
	int pruefung = 0;
	pruefung = scanf("%d", &eingabewert);
	while (getchar()!='\n')
	    ;
	while (pruefung == 0 || eingabewert < min || eingabewert > max)
	{
		printf("Bitte wiederholen Sie ihre Eingabe! Sie war nicht im korrekten Bereich: ");
		pruefung = scanf("%d", &eingabewert);
		while (getchar()!='\n')
			;
	}
	return eingabewert;
}


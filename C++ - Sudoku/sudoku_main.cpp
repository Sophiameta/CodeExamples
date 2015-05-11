#include "sudoku.h"
#include <cstring>

using namespace std;


/**
Programm zum Generieren und Lösen von Sudokus
Das Programm kann 2 verschiedenen Formen aufgerufen werden:
"./sudoku -s datei_name" um das sich in der Textdatei befindende Sudoku einzulesen und zu lösen 
oder "./sudoku -g n" um ein eindeutig loesbares Sudoku der Ordnung n (24 <= n <= 35)
inklusive seiner Lösung zu erzeugen.
Ein Sudoku der Ordnung n besteht aus einer Matrix mit 9 Zeilen und 9 Spalten, in die n Ziffern aus
dem Bereich von 1 bis 9 so eingefügt sind, dass in jeder Zeile, in jeder Spalte und in jedem markierten
Quadrat jede eingefügte Ziffer höchstens einmal vorkommt. Ein Sudoku der Ordnung n ist eindeutig lösbar, 
wenn es nur genau eine Möglichkeit gibt, die freien Zellen zu einer Lösung zu ergänzen.
*/

int main(int argc, char const *argv[])
{
	if (argc == 3 && strcmp(argv[1],"-s") == 0) 
	{
		std::string datei_name = argv[2];
		cout << "Ich löse ein Sudoku. Bitte warten..." << endl << endl;
		Sudoku sudoku(datei_name);	
		cout << "Sudoku der Ordnung " << sudoku.get_ordnung() << ":" << endl << endl;
		sudoku.zeige();
		cout << (sudoku.pruefe_eindeutig() ? "Eindeutige " : "Nicht eindeutige ") << "Lösung:" << endl << endl;
		if(sudoku.loese_aufsteigend())
		{
		    sudoku.zeige();
		}
		else
		{
		    cout << "Das Sudoku ist unlösbar!"<< endl << endl;
		}
	} 
	else if (argc == 3 && strcmp(argv[1], "-g") == 0 && atoi(argv[2]) >= 24 && atoi(argv[2]) <= 35)
	{			
		Sudoku sudoku(atoi(argv[2]));
		cout << "Generiere Sudoku mit der Lösung:" << endl << endl;
		cout << "Bitte warten..." << endl << endl;
		sudoku.generiere();
		cout << "Sudoku der Ordnung " << argv[2] << ":" << endl << endl;
		sudoku.zeige();
	} 
	else
	{
		cout << "Fehler! Bitte rufen Sie das Programm in folgender Form auf" << endl <<
			"1)./sudoku -s datei_name\tum das sich in der Textdatei befindende Sudoku einzulesen und zu lösen" << endl <<
			"2)./sudoku -g n (24 < n < 35)\tum ein eindeutig loesbares Sudoku der Ordnung n zu erzeugen" << endl;
	}
}

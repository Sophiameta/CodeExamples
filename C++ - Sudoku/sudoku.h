#include <vector>
#include <iostream>
#include <random>
#include <algorithm>
#include <fstream>
#include <sstream>

using namespace std;

/**
Repräsentiert ein Sudoku, dass aus seiner Größe, den erlaubten Werten, seiner Ordnung und seinen Feldern besteht.
*/
class Sudoku
{

private:
	/** Die Größe*/
	const int groesse = 9;
	/** Die erlaubten Werte*/
	std::vector<int> werte = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	/** Die Ordnung*/
	int ordnung;
	/** Die Felder*/
	std::vector<std::vector<int>> felder;

public:
	Sudoku(int ordnung);
	Sudoku(std::string datei_name);
	Sudoku(std::vector<std::vector<int>> f);
	bool loese_aufsteigend();
	bool loese_absteigend();
	void generiere();
	void zeige();
	bool pruefe_eindeutig();
    int get_ordnung();
	int get_feld(int i, int j);

private:
	bool pruefe_zeilen();	
	bool pruefe_spalten();
	bool pruefe_bloecke();
	bool pruefe_nummer_block(int nummer, int zeile, int spalte);
	bool pruefe_nummer_zeile(int nummer, int zeile);
	bool pruefe_nummer_spalte(int nummer, int spalte);
	bool pruefe_nummer_vector(std::vector<int> werte, int nummer);
	bool pruefe_gueltig(int zeile, int spalte, int nummer);
	int get_zufallszahl();
	void initialisiere_felder();
	
};
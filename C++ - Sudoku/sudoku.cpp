#include "sudoku.h"
#include <iostream>
#include <random>
#include <algorithm>
#include <fstream>
#include <sstream>


/**
Erzeugt ein Sudoku mit übergebener Ordnung.
@param o    Ordnung des Sudokus
*/
Sudoku::Sudoku(int o)
{
    if(o >= 24 && o <= 35)
    {
        ordnung = o;
        felder = std::vector<std::vector<int>>(groesse);
        for (int zeile = 0; zeile < groesse; ++zeile)
        {
            felder[zeile] = std::vector<int>(groesse);
            for (int spalte = 0; spalte < groesse; ++spalte)
            {
                felder[zeile][spalte] = 0;
            }
        }
    }
    else 
    {
        cout << "Das Sudoku konnte nicht erzeugt werden!" << endl;
    }
}

/**
Erzeugt ein Sudoku mit übergebenem Dateinamen der Datei, aus der das Sudoku gelesen werden soll.
@param datei_name    Dateinamen der Datei, aus der das Sudoku gelesen werden soll
*/
Sudoku::Sudoku(string datei_name)  
{
    felder = std::vector<std::vector<int>>(groesse);
    for (int zeile = 0; zeile < groesse; ++zeile)
    {
        felder[zeile] = std::vector<int>(groesse);
        for (int spalte = 0; spalte < groesse; ++spalte)
        {
            felder[zeile][spalte] = 0;
        }
    }
    int nummer = 0;
    fstream input;
    std::vector<int> tmp;
    int a = 0;
    int o = 0;
    input.open(datei_name, std::fstream::in);
    
    if(input.is_open())
    {
        while(input >> nummer)
        {
            tmp.push_back(nummer);
            input.get();
        }
        if(tmp.size() == 81) 
        {
            for (int i = 0; i < groesse; ++i)
            {
                for (int j = 0; j < groesse; ++j)
                {
                    felder[i][j] = tmp[a];
                    if(tmp[a] > 0)
                    {
                        o++;
                    }
                    a++;
                }
            } 
            ordnung = o;
        }
        else 
        {
            cout << "Das eingelesene Sudoku hat nicht die richtige Groesse. Ein Sudoku besteht aus 81 Feldern!" << endl;
        }
        input.close();
    }
    else 
    {
        cout << "Die Datei konnte nicht geoeffnet werden!" << endl;
    }
    
}

/**
Erzeugt ein Sudoku mit übergebenen Feldern des Sudokus.
@param f    Felder des Sudokus
*/
Sudoku::Sudoku(std::vector<std::vector<int>> f)
{
    int x = f.size();
    if (x == groesse)
    {
        felder = f;
    }
    else 
    {
        cout << "Das Sudoku konnte nicht erzeugt werden!" << endl;
    }
}

/**
Überprüft, ob das Sudoku aufsteigend (die Zellen des Sudokus werden mit Ziffern in der Reihenfolge 
von 1 bis 9 belegt) mit einem rekursiven Backtracking-Verfahren gelöst werden kann.
@return wahr, wenn es gelöst werden kann; falsch, wenn nicht
*/
bool Sudoku::loese_aufsteigend() 
{
    bool gefunden = false;
    bool zurueck= false;
    bool unloesbar = false;

    std::vector<int> position;

    for (int zeile = 0; zeile < groesse; ++zeile)
    {
        for (int spalte = 0; spalte < groesse; ++spalte)
        {
            if(!unloesbar && (felder[zeile][spalte] == 0 || zurueck)) 
            {
                gefunden = false;
                int nummer = zurueck? (felder[zeile][spalte] + 1) : 1;
                while (nummer < 10)
                {
                    if(pruefe_gueltig(nummer, zeile, spalte) && gefunden == false)
                    {
                        felder[zeile][spalte] = nummer;
                        zurueck = false;
                        position.push_back(zeile * 9 + spalte);
                        gefunden = true;
                    }
                    ++nummer;
                }
                if (zurueck || !gefunden) 
                {
                    zurueck= true;
                    felder[zeile][spalte] = 0;
                    if (!position.empty())
                    {
                        zeile = position.back() / 9;
                        spalte = (position.back() % 9) - 1;
                        position.pop_back();
                    }   
                    else 
                    {
                        unloesbar = true;
                    }
                }   
            }
        }
    }
    return gefunden;
}

/**
Überprüft, ob das Sudoku absteigend (die Zellen des Sudokus werden mit Ziffern in der Reihenfolge 
von 9 bis 1 belegt) mit einem rekursiven Backtracking-Verfahren gelöst werden kann.
@return wahr, wenn es gelöst werden kann; falsch, wenn nicht
*/
bool Sudoku::loese_absteigend() 
{ 
    bool gefunden = false;
    bool zurueck= false;
    bool unloesbar = false;

    std::vector<int> position;

    for (int zeile = 0; zeile < groesse; ++zeile)
    {
        for (int spalte = 0; spalte < groesse; ++spalte)
        {
            if(!unloesbar && (felder[zeile][spalte] == 0 || zurueck)) 
            {
                gefunden = false;
                int nummer = zurueck? felder[zeile][spalte] -1 : 9;
                if (nummer > 0 && nummer < 10)
                {
                    while (nummer > 0)
                    {
                        if(pruefe_gueltig(nummer, zeile, spalte) && gefunden == false) 
                        {
                            felder[zeile][spalte] = nummer;
                            zurueck= false;
                            position.push_back(zeile * 9 + spalte);
                            gefunden = true;
                        }
                        --nummer;
                    }
                }
                if (zurueck || !gefunden ) 
                {
                    zurueck= true;
                    felder[zeile][spalte] = 0;
                    if (!position.empty())
                    {
                        zeile = position.back() / 9;
                        spalte = (position.back() % 9) - 1;
                        position.pop_back();
                    }   
                    else 
                    {
                        unloesbar = true;
                    } 
                }   
            }
        }
    }
    return gefunden;
}


/**
Generiert ein eindeutig lösbares Sudoku.
*/
void Sudoku::generiere() 
{
    static std::random_device rd;
    static std::mt19937 g(rd());
    std::shuffle(werte.begin(), werte.end(), g);
    int zaehler = 0;
    int i = 0;
    for (int i = 0; i < 9; ++i)
    {
        felder[i][i] = werte.at(i);
    }
    loese_aufsteigend();
    zeige();
    while (zaehler < (81 - ordnung)) 
    {
        int zeile = get_zufallszahl() - 1;
        int spalte = get_zufallszahl() - 1;
        int tmp_wert = 0;
        
        if (felder[zeile][spalte] != 0) 
        {
            tmp_wert = felder[zeile][spalte];
            felder[zeile][spalte] = 0;
            if (!pruefe_eindeutig())
            {
                felder[zeile][spalte] = tmp_wert;   
            } 
            else
            {
                zaehler++;
            }
        }
        i++;
    }
}

/**
Gibt das Sudoku lesbar aus.
*/
void Sudoku::zeige()
{
    for (int zeile = 0; zeile < groesse; ++zeile)
    {
        for (int spalte = 0; spalte < groesse; ++spalte)
        {
            cout << felder[zeile][spalte] << " " << ((spalte + 1) % 3 == 0 ? " " : "");
        }
        cout << endl << ((zeile + 1) % 3 == 0 ? "\n" : "");
    }
}

/**
Überprüft, ob das Sudoku eindeutig lösbar ist.
@return wahr, wenn das Sudoku eindeutig lösbar ist; falsch, wenn nicht
*/
bool Sudoku::pruefe_eindeutig() 
{
    Sudoku aufsteigend(felder);
    Sudoku absteigend(felder);
    aufsteigend.loese_aufsteigend();
    //aufsteigend.zeige();
    absteigend.loese_absteigend();
    //absteigend.zeige();
    bool gleich = true;
    for (int i = 0; i < groesse; ++i)
    {
        for (int j = 0; j < groesse; ++j)
        {
            gleich = (gleich && (aufsteigend.get_feld(i,j) == absteigend.get_feld(i,j)) && (aufsteigend.get_feld(i,j) != 0));
        }
    }
    return gleich;
}

/** 
Liefert die Ordnung des Sudokus.
@return Die Ordnung des Sudokus.
*/
int Sudoku::get_ordnung()
{
    return ordnung;
}

/** 
Liefert die Ordnung des Sudokus.
@return Die Ordnung des Sudokus.
*/
int Sudoku::get_feld(int i, int j)
{
    return felder[i][j];
}

/**
Initialisiert alle Felder des Sudokus mit 0.
*/
void Sudoku::initialisiere_felder()
{
    for (int j = 0; j < groesse; ++j)
    {
        for (int i = 0; i < groesse; ++i)
        {
            felder[i][j] = 0;
        }
    }
}

/**
Überprüft, ob die Nummer in dem zu überprüfenden Feld stehen darf, 
welches durch die übergebene Zeile und Spalte definiert wird.
@param nummer die Nummer 
@param zeile die Zeile
@param spalte die Spalte
@return wahr, Nummer in dem Feld stehen darf; falsch, wenn nicht
*/
bool Sudoku::pruefe_gueltig(int nummer, int zeile, int spalte) 
{
    return !pruefe_nummer_spalte(nummer, spalte) &&
           !pruefe_nummer_zeile(nummer, zeile) &&
           !pruefe_nummer_block(nummer, zeile, spalte);
}

/**
Überprüft, ob die Nummer bereits in dem Block steht, in dem das zu überprüfende Feld liegt, 
welches durch die übergebene Zeile und Spalte definiert wird.
@param nummer die Nummer 
@param zeile die Zeile
@param spalte die Spalte
@return wahr, wenn die Nummer bereits in dem Block steht; falsch, wenn nicht
*/
bool Sudoku::pruefe_nummer_block(int nummer, int zeile, int spalte) 
{
    std::vector<int> block;
    int block_id = (spalte /  3 + ((zeile / 3) * 3));

    for (int j = (block_id % 3) * 3; j <= 2 + (block_id % 3) * 3; ++j)
    {
        for (int i = (block_id / 3) * 3 ; i <= 2 + (block_id / 3) * 3 ; ++i)
        {
            block.push_back(felder[i][j]);
        }  
    }
    return pruefe_nummer_vector(block, nummer);
}


/**
Überprüft, ob die Nummer bereits in der Zeile steht.
@param nummer die Nummer 
@param zeile die Zeile
@return wahr, wenn die Nummer bereits in der Zeile steht; falsch, wenn nicht
*/
bool Sudoku::pruefe_nummer_zeile(int nummer, int zeile) 
{
    static std::vector<int> v_in_zeile(9);
    for (int spalte = 0; spalte < 9; ++spalte)
    {
        v_in_zeile[spalte] = felder[zeile][spalte];  
    }
    return pruefe_nummer_vector(v_in_zeile, nummer);
}

/**
Überprüft, ob die Nummer bereits in der Spalte steht.
@param nummer die Nummer 
@param spalte die Spalte
@return wahr, wenn die Nummer bereits in der Spalte steht; falsch, wenn nicht
*/
bool Sudoku::pruefe_nummer_spalte(int nummer, int spalte) 
{
    static std::vector<int> v_in_col(9);
    for (int zeile = 0; zeile < 9; ++zeile)
    {
        v_in_col[zeile] = felder[zeile][spalte];  
    }
    return pruefe_nummer_vector(v_in_col, nummer);
}

/**
Überprüft, ob die Nummer in dem übergebenen Vektor steht.
@param nummer die Nummer 
@param vektor der Vektor
@return wahr, wenn die Nummer in dem übergebenen Vektor steht; falsch, wenn nicht
*/
bool Sudoku::pruefe_nummer_vector(std::vector<int> v_in_vec, int nummer)
{
    return (std::find(v_in_vec.begin(), v_in_vec.end(), nummer) != v_in_vec.end());
}

/**
Liefert eine zufällige Zahl aus den erlaubten Werten im Sudoku.
@return eine zufällige Zahl aus den erlaubten Werten im Sudoku.
*/
int Sudoku::get_zufallszahl()
{
    static std::random_device rd;
    static std::mt19937 g(rd());
    std::shuffle(werte.begin(), werte.end(), g);
    return werte[0];
}

import java.io.*;
import java.util.ArrayList;
/**
* Repraesentiert einen Dienst zum Schreiben/Lesen von Objekten, bzw. ArrayLists in/aus Dateien.
* @author Sophia Muehling
*/

@SuppressWarnings("unchecked")
public class SchreibeUndLeseInDatei
{
	/**
	* Schreibt ein Objekt in eine Datei und gibt einen Booleschen Wert zurueck,
	* ob die Datei erfolgreich geschrieben wurde oder nicht.
	* @param objekt 	Das Objekt, welches in die Datei geschrieben werden soll.
	* @param datei		Der Dateiname bzw. der Pfad zur Datei.
	* @return Boolescher Wert, ob in die Datei geschrieben werden konnte oder nicht.
	*/
	public static boolean schreibeInDatei(Object objekt, String datei)
	{
		boolean erzeugt = false;
		try
		{
			FileOutputStream schreibeDatei = new FileOutputStream(datei);
			ObjectOutputStream schreibeObjekt = new ObjectOutputStream(schreibeDatei);
			schreibeObjekt.writeObject(objekt);
			schreibeObjekt.close();
			schreibeDatei.close();
			erzeugt = true; 
		}
		catch (IOException ioe)
		{
			System.out.println("Leider konnte die Datei nicht geschrieben werden.");
		}
		return erzeugt;
	}
	/**
	* Liest eine ArrayList von Objekten aus einer Datei ein und gibt diese zurueck.
	* @param datei	 Der Dateiname bzw. der Pfad zur Datei.
	* @return Die ArrayListe von Objekten aus der Datei.
	*/
	public static ArrayList<Object> leseArrayListAusDatei(String datei)
	{
		ArrayList<Object> objekt = null;
		try
		{
			InputStream leseDatei = new FileInputStream(datei);
			ObjectInputStream leseObjekt = new ObjectInputStream(leseDatei);
			try
			{
				objekt = (ArrayList<Object>) leseObjekt.readObject();
				leseObjekt.close();
				leseDatei.close();
			}
			catch (ClassNotFoundException cnfe)
			{
				System.out.println("Leider konnte die Datei nicht gelesen werden.");
			}
		}
		catch (IOException ioe)
		{
			System.out.println("Leider konnte die Datei nicht gelesen werden.");
		}
		return objekt;
	}
}
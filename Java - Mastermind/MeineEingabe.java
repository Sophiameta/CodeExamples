import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Repraesentiert die Eingabe des Benutzers.
 * @author Sophia Mühling
 */

public class MeineEingabe
{
	/** Die Eingabe */
	private static BufferedReader eingabe =
	new BufferedReader(new InputStreamReader(System.in));

	/** Erzeugt eine leere Eingabe.*/
	private MeineEingabe()
	{
	}

	/**Erfasst einen String über die Tastatur.
	 * @return Der erfasst String.
	 */
	public static String erfasseText()
	{
		return erfasseText("");
	}

	/**
	 * Erfasst einen String mit Hilfe einer Eingabeaufforderung über die Tastatur.
	 * @param	eingabeAufforderung 	die Eingabeaufforderung	
	 * @return Der erfasste String.
	 */
	public static String erfasseText(String eingabeAufforderung)
	{
		System.out.print(eingabeAufforderung);
		String text = "";
		boolean eingabeOk = false;
		while (!eingabeOk)
			try
			{
				text = eingabe.readLine();
				eingabeOk = true;
			}	
			catch (IOException ioe)
			{
			}
		return text;
	}

	/**
	* Erfasst einen String in einem gewünschten Format mit Hilfe einer Eingabeaufforderung über die Tastatur. 
	* Falls der eingebene Text nicht im gewünschten Eingabeformat ist, wird der Benutzer erneut aufgefordert, einen String einzugeben.
	*@param		eingabeAufforderung 	die Eingabeaufforderung	
	*@param 	eingabeFormat 			das gewünschte Eingabeformat
	*@return Der erfasste String.
	*/	
	public static String erfasseText(String eingabeAufforderung, String eingabeFormat)
	{	
		String text = "";
		boolean eingabeOk = false;
		while(!eingabeOk)
		{	
			try
			{
				while (!text.matches(eingabeFormat))
				{
					System.out.printf("%s", eingabeAufforderung);
					text = eingabe.readLine();
				}
				eingabeOk = true;
			}
			catch (IOException ioe)
			{
				System.out.println("Bitte gültigen Datentyp eingeben.");
			}
		}
		return text;
	}

	/**Erfasst einen Int-Wert über die Tastatur.
	 * @return Der erfasste Int-Wert.
	 */
	public static int erfasseInt()
	{
		return erfasseInt("");
	}

	/**Erfasst einen Int-Wert zwischen einem minimalen und maximalen Wert mit Hilfe einer Eingabeaufforderung über die Tastatur.
	 * Falls der eingebene Wert außerhalb des Wertebereichs liegt, wird der Benutzer erneut aufgefordert, einen Wert einzugeben.
	 * @param	eingabeAufforderung 	die Eingabeaufforderung	
	 * @param	min 	der minimale Wert
	 * @param 	max 	der maximale Wert
	 * @return Der erfasste Int-Wert.
	 */
	public static int erfasseInt(String eingabeAufforderung, int min, int max)
	{
		int wert = 0;
		boolean eingabeOk = false;
		while (!eingabeOk)
		{
			wert = erfasseInt(eingabeAufforderung);
			eingabeOk = (min <= wert && wert <= max);
		}
		return wert;
	}

	/**Erfasst einen Int-Wert mit Hilfe einer Eingabeaufforderung über die Tastatur.
	 * @param	eingabeAufforderung 	die Eingabeaufforderung	
	 * @return Der erfasste Int-Wert.
	 */
	public static int erfasseInt(String eingabeAufforderung)
	{
		int wert = 0;
		boolean eingabeOk = false;
		while (!eingabeOk)
			try
			{
				wert = Integer.parseInt(erfasseText(eingabeAufforderung));
				eingabeOk = true;
			}
			catch (NumberFormatException nfe)
			{
			}
		return wert;
	}

	/**Erfasst einen Double-Wert über die Tastatur.
	 * @return Der erfasste Double-Wert.
	 */
	public static double erfasseDouble()
	{
		return erfasseDouble("");
	}

	/**Erfasst einen Double-Wert zwischen einem minimalen und maximalen Wert mit Hilfe einer Eingabeaufforderung über die Tastatur.
	 * Falls der eingebene Wert außerhalb des Wertebereichs liegt, wird der Benutzer erneut aufgefordert, einen Wert einzugeben.
	 * @param	eingabeAufforderung 	die Eingabeaufforderung	
	 * @param	min 	der minimale Wert
	 * @param 	max 	der maximale Wert
	 * @return Der erfasste Double-Wert.
	 */
	public static double erfasseDouble(String eingabeAufforderung, double min, double max)
	{
		double wert = 0;
		boolean eingabeOk = false;
		while (!eingabeOk)
		{
			wert = erfasseDouble(eingabeAufforderung);
			eingabeOk = (min <= wert && wert <= max);
		}
		return wert;
	}

	/**Erfasst einen Double-Wert mit Hilfe einer Eingabeaufforderung über die Tastatur.
	 * @param	eingabeAufforderung 	die Eingabeaufforderung	
	 * @return Der erfasste Double-Wert.
	 */
	public static double erfasseDouble(String eingabeAufforderung)
	{
		double wert = 0;
		boolean eingabeOk = false;
		while (!eingabeOk)
			try
			{
				wert = Double.parseDouble(erfasseText(eingabeAufforderung));
				eingabeOk = true;
			}
			catch (NumberFormatException nfe)
			{
			}
		return wert;
	}
}

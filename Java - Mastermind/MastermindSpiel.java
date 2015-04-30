import java.util.ArrayList;
/** 
 * Repräsentiert ein Mastermindspiel, das aus der Liste der bereits durchgeführten Rateversuche, 
 * der Spielvariante und der Stellenanzahl des Codes besteht.
 * @author Sophia Mühling
 */
public class MastermindSpiel
{	
	/**Die Liste der bereits durchgeführten Rateversuche.*/
	private ArrayList<MastermindVersuch> tippVerlauf = new ArrayList<MastermindVersuch>();
	/**Die Spielvariante.*/
	private int spielvariante = 0;
	/**Die Stellenanzahl des Codes.*/
	private int stellenanzahl = 0;

	/** 
	 * Erzeugt ein Mastermind-Spiel mit als Parameter übergebener Spielvariante (1 oder 2), der Anzahl der Rateversuche (> 0)
	 * und der Stellenanzahl des Codes (1-9). Es werden Exeptions geworfen, falls die Werte der übergebenen Parameter nicht im gewünschten Wertebereich liegen.
	 * @param spielvariante 	Die Spielvariante.
	 * @param versuche 				Die Anzahl der Rateversuche.
	 * @param stellenanzahl 	Die Stellenanzahl des Codes.
	 * @throws	IllegalArgumentException		Die Spielvariante sollte entweder den Wert 1 oder 2 haben.
	 * @throws	IllegalArgumentException		Es muss mehr als 0 Rateversuche geben.
	 * @throws	IllegalArgumentException		Die Stellenanzahl des Codes darf nicht kleiner gleich 0 oder größer als 9 sein.
	 */
	public MastermindSpiel(int spielvariante, int versuche, int stellenanzahl)
	{
		if (spielvariante < 1 || spielvariante > 2)
		{
			throw new IllegalArgumentException( "Die Spielvariante sollte entweder den Wert 1 oder 2 haben." );
		}
		this.spielvariante = spielvariante;
		if (versuche <= 0)
		{
			throw new IllegalArgumentException( "Es muss mehr als 0 Rateversuche geben." );
		}
		if (stellenanzahl <= 0 || stellenanzahl > 9)
		{
			throw new IllegalArgumentException( "Die Stellenanzahl des Codes darf nicht kleiner gleich 0 oder größer als 9 sein." );
		}
		this.stellenanzahl = stellenanzahl;
		String codeString = this.erzeugeCodestring();
		boolean beendeSpiel = false;
		String tippString = "";
		MastermindVersuch tippVersuch = null;
		while(!beendeSpiel)
		{
			for (int i = 1; i <= versuche; i++)
			{
				if (spielvariante == 1 || i == 1)
				{
					tippString = erzeugeTippstring();
				}
				else
				{
					tippString = erzeugeKITippstring(tippString);
				}
				tippVersuch = new MastermindVersuch(codeString, tippString);
				tippVerlauf.add(tippVersuch);
				if (tippVersuch.getRichtigeStelle() == stellenanzahl)
				{
					System.out.printf("\n\nHerzlichen Glückwunsch! Der Code wurde beim %d. Versuch erraten.", i);
					i = versuche;
					beendeSpiel = true;
				}
				else if (i == versuche)
				{
					System.out.printf("\n\nSchade! Leider wurde der Code in %d Versuchen nicht erraten. Versuchen Sie es doch nocheinmal!", versuche);
					beendeSpiel = true;
				}
				else
				{
					System.out.printf("\n\nVersuch %d: ", i);
					for (int j = 0; j < tippVerlauf.size(); j++)
					{
						System.out.printf("\n%d: %s an richtiger Stelle: %d an falscher Stelle: %d", j + 1, tippVerlauf.get(j).getTippString(), tippVerlauf.get(j).getRichtigeStelle(), tippVerlauf.get(j).getRichtigeZahl());
					}
				}
			}
		}
  }

  /**
   * Erzeugt den zu erratenden Code.
   */
  private String erzeugeCodestring()
  {
  	String codeString = "";
  	if (spielvariante == 1)
  	{
  		Codegenerator code = new Codegenerator(stellenanzahl);
			codeString = code.getCodeString();
			//System.out.printf("\nZufallscode: %s", codeString);
  	}
  	else
  	{
  		while(codeString.length() != stellenanzahl)
			{
				System.out.printf("\nBitte geben Sie Ihren %d-stelligen zu erratenden Code ein!\t", stellenanzahl);
				codeString = MeineEingabe.erfasseText();
			}
  	}
  	return codeString;
  }

  /**
   * Erzeugt einen Rateversuchscode.
   * @return Der neue Rateversuchscode.
   */
  private String erzeugeTippstring()
  {
  	String tippString = "";
  	if (spielvariante == 1)
  	{
  		while(tippString.length() != stellenanzahl)
			{
				System.out.printf("\n\nBitte geben Sie Ihren %d-stelligen Tipp ein!\t", stellenanzahl);
				tippString = MeineEingabe.erfasseText();
			}
  	}
  	else
  	{
				Codegenerator starttipp = new Codegenerator(stellenanzahl);
				tippString = starttipp.getStartCodeString();
		}
  	return tippString;
  }

  /**
   * Erzeugt einen neuen Rateversuchscode des Computers mit Hilfe eines Rateversuchcodes, von dem ausgegangen wird.
   * @param 	tippString 	Der Rateversuchscode, von dem ausgegangen wird.
   * @return Der neue Rateversuchscode des Computers.
   */
	private String erzeugeKITippstring(String tippString)
	{
		//System.out.printf("\n\nStartcode%d: [%s]", versuch, tippString);
		boolean guterTipp = true;
		do
		{	
			guterTipp = true;
			tippString = this.inkrementiereTippstring(tippString);
			//System.out.printf("\n\nTippString nach Umwandlung: [%s]", tippString);
			

			for (int t = 0 ; t < tippVerlauf.size(); t++)
				{
					MastermindVersuch tippKandidat = new MastermindVersuch(tippVerlauf.get(t).getTippString(), tippString);
					if ((tippKandidat.getRichtigeStelle() != tippVerlauf.get(t).getRichtigeStelle()) || tippKandidat.getRichtigeZahl() != tippVerlauf.get(t).getRichtigeZahl())
						guterTipp = false;
				}
		}while(!guterTipp);
		//System.out.printf("\n\nComputercode: %s", tippString);
		return tippString;
	}

	/**
   * Inkrementiert einen Rateversuchscode.
   * @param 	tippString 	Der Rateversuchscode.
   * @return Der inkrementierte Rateversuchscode.
   */
	private String inkrementiereTippstring(String tippString)
	{
		int tipp = Integer.parseInt(tippString);
		do
		{
			tipp++;
		} while (!istEinmalig(String.format("%0" + tippString.length() + "d", tipp)));

		return String.format("%0" + tippString.length() + "d", tipp);
	}

	/**
   * Prüft, ob die Ziffern von von 0-9 in einem Rateversuchscode höchstens einmal vorkommen.
   * @param 	tippString 	Der Rateversuchscode.
	 * @return Die Aussage, ob die Ziffern von von 0-9 in einem Rateversuchscode höchstens einmal vorkommen.
	 */
	private boolean istEinmalig(String tippString)
	{
		boolean istEinmalig = true;
		for (int i = 0; i < tippString.length(); i++)
		{
			if (tippString.indexOf(tippString.charAt(i), i + 1) > 0)
			{
				istEinmalig = false;
			}
		}
		return istEinmalig;
	}
}

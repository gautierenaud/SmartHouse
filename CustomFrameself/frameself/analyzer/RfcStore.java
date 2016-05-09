package frameself.analyzer;

import frameself.format.Rfc;

import java.io.IOException;
import java.util.ArrayList;

public class RfcStore
{
	private static ArrayList<Rfc> rfcs = new ArrayList();

	public void store(ArrayList<Rfc> rfcs)
	{
		rfcs.addAll(rfcs);
	}

	public static ArrayList<Rfc> getRfcs() {
		return rfcs;
	}

	public static void setRfcs(ArrayList<Rfc> rfcs) {
		RfcStore.rfcs = rfcs;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/analyzer/RfcStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
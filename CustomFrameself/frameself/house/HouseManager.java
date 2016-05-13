package frameself.house;

import java.util.ArrayList;

public class HouseManager 
{
	private ArrayList<String> personsInside;
	private static HouseManager instance;
	
	private HouseManager()
	{
		this.personsInside = new ArrayList<String>();
	}
	
	public static HouseManager getInstance()
	{
		if(instance==null)
		{
			instance = new HouseManager();
		}
		return instance;
	}
	public void addPerson(String person)
	{
		personsInside.add(person);
	}
	
	public void removePerson(String person)
	{
		personsInside.remove(person);
	}
	
	public boolean isHouseEmpty()
	{
		return personsInside.isEmpty();
	}
	
	public boolean isPersonInside(String person)
	{
		return personsInside.contains(person);
	}
}

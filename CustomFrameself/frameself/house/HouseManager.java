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
	
	public int numberPersonsInside()
	{
		return this.personsInside.size();
	}
	
	public String updatePersonsInHouse(String person)
	{
		HouseManager houseManager = HouseManager.getInstance();
		boolean stateEmptyHouseBefore = houseManager.isHouseEmpty();
		int numberPersonsInsideBefore = houseManager.numberPersonsInside();
		if(!this.isPersonInside(person))
		{
			this.addPerson(person);
		}	
		else
		{
			this.removePerson(person);
		}
		boolean stateEmptyHouseNow = houseManager.isHouseEmpty();
		int numberPersonsInsideNow = houseManager.numberPersonsInside();	
		if(stateEmptyHouseNow && !stateEmptyHouseBefore)
		{
			return "HouseState:Empty";
		}
		else if(!stateEmptyHouseNow && stateEmptyHouseBefore)
		{
			return "HouseState:NotEmpty";
		}
		
		if(numberPersonsInsideNow > numberPersonsInsideBefore)
		{
			return "HouseState:PersonIn";
		}
		else if(numberPersonsInsideNow < numberPersonsInsideBefore && !stateEmptyHouseNow)
		{
			return "HouseState:PersonOut";
		}
		return "";
	}
}

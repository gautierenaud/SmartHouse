package frameself.house;

import java.util.ArrayList;

public class HouseManager 
{
	private ArrayList<String> personsInside;
	private static HouseManager instance;
	private int numberBefore;
	
	private HouseManager()
	{
		this.personsInside = new ArrayList<String>();
		numberBefore = 0;
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
	
	public String PersonInOrOut()
	{
		HouseManager houseManager = HouseManager.getInstance();
		int numberPersonsInsideNow = houseManager.numberPersonsInside();
		if(numberPersonsInsideNow > numberBefore)
		{
			return "HouseState:PersonIn";
		}
		else if(numberPersonsInsideNow < numberBefore && !houseManager.isHouseEmpty())
		{
			return "HouseState:PersonOut";
		}
		return "";
	}
	public String updatePersonsInHouse(String person)
	{
		HouseManager houseManager = HouseManager.getInstance();
		boolean stateEmptyHouseBefore = houseManager.isHouseEmpty();
		numberBefore = houseManager.numberPersonsInside();	
		if(!this.isPersonInside(person))
		{
			this.addPerson(person);
		}	
		else
		{
			this.removePerson(person);
		}
		boolean stateEmptyHouseNow = houseManager.isHouseEmpty();
		
		if(stateEmptyHouseNow && !stateEmptyHouseBefore)
		{
			return "HouseState:Empty";
		}
		else if(!stateEmptyHouseNow && stateEmptyHouseBefore)
		{
			return "HouseState:NotEmpty";
		}
		return "";
	}
}

package order;

import java.util.ArrayList;

public class RuleFrameself {

	private String ruleName;
	private ArrayList<String> rfcIDs;
	private String category;
	private String actionName;
	
	private RuleFrameself()
	{
		
	}
	
	public RuleFrameself(String ruleName, String category, String actionName)
	{
		this.ruleName = ruleName;
		this.category = category;
		this.actionName = actionName;
		this.rfcIDs = new ArrayList<String>();
	}
	
	public String getRuleName()
	{
		return this.ruleName;
	}
	
	public void addRfcID(String RfcID)
	{
		this.rfcIDs.add(RfcID);
	}
	
	public ArrayList<String> getRfcIDs()
	{
		return this.rfcIDs;
	}
	
	public String getCategory()
	{
		return this.category;
	}
	
	public String getActionName()
	{
		return this.actionName;
	}
	

}

package server;

import order.RuleFrameself;

public class RuleGenerator {

	public static byte[] generateRule(RuleFrameself rule)
	{
		String data = "\nrule \"" + rule.getRuleName() + "\"\n";
		data += "\twhen\n";
		for(String RfcID : rule.getRfcIDs())
		{
			data += "\t\tRfc(id == \"" + RfcID + "\")\n";
		}
		data += "\tthen\n";
		data += "\t\tArrayList<Attribute> attributes = new ArrayList<Attribute>();\n";
		data += "\t\tattributes.add(new Attribute(\"state\",\"false\"));\n";
		data += "\t\tAction action = new Action();\n";
		data += "\t\taction.setCategory(\"" + rule.getCategory() + "\");\n";
		data += "\t\taction.setName(\"" + rule.getActionName() + "\");\n";
		data += "\t\taction.setAttributes(attributes);\n";
		data += "\t\taction.setTimestamp(new Date());\n";
		data += "\t\tinsert(action);\n";
		data += "end\n";
		return data.getBytes();
	}
	
	/*
	rule "add SwitchLampOn"
	    when
	       Rfc(category == "IncreaseLight")
	    then 
	   	   ArrayList<Attribute> attributes = new ArrayList<Attribute>();
	   	   attributes.add(new Attribute("state","false"));
	   	   Action action = new Action();
	   	   action.setCategory("Switch");
	   	   action.setName("SwitchLampOn");
	   	   action.setAttributes(attributes);
	   	   action.setEffector(new Effector("Lamp")); 
	   	   action.setTimestamp(new Date());
	      	insert(action);
	end
	*/

}

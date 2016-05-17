package order;

import java.util.ArrayList;

public class RuleFrameself {

	private String ruleName;
	private ArrayList<String> rfcIDs;
	private String category;
	private ArrayList<String> actions;
	private ArrayList<Pair<String, String>> attributes;
	private String policy;

	private RuleFrameself()
	{

	}

	public RuleFrameself(String ruleName, String category)
	{
		this.ruleName = ruleName;
		this.category = category;
		this.actions = new ArrayList<String>();
		this.rfcIDs = new ArrayList<String>();
		this.attributes = new ArrayList<>();
	}

	public String getRuleName()
	{
		return this.ruleName;
	}

	public void addRfcID(String RfcID)
	{
		this.rfcIDs.add(RfcID);
	}

	public void addAction(String action)
	{
		this.actions.add(action);
	}

	public void addAttribute(Pair<String, String> pair) { this.attributes.add(pair); }

	public ArrayList<String> getRfcIDs()
	{
		return this.rfcIDs;
	}

	public String getCategory()
	{
		return this.category;
	}

	public ArrayList<String> getActions()
	{
		return this.actions;
	}

	public byte[] generateRule()
	{
		String data = "\nrule \"" + this.getRuleName() + "\"\n";
		data += "\twhen\n";
		for(String RfcID : this.getRfcIDs())
		{
			data += "\t\tRfc(id == \"" + RfcID + "\")\n";
		}
		if (policy != null)
			data += "\t\tPolicy(name == \"" + policy + "\")\n";
		data += "\tthen\n";
		int i = 0;
		for(String action : this.getActions())
		{
			data += "\t\tArrayList<Attribute> attributes" + i + " = new ArrayList<Attribute>();\n";
			data += "\t\tattributes" + i + ".add(new Attribute(\"state\",\"false\"));\n";
			for (Pair<String, String> p : this.getAttributes()){
				data += "\t\tattributes" + i + ".add(new Attribute(\"" + p.getFirst() + "\",\"" + p.getSecond() + "\"));\n";
			}
			data += "\t\tAction action" + i + " = new Action();\n";
			data += "\t\taction" + i + ".setCategory(\"" + this.getCategory() + "\");\n";
			data += "\t\taction" + i + ".setName(\"" + action + "\");\n";
			data += "\t\taction" + i + ".setAttributes(attributes" + i + ");\n";
			data += "\t\taction" + i + ".setTimestamp(new Date());\n";
			data += "\t\tinsert(action" + i + ");\n";
			i++;
		}
		data += "end\n";
		return data.getBytes();
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public ArrayList<Pair<String, String>> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<Pair<String, String>> attributes) {
		this.attributes = attributes;
	}

}

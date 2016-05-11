package frameself.format;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Action implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static int counter = 1;
	private String id;
	private String category;
	private String name;
	private ArrayList<Attribute> attributes;
	private int priority;
	private String result;
	private String Description;
	private Effector effector;
	private String Error;
	private Date timestamp;

	public Action() {
		this.id = ("Rfc" + counter++);
	}


	public Action(String category, String name, ArrayList<Attribute> attributess, int priority, String description, Effector effector, Date timestamp)
	{
		this.id = ("Action" + counter++);
		this.category = category;
		this.name = name;
		this.attributes = this.attributes;
		this.priority = priority;
		this.Description = description;
		this.effector = effector;
		this.timestamp = timestamp;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		counter = counter;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Attribute> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Effector getEffector() {
		return this.effector;
	}

	public void setEffector(Effector effector) {
		this.effector = effector;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getError() { return this.Error; }

	public void setError(String error) {
		this.Error = error;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/format/Action.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
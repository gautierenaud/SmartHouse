package frameself.format;

import java.util.Date;

public class Rfc {
	private static int counter = 1;
	private String id;
	private String category;
	private String value;
	private String location;
	private Date timestamp;
	private Date expiry;
	private int priority;
	private int severity;
	private String description;

	public Rfc() {
		this.id = ("Rfc" + counter++);
	}


	public Rfc(String category, String value, String location, String description, int priority, int severity, Date timestamp, Date expiry)
	{
		this.id = ("Rfc" + counter++);
		this.category = category;
		this.value = value;
		this.location = location;
		this.description = description;
		this.priority = priority;
		this.severity = severity;
		this.timestamp = timestamp;
		this.expiry = expiry;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Rfc.counter = counter;
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getExpiry() {
		return this.expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getSeverity() {
		return this.severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}


/* Location:home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/format/Rfc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
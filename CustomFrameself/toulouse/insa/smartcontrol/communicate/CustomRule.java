package toulouse.insa.smartcontrol.communicate;

import java.util.Map;

public class CustomRule {

	private String id;
	private String name;
	private Map<String, Object> metaData;
	
	public CustomRule(String id, Map<String, Object> meta, String name){
		this.id = id;
		this.metaData = meta;
		this.name = name;
	}
	
	public CustomRule(){
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getMetaData() {
		return metaData;
	}

	public void setMetaData(Map<String, Object> metaData) {
		this.metaData = metaData;
	}
	
}

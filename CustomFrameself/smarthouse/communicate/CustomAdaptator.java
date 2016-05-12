package smarthouse.communicate;

import org.drools.definition.rule.Rule;

import toulouse.insa.smartcontrol.communicate.CustomRule;

public class CustomAdaptator {
	public static CustomRule adaptRule(Rule rule){
		CustomRule result = new CustomRule(rule.getId(), rule.getMetaData(), rule.getName());
		return result;
	}
}

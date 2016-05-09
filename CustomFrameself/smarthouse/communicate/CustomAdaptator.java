package smarthouse.communicate;

import org.drools.definition.rule.Rule;

public class CustomAdaptator {
	public static CustomRule adaptRule(Rule rule){
		CustomRule result = new CustomRule(rule.getId(), rule.getMetaData(), rule.getName());
		return result;
	}
}

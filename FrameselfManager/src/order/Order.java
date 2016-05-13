package order;

import java.util.ArrayList;

public class Order 
{
	public enum OrderType {START, STOP, ADDRULE};
	OrderType orderType;
	ArrayList<RuleFrameself> rules;
	
	private Order()
	{
		
	}
	
	public Order(OrderType orderType)
	{
		this.orderType = orderType;
		this.rules = new ArrayList<RuleFrameself>();
	}
	
	public void addRule(RuleFrameself rule)
	{
		this.rules.add(rule);
	}
	
	public OrderType getOrderType()
	{
		return this.orderType;
	}
	
	public ArrayList<RuleFrameself> getRules()
	{
		return this.rules;
	}
}

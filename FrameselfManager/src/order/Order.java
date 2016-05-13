package order;

public class Order 
{
	public enum OrderType {START, STOP, ADDRULE};
	OrderType orderType;
	RuleFrameself rule;
	
	private Order()
	{
		
	}
	
	public Order(RuleFrameself rule)
	{
		this.orderType = OrderType.ADDRULE;
		this.rule = rule;
	}
	
	public Order(OrderType orderType)
	{
		this.orderType = orderType;
		this.rule = null;
	}
	
	public OrderType getOrderType()
	{
		return this.orderType;
	}
	
	public RuleFrameself getRule()
	{
		return this.rule;
	}
}

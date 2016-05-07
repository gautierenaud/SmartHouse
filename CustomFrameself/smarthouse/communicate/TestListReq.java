package smarthouse.communicate;

public class TestListReq {
	
	private ListReqReceiver testReceiver;
	private ListReqSender testSender;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestListReq();
	}
	
	public TestListReq(){
		testReceiver = new ListReqReceiver();
		testSender = new ListReqSender();
	}

}

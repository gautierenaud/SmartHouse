package smarthouse.communicate;

public class TestListReq {
	
	private ListReqSender testSender;
	
	public static void main(String[] args) {
		new TestListReq();
	}
	
	public TestListReq(){
		new ListAckReceiver();
		/*
		testSender = new ListReqSender();
		testSender.sendReq(ReqType.SYMPTOM);
		*/
	}
}

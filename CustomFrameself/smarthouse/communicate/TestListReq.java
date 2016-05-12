package smarthouse.communicate;

import toulouse.insa.smartcontrol.communicate.ReqType;

public class TestListReq {
	
	private ListReqSender testSender;
	
	public static void main(String[] args) {
		new TestListReq();
	}
	
	public TestListReq(){
		new ListAckReceiver();
		testSender = new ListReqSender();
		testSender.sendReq(ReqType.POLICY);
	}
}

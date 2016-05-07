package smarthouse.communicate;

enum ReqType {EVENT, RFC, SYMPTOM, ACTION};

public class ListRequest implements java.io.Serializable{
	public ReqType req;
	
	public ListRequest(ReqType req){
		this.req = req;
	}
	
	// dummy constructor for Jackson
	public ListRequest(){
	}
	
	public String toString(){
		return this.req.toString();
	}
}
package toulouse.insa.smartcontrol.communicate;

import java.util.ArrayList;

public class ListAnswer {

	private ReqType answerType;
	private ArrayList<CustomRule> answerList;
	
	public ListAnswer(ReqType type, ArrayList<CustomRule> rules){
		this.answerList = rules;
		this.answerType = type;
	}
	
	public ListAnswer(){}

	public ReqType getAnswerType() {
		return answerType;
	}

	public void setAnswerType(ReqType answerType) {
		this.answerType = answerType;
	}

	public ArrayList<CustomRule> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(ArrayList<CustomRule> answerList) {
		this.answerList = answerList;
	}
	
}

package server;

import frameself.format.Action;

public class ServerFrameself extends ServerDispatch {

	public ServerFrameself(String ipSource, int portTo, int portFrom) {
		super(ipSource, portTo, portFrom);
		// TODO Auto-generated constructor stub
	}

	public void treatAction(Action action)
	{;
		if(action.getCategory().equals("LED")) //in
		{
			if(action.getName().equals("setText"))
			{
				this.sendAction(action); //out
			}
		}
		else if(action.getCategory().equals("Phillips")) //in
		{
			if(action.getName().equals("changeColor"))
			{
				this.sendAction(action); //out
			}
		}
	}
}

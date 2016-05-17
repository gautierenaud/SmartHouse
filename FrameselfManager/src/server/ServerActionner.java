package server;

import frameself.format.Action;

public class ServerActionner extends ServerDispatch {

	public ServerActionner(String ipSource, int portTo, int portFrom) {
		super(ipSource, portTo, portFrom);
		// TODO Auto-generated constructor stub
	}

	public void treatAction(Action action)
	{
		this.sendAction(action);
	}
}

package server;

import frameself.format.Action;

public class ServerActionner extends ServerDispatch {

	public ServerActionner(String ipSource, int portFrom, int portTo) {
		super(ipSource, portFrom, portTo);
		// TODO Auto-generated constructor stub
	}

	public void treatAction(Action action)
	{
		this.sendAction(action);
	}
}

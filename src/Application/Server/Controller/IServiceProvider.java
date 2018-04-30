package Application.Server.Controller;

import Middleware.Message;
import Middleware.MessageHandler;

public interface IServiceProvider {
	

	public void handleMessage(Message message);
	public void setMessageHandler(MessageHandler handler);
	public void setHal();
	public void update(String value);
	public void update(Integer value);

}

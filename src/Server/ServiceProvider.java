package Server;

import Middleware.Message;

public interface ServiceProvider {
	
	public void run();
	public void stop();
	public void handleMessage(Message message);

}

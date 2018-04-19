package Server;

import Middleware.Message;

public interface ServiceProvider {
	
	public void run();
	public void kill();
	public void handleMessage(Message message);


}

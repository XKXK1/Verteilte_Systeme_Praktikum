package Application.Server.Controller;

import Middleware.Message;

public interface ServiceProvider {
	
	public void function1();
	public void function2();
	public void stopMovement();
	public void run();
	public void kill();
	public void handleMessage(Message message);


}

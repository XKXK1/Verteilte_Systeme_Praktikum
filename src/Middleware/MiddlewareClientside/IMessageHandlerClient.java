package Middleware.MiddlewareClientside;

import SharedLibraryMW.Message;

public interface IMessageHandlerClient {
	public void sendMessage(Message msg);
	public Message receiveMessage();
}

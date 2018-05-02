package Middleware.MiddlewareServerside;

import SharedLibraryMW.Message;

public interface IMessageHandlerServer {
	public void sendMessage(Message msg);
	public Message receiveMessage();
}

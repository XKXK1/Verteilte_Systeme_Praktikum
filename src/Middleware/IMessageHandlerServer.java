package Middleware;


public interface IMessageHandlerServer {
	public void MessageHandler(int port);
	public void MessageHandler();	
	public void sendMessage(Message msg);
	public Message receiveMessage();
}

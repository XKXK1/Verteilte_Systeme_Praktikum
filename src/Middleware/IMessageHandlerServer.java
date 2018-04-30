package Middleware;

public interface IMessageHandlerServer {
	public void sendMessage(Message msg);
	public Message receiveMessage();
}

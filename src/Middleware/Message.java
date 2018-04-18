package Middleware;

import java.net.InetAddress;

public class Message {
	private MessageType type;
	private MessageCommands command;
	private int value;
	private int port;
	private InetAddress address;
	
	public Message(MessageType type, MessageCommands command, int value) {
		this.type = type;
		this.command = command;
		this.value = value;
	}
	
	public MessageType getType() {
		return type;
	}
	public MessageCommands getCommand() {
		return command;
	}
	public int getValue() {
		return value;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public InetAddress getAddress() {
		return address;
	}
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public void setCommand(MessageCommands command) {
		this.command = command;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}

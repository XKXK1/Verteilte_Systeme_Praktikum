package Server;

import Middleware.MessageType;

public class HalDataMessage {
	private int value;
	private MessageType type;
	private String state;
	
	
	public String getState() {
		return state;
	}

	public HalDataMessage(int value, MessageType type, String state) {
		this.value = value;
		this.type = type;
		this.state = state;
	}

	public int getValue() {
		return value;
	}

	public MessageType getType() {
		return type;
	}


}

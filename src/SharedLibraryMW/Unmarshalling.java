package SharedLibraryMW;

import org.json.simple.JSONObject;

public class Unmarshalling {
	
	/*
	 * Unmarshalling the received json object to a Message.
	 */
	public Message JsonToMsg(JSONObject json) {
		MessageCommands command = MessageCommands.fromInteger(Integer.parseInt((String) json.get("Command")));
		MessageType type = MessageType.fromInteger(Integer.parseInt((String) json.get("Type")));
		int value = Integer.parseInt((String) json.get("Value"));

		Message msg = new Message(type, command, value);
		return msg;
	}

}

package SharedLibraryMW;

import org.json.simple.JSONObject;

public class Marshalling {

	/*
	 * Marshalling a Message to a json object.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject MsgToJson(Message msg) {
		JSONObject jsonObj= new JSONObject();

		jsonObj.put("Command", Integer.toString(MessageCommands.toInteger(msg.getCommand())));
		jsonObj.put("Type", Integer.toString(MessageType.toInteger(msg.getType())));
		jsonObj.put("Value", Integer.toString(msg.getValue()));

		return jsonObj;
	}

}

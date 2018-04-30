package Middleware;

import org.json.simple.JSONObject;
public class MessageHandler  {
	protected UdpHandler udpHandler;

	public void sendMessage(Message msg) {
		JSONObject json = MsgToJson(msg);
		UdpWrapper wrapper = new UdpWrapper(msg.getPort(), msg.getAddress(), json);
		udpHandler.sendJson(wrapper);
	}

	public Message receiveMessage() {
		UdpWrapper wrapper = udpHandler.receiveJson();
		if (wrapper != null) {
			Message msg = JsonToMsg(wrapper.getJsonObj());
			msg.setPort(wrapper.getPort());
			msg.setAddress(wrapper.getAddress());
			return msg;
		}
		return null;
	}

	protected Message JsonToMsg(JSONObject json) {
		MessageCommands command = MessageCommands.fromInteger(Integer.parseInt((String) json.get("Command")));
		MessageType type = MessageType.fromInteger(Integer.parseInt((String) json.get("Type")));
		int value = Integer.parseInt((String) json.get("Value"));

		Message msg = new Message(type, command, value);
		return msg;
	}

	protected JSONObject MsgToJson(Message msg) {
		JSONObject jsonObj= new JSONObject();

		jsonObj.put("Command", Integer.toString(MessageCommands.toInteger(msg.getCommand())));
		jsonObj.put("Type", Integer.toString(MessageType.toInteger(msg.getType())));
		jsonObj.put("Value", Integer.toString(msg.getValue()));

		return jsonObj;
	}

}

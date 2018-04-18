package Middleware;

import org.json.simple.JSONObject;

public class MessageHandler{	
	UdpHandler udpHandler;
	
	public MessageHandler(int port) {
		udpHandler = new UdpHandler(port);
	}
	public void sendMessage(Message msg) {
		JSONObject json = MsgToJson(msg);
		UdpWrapper wrapper = new UdpWrapper(msg.getPort(), msg.getAddress(), json);
		udpHandler.sendJson(wrapper);
	}
	
	public Message receiveMessage() {
		UdpWrapper wrapper = udpHandler.receiveJson();
		if(wrapper != null) {
			Message msg = JsonToMsg(wrapper.getJsonObj());
			msg.setPort(wrapper.getPort());
			msg.setAddress(wrapper.getAddress());
			return msg;
		}
		return null;
	}
	
	private Message JsonToMsg(JSONObject json) {
		MessageCommands command = MessageCommands.fromInteger( Integer.parseInt((String) json.get("Command")));
		MessageType type = MessageType.fromInteger( Integer.parseInt((String) json.get("Type")));
		float value = Float.parseFloat((String)json.get("Value"));
		
		Message msg = new Message(type, command, value);
		return msg;
	}
	
	private JSONObject MsgToJson(Message msg) 
	{
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("Command", Integer.toString( MessageCommands.toInteger(msg.getCommand())));	      
		jsonObj.put("Type", Integer.toString( MessageType.toInteger(msg.getType())));
		jsonObj.put("Value", Float.toString( msg.getValue()) );

		return jsonObj;
	}
	
}

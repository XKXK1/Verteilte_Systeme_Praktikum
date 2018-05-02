package SharedLibraryMW;

import java.net.DatagramPacket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MessageHandler  {
	protected UdpHandler udpHandler;
	private Marshalling marshaller = new Marshalling();
	private Unmarshalling unMarshaller = new Unmarshalling();

	public void sendMessage(Message msg) {
		JSONObject json = marshaller.MsgToJson(msg);
		
		byte[] rawJason = JsonToByte(json);
		
		DatagramPacket packet = new DatagramPacket(rawJason, rawJason.length, msg.getAddress(), msg.getPort());
		udpHandler.sendPacket(packet);
	}

	public Message receiveMessage() {
		DatagramPacket packet = udpHandler.receivePacket();
		
		if (packet != null) {
				JSONObject json = byteToJson(packet.getData());
				Message msg = unMarshaller.JsonToMsg(json);
				msg.setPort(packet.getPort());
				msg.setAddress(packet.getAddress());
				return msg;
		}
		return null;
	}
	
	private JSONObject byteToJson(byte[] byteArray) {
		String jsonString = new String(byteArray);
		jsonString = jsonString.replace("\u0000", "");
		JSONParser parser = new JSONParser();
        JSONObject json;
        try {
			json = (JSONObject) parser.parse(jsonString);
			return json;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;		
	}
	
	private byte[] JsonToByte(JSONObject json) {
		String jsonString = json.toJSONString();
		byte[] rawJason = jsonString.getBytes();	
		return rawJason;
	}
}

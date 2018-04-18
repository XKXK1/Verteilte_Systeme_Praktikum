package Middleware;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UdpHandler {
	private DatagramSocket socket;
	
	public UdpHandler(int serverPort) {	
		try {
			//create ListeningPort
			socket = new DatagramSocket(serverPort);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendJson(UdpWrapper msg) {
		//make byte[] from jasonfile	
		String jsonString = msg.getJsonObj().toJSONString();
		byte[] rawJason = jsonString.getBytes(); 	
		//make Packet ready to send
		DatagramPacket packet = new DatagramPacket(rawJason, rawJason.length, msg.getAddress(), msg.getPort());
		try {
			socket.send(packet);			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public UdpWrapper receiveJson() {
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
		try {
			socket.receive(packet);
			
			//get real Data from packet
			byte[] data = packet.getData();
			String jsonString = new String(data);
			jsonString = jsonString.replace("\u0000", "");
			JSONParser parser = new JSONParser();
	        JSONObject json = (JSONObject) parser.parse(jsonString);		
	        
	        UdpWrapper msg = new UdpWrapper(packet.getPort(),packet.getAddress(),json);
	        
			return msg;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
package SharedLibraryMW;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpHandler {
	private DatagramSocket socket;
	private final int packetSize = 1024;
	private final int timeOut = 500;
	
	public UdpHandler(int serverPort) {	
		try {
			//create ListeningPort
			socket = new DatagramSocket(serverPort);
			socket.setSoTimeout(timeOut);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UdpHandler() {	
		try {
			//create ListeningPort
			socket = new DatagramSocket();
			socket.setSoTimeout(5000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void sendPacket(DatagramPacket packet) {
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
	
	public DatagramPacket receivePacket() {
		DatagramPacket packet = new DatagramPacket(new byte[packetSize], packetSize);
		try {
			socket.receive(packet);
			return packet;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;
	}
}

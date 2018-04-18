package Middleware;

import java.net.InetAddress;

import org.json.simple.JSONObject;

public class UdpWrapper {
	private int port;
	private InetAddress address;
	private JSONObject jsonObj;
	
	public UdpWrapper(int port, InetAddress address, JSONObject jsonObj) {
		this.port = port;
		this.address = address;
		this.jsonObj = jsonObj;	
	}
	
	public int getPort() {
		return port;
	}
	public InetAddress getAddress() {
		return address;
	}
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	
}

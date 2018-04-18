package Client;

import java.net.InetAddress;

public class ServiceAddress {

	private int port;
	private InetAddress address;
	
	public ServiceAddress(InetAddress address, int port){
		this.address = address;
		this.port = port;
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
	
}

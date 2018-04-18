package Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RobotHandler {
	private EventHandler eventHandler = null;
	
	ServiceConsumer consumer = null;
	
	
	public RobotHandler(EventHandler handler) {
		eventHandler = handler;
		addRobot();
	}
	
	public void addRobot(){
		ServiceAddress verticalAddress = null;
		ServiceAddress horizontalAddress = null;
		ServiceAddress gripperAddress = null;
		
		try {
			gripperAddress = new ServiceAddress(InetAddress.getByName("localhost"), 2000);
			verticalAddress = new ServiceAddress(InetAddress.getByName("localhost"), 20001);
			horizontalAddress = new ServiceAddress(InetAddress.getByName("localhost"), 20002);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		consumer = new ServiceConsumer(this, verticalAddress , horizontalAddress, gripperAddress);
	}
	
	public void setHorizontal(int val){
		consumer.setHorizontal(val);
	}
	
	public void setVertical(int val){
		consumer.setVertical(val);
	}
	
	public void setGripper(boolean isOpen){
		consumer.setGripper(isOpen);
	}
	
	public void getHorizontal(int val){
		eventHandler.setHorizontal(val);
	}
	
	public void getVertical(int val){
		eventHandler.setVertical(val);
	}
	
	public void getGripper(boolean isOpen){
		eventHandler.setGrip(isOpen);
	}
}

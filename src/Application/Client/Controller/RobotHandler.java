package Application.Client.Controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import Application.Client.View.EventHandler;

public class RobotHandler {
	private EventHandler eventHandler = null;
	
	ServiceConsumer consumer = null;
	
	
	public RobotHandler(EventHandler handler) {
		eventHandler = handler;
	}
	
	public void addRobot(String ip){
		ServiceAddress verticalAddress = null;
		ServiceAddress horizontalAddress = null;
		ServiceAddress gripperAddress = null;
		
		try {
			gripperAddress = new ServiceAddress(InetAddress.getByName(ip), 2000);
			horizontalAddress = new ServiceAddress(InetAddress.getByName(ip), 2001);
			verticalAddress = new ServiceAddress(InetAddress.getByName(ip), 2002);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		consumer = new ServiceConsumer(this, verticalAddress , horizontalAddress, gripperAddress);
		consumer.startRecv();
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

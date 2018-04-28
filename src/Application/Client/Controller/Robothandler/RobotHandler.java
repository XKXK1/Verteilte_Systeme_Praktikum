package Application.Client.Controller.Robothandler;

import java.net.InetAddress;
import java.net.UnknownHostException;

import Application.Client.Controller.ClientServiceConsumer.ServiceAddress;
import Application.Client.Controller.ClientServiceConsumer.ServiceConsumer;
import Application.Client.View.Eventhandler.EventHandler;

public class RobotHandler implements IRobotHandler{
	private EventHandler eventHandler = null;
	
	ServiceConsumer consumer = null;
	
	
	public RobotHandler(EventHandler handler) {
		eventHandler = handler;
	}
	
	@Override
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
	
	@Override
	public void setHorizontalRobot(int val){
		consumer.setHorizontal(val);
	}
	
	@Override
	public void setVerticalRobot(int val){
		consumer.setVertical(val);
	}
	
	@Override
	public void setGripRobot(boolean isOpen){
		consumer.setGrip(isOpen);
	}
	
	@Override
	public void setHorizontalGUI(int val){
		eventHandler.setHorizontal(val);
	}
	
	@Override
	public void setVerticalGUI(int val){
		eventHandler.setVertical(val);
	}
	
	@Override
	public void setGripGUI(boolean isOpen){
		eventHandler.setGrip(isOpen);
	}
}

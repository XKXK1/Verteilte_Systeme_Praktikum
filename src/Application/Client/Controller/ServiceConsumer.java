package Application.Client.Controller;

import Middleware.Message;
import Middleware.MessageCommands;
import Middleware.MessageHandler;
import Middleware.MessageType;
import lejos.utility.Delay;

public class ServiceConsumer implements Runnable{

	private float verticalPosition = 0;
	private float horizontalPosition = 0;
	private boolean isGripperOpen = false;
	private final int delay = 128;
	
	private boolean isAlive = false;
	private Thread recvThread = null;
	
	private MessageHandler verticalService = null;
	private MessageHandler horizontalService = null;
	private MessageHandler gripperService = null;
	
	private ServiceAddress verticalAddress = null;
	private ServiceAddress horizontalAddress = null;
	private ServiceAddress gripperAddress = null;
	
	private RobotHandler handler = null;
	
	
	
	public ServiceConsumer(RobotHandler handler, ServiceAddress verticalAddress, ServiceAddress horizontalAddress, ServiceAddress gripperAddress){
		this.horizontalAddress = horizontalAddress;
		this.verticalAddress = verticalAddress;
		this.gripperAddress = gripperAddress;
		
		gripperService = new MessageHandler();
		verticalService = new MessageHandler();
		horizontalService = new MessageHandler();
		
		this.handler = handler;
	}
	
	public void setVertical(int val){
		Message msg = new Message(MessageType.VERTICAl, MessageCommands.SET, val);
		msg.setPort(verticalAddress.getPort());
		msg.setAddress(verticalAddress.getAddress());
		verticalService.sendMessage(msg);
	}
	
	public void setHorizontal(int val){
		Message msg = new Message(MessageType.HORIZONTAL, MessageCommands.SET, val);
		msg.setPort(horizontalAddress.getPort());
		msg.setAddress(horizontalAddress.getAddress());
		horizontalService.sendMessage(msg);
	}
	
	public void setGripper(boolean isOpen){
		int val = 0;
		if(isOpen){
			val = 1;
		}
		
		Message msg = new Message(MessageType.GRIP, MessageCommands.SET, val);
		msg.setPort(gripperAddress.getPort());
		msg.setAddress(gripperAddress.getAddress());
		gripperService.sendMessage(msg);
	}
	
	public void startRecv(){
		if(!isAlive){
			isAlive = true;
			recvThread = new Thread(this);
			recvThread.start();
		}
	}
	
	public void stopRecv(){
		isAlive = false;
	}
	
	@Override
	public void run() {
		System.out.println("Recieve start");
		while(isAlive){
			getGripper();
			getHorizontal();
			getVertical();
			Delay.msDelay(delay);
		}
	}
	
	private void getGripper(){
		Message msg = new Message(MessageType.GRIP, MessageCommands.GET, 0);
		msg.setPort(gripperAddress.getPort());
		msg.setAddress(gripperAddress.getAddress());
		gripperService.sendMessage(msg);
		msg = gripperService.receiveMessage();
		
		if(msg != null){
			if(msg.getCommand().equals(MessageCommands.REPLY)  && msg.getType().equals(MessageType.GRIP)){
				if(msg.getValue() == 1){
					if(!isGripperOpen){
						isGripperOpen = true;
						handler.getGripper(isGripperOpen);
					}
				}
				else{
					if(isGripperOpen){
						isGripperOpen = false;
						handler.getGripper(isGripperOpen);
					}
				}
			}
		}
	}
	
	private void getHorizontal(){
		Message msg = new Message(MessageType.HORIZONTAL, MessageCommands.GET, 0);
		msg.setPort(horizontalAddress.getPort());
		msg.setAddress(horizontalAddress.getAddress());
		horizontalService.sendMessage(msg);
		msg = horizontalService.receiveMessage();
		
		if(msg != null){
			if(msg.getCommand().equals(MessageCommands.REPLY) && msg.getType().equals(MessageType.HORIZONTAL)){
				int val = msg.getValue();
				if(val != horizontalPosition){
					handler.getHorizontal(val);
				}
				horizontalPosition = val;
			}
		}
	}
	
	private void getVertical(){
		Message msg = new Message(MessageType.VERTICAl, MessageCommands.GET, 0);
		msg.setPort(verticalAddress.getPort());
		msg.setAddress(verticalAddress.getAddress());
		verticalService.sendMessage(msg);
		msg = verticalService.receiveMessage();
		
		if(msg != null){
			if(msg.getCommand().equals(MessageCommands.REPLY) && msg.getType().equals(MessageType.VERTICAl)){
				int val = msg.getValue();
				if(val != verticalPosition){
					handler.getVertical(val);
				}
				verticalPosition = val;
			}
		}
	}
	
}
package Client;

import Middleware.Message;
import Middleware.MessageCommands;
import Middleware.MessageHandler;
import Middleware.MessageType;

public class ServiceConsumer {

	private float verticalPosition = 0;
	private float horizontalPosition = 0;
	private boolean isGripperOpen = false;
	
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
		
		gripperService = new MessageHandler(2100);
		verticalService = new MessageHandler(2101);
		horizontalService = new MessageHandler(2102);
		
		this.handler = handler;
	}
	
	public void setVertical(int val){
		Message msg = new Message(MessageType.VERTICAl, MessageCommands.SET, val);
		msg.setPort(verticalAddress.getPort());
		msg.setAddress(verticalAddress.getAddress());
		verticalService.sendMessage(msg);
	}
	
	public void setHorizontal(int val){
		Message msg = new Message(MessageType.VERTICAl, MessageCommands.SET, val);
		msg.setPort(horizontalAddress.getPort());
		msg.setAddress(horizontalAddress.getAddress());
		horizontalService.sendMessage(msg);
	}
	
	public void setGripper(boolean isOpen){
		int val = 0;
		if(isOpen){
			val = 1;
		}
		
		Message msg = new Message(MessageType.VERTICAl, MessageCommands.SET, val);
		msg.setPort(gripperAddress.getPort());
		msg.setAddress(gripperAddress.getAddress());
		gripperService.sendMessage(msg);
	}
	
}

package Application;

import java.util.Scanner;

import Application.Server.Controller.GripperService;
import Application.Server.Controller.HalDataContainer;
import Application.Server.Controller.HorizontalService;
import Application.Server.Controller.IHalServices;
import Application.Server.Controller.IServiceProvider;
import Application.Server.Controller.VerticalService;
import Middleware.MessageHandlerServer;

public class ServerMain {

	static public void main(String[] args) {

	IServiceProvider gService = new GripperService();
	IServiceProvider hService = new HorizontalService();
	IServiceProvider vService = new VerticalService();
	new HalDataContainer(gService,hService,vService);		
	gService.setHal();
	hService.setHal();
	vService.setHal();
		
	MessageHandlerServer gHandler = new MessageHandlerServer(2000, gService);
	MessageHandlerServer hHandler = new MessageHandlerServer(2001, hService);
	MessageHandlerServer vHandler = new MessageHandlerServer(2002, vService);
	
	gService.setMessageHandler(gHandler);
	hService.setMessageHandler(hHandler);
	vService.setMessageHandler(vHandler);
	
	
	Thread verticalSocketThread = new Thread(vHandler);
	Thread horizontalSocketThread = new Thread(hHandler);
	Thread gripperSocketThread = new Thread(gHandler);
	
	gripperSocketThread.start();
	horizontalSocketThread.start();
	verticalSocketThread.start();
	
	Scanner scanner = new Scanner(System.in);
	scanner.nextLine();
	
	try {
		gripperSocketThread.join();
		horizontalSocketThread.join();
		verticalSocketThread.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	
}
}
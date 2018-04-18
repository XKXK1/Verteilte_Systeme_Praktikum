package Server;

import java.util.Scanner;

public class ServerMain {

	static public void main(String[] args) {
		GripperService gService = new GripperService(2000);
		HorizontalService hService = new HorizontalService(2001);
		VerticalService vService = new VerticalService(2002);
		HalDataContainer halContainer = new HalDataContainer(gService,hService,vService);		
		
		Thread verticalThread = new Thread(vService);
		Thread horizontalThread = new Thread(hService);
		Thread gripperThread = new Thread(gService);
		gripperThread.start();
		horizontalThread.start();
		verticalThread.start();
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		gService.kill();
		hService.kill();
		vService.kill();
		try {
			gripperThread.join();
			horizontalThread.join();
			verticalThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
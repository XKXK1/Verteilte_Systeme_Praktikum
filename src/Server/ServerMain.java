package Server;

import java.util.Scanner;

public class ServerMain {

	static public void main(String[] args) {
		HalDataContainer halContainer = new HalDataContainer();
		GripperService gService = new GripperService(2000);
		
		Thread gripperThread = new Thread(gService);
		gripperThread.start();
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		gService.kill();
		try {
			gripperThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
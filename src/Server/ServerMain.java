package Server;


public class ServerMain {

	static public void main(String[] args) {
		GripperService gService = new GripperService(2000);
		Thread gripperThread = new Thread(gService);
		gripperThread.start();
		gService.kill();
	}
	
}

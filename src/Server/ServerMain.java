package Server;


public class ServerMain {

	static public void main(String[] args) {
		GripperService gService = new GripperService(2000);
		Thread gripperThread = new Thread(gService);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gService.kill();
	}
	
}

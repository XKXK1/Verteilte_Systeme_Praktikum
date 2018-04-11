
public class MainClass {
	public static void main(String[] args) {
		EventHandler ev = new EventHandler();
		try {
			ev.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

package Server;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;

import Middleware.Message;
import Middleware.MessageHandler;

public class HorizontalService implements ServiceProvider, Runnable {
	private static CaDSEV3RobotHAL caller = null;
	private MessageHandler messageHandler;
	private boolean running = false;
	private int current = 0;
	private int goal = 0;
	
	public HorizontalService(int port) {
		running = true;
		messageHandler = new MessageHandler(port);
	}

	@Override
	public void run() {
		Message msg = null;
		caller = CaDSEV3RobotHAL.getInstance();

		System.out.println("thread started");
		while (running) {
			msg = messageHandler.receiveMessage();
			if (msg != null) {
				handleMessage(msg);
			}
			// messageHandler.sendMessage(msg);
		}

		System.out.println("thread killed");
	}

	@Override
	public void kill() {
		running = false;
	}

	@Override
	public void handleMessage(Message message) {
		switch (message.getCommand()) {
		case SET:
			System.out.println("Test");
			caller.stop_h();
			goal = message.getValue();
			System.out.println(goal);
			if (goal > current) {
				caller.moveLeft();
			} else if (goal < current) {
				caller.moveRight();
			}
			System.out.println("ende");
			break;
		case GET:
			break;
		case REPLY:
			break;
		default:
			break;
		}
	}

	public void update(Integer value) {
		if(value != null) {
			current = value;
			if(current == goal) {
				caller.stop_h();
			}
		}
	}

}

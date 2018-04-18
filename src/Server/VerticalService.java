package Server;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;

import Middleware.Message;
import Middleware.MessageHandler;

public class VerticalService implements ServiceProvider, Runnable {
	private static CaDSEV3RobotHAL caller = null;
	private MessageHandler messageHandler;
	private boolean running = false;
	
	private int current;
	private int goal;
	
	public VerticalService(int port) {
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
			caller.stop_v();
			goal = message.getValue();
			System.out.println(goal);
			if (goal > current) {
				caller.moveUp();
			} else if (goal < current) {
				caller.moveDown();
			}
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
				caller.stop_v();
			}
		}
	}

}

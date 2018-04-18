package Server;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;


import Middleware.*;

public class GripperService implements ServiceProvider, Runnable {
	private static CaDSEV3RobotHAL caller = null;
	private MessageHandler messageHandler;
	private boolean running = false;
	private int gripValue = -1;

	enum GripperStates {
		OPEN, CLOSE
	};

	public GripperService(int port) {
		running = true;
		messageHandler = new MessageHandler(port);
	}

	public void gripperOpen() {
		caller.doOpen();

	}

	public void gripperClose() {
		caller.doClose();

	}

	@Override
	public void run() {
		Message msg = null;
		caller = CaDSEV3RobotHAL.getInstance();

		System.out.println("grip service started");
		while (running) {
			msg = messageHandler.receiveMessage();
			if (msg != null) {
				handleMessage(msg);
			}
		}

		System.out.println("grip service ended");
	}

	@Override
	public void kill() {
		running = false;
	}

	@Override
	public void handleMessage(Message message) {
		switch (message.getCommand()) {
		case SET:
			if (message.getValue() == 0) {
				gripperClose();
			} else if (message.getValue() == 1) {
				gripperOpen();
			}
		case GET:
			break;
		case REPLY:
			break;
		default:
			break;
		}
	}

	public void update(String value) {
		//System.out.println(value);		
	}


}

package Server;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;
import org.cads.ev3.middleware.CaDSEV3RobotType;
import org.cads.ev3.middleware.hal.ICaDSEV3RobotFeedBackListener;
import org.cads.ev3.middleware.hal.ICaDSEV3RobotStatusListener;
import org.json.simple.JSONObject;

import Middleware.*;

public class GripperService
		implements ServiceProvider, Runnable {
	private static CaDSEV3RobotHAL caller = null;
	private MessageHandler messageHandler;
	private boolean running = false;

	enum GripperStates {
		OPEN, CLOSE
	};

	public GripperService(int port) {
		running = true;
		caller = CaDSEV3RobotHAL.getInstance();
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
		
		System.out.println("thread started");
		while (running) {
			msg = messageHandler.receiveMessage();
			if(msg != null) {
				handleMessage(msg);		
			}
			//messageHandler.sendMessage(msg);
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
}

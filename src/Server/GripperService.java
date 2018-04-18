package Server;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;
import org.cads.ev3.middleware.CaDSEV3RobotType;
import org.cads.ev3.middleware.hal.ICaDSEV3RobotFeedBackListener;
import org.cads.ev3.middleware.hal.ICaDSEV3RobotStatusListener;
import org.json.simple.JSONObject;

import Middleware.*;

public class GripperService implements ServiceProvider, Runnable,  ICaDSEV3RobotStatusListener, ICaDSEV3RobotFeedBackListener {
	private static CaDSEV3RobotHAL caller = null;
	private MessageHandler messageHandler;
	
	enum GripperStates{OPEN,CLOSE};
	
	public GripperService(int port) {
		caller = CaDSEV3RobotHAL.createInstance(CaDSEV3RobotType.REAL, this, this);
		messageHandler = new MessageHandler(port);
		
	}
	
	@Override
	public synchronized void giveFeedbackByJSonTo(JSONObject feedback) {
		System.out.println(feedback);
	}

	@Override
	public synchronized void onStatusMessage(JSONObject status) {
		System.out.println(status);

	}
	
	
	public void gripperOpen() {
		caller.doOpen();
		
	}
	
	public void gripperClose() {
		caller.doClose();
		
	}

	@Override
	public void run() {
		Message msg = messageHandler.receiveMessage();
		handleMessage(msg);

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMessage(Message message) {
		switch(message.getType()) {

		
		}

	}

}

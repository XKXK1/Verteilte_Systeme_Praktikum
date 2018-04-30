package Application.Server.Controller;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;

import Middleware.*;

public class GripperService implements IServiceProvider, IHalServices {
	private CaDSEV3RobotHAL caller = null;
	private MessageHandler messageHandler = null;
	private boolean open = false;

	public GripperService() {
	}

	@Override
	public void handleMessage(Message message) {
		switch (message.getCommand()) {
		case SET:
			if (message.getValue() == 0) {
				function2();
			} else if (message.getValue() == 1) {
				function1();
			}
		case GET:
			Message reply;
			if (open) {
				reply = new Message(MessageType.GRIP, MessageCommands.REPLY, 1);
			} else {
				reply = new Message(MessageType.GRIP, MessageCommands.REPLY, 0);
			}
			reply.setAddress(message.getAddress());
			reply.setPort(message.getPort());
			messageHandler.sendMessage(reply);
			break;
		case REPLY:
			break;
		default:
			break;
		}
	}

	public void update(String value) {
		if (value == "closed") {
			open = false;
		} else {
			open = true;
		}
	}

	@Override
	public void function1() {
		caller.doOpen();

	}

	@Override
	public void function2() {
		caller.doClose();

	}

	@Override
	public void stopMovement() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Integer value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHal() {
		caller = CaDSEV3RobotHAL.getInstance();

	}

	@Override
	public void setMessageHandler(MessageHandler handler) {
		this.messageHandler = handler;
	}

}

package Application.Server.Controller;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;

import Middleware.Message;
import Middleware.MessageCommands;
import Middleware.MessageHandler;
import Middleware.MessageType;

public class VerticalService implements IServiceProvider, IHalServices {
	private CaDSEV3RobotHAL caller = null;
	private MessageHandler messageHandler = null;

	private int current = 0;
	private int goal = -1;
	private boolean up = false;

	public VerticalService() {

	}

	@Override
	public void handleMessage(Message message) {
		switch (message.getCommand()) {
		case SET:
			stopMovement();
			goal = message.getValue();
			System.out.println(goal);
			if (goal > current) {
				up = true;

				new Thread(new Runnable() {
					@Override
					public void run() {
						function1();
					}
				}).start();

			} else if (goal < current) {
				up = false;

				new Thread(new Runnable() {
					@Override
					public void run() {
						function2();
					}
				}).start();

			}
			break;
		case GET:
			Message reply = new Message(MessageType.VERTICAl, MessageCommands.REPLY, current);
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

	public void update(Integer value) {
		if (value != null) {
			current = value;

			if (up) {
				if (current >= goal) {
					stopMovement();
				}
			} else if (!up) {
				if (current <= goal) {
					stopMovement();
				}
			}

		}
	}

	@Override
	public void function1() {
		caller.moveUp();

	}

	@Override
	public void function2() {
		caller.moveDown();
		
	}

	@Override
	public void stopMovement() {
		caller.stop_v();
		
	}

	@Override
	public void update(String value) {
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

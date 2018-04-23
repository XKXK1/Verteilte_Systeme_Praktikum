package Application.Server.Controller;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;

import Middleware.Message;
import Middleware.MessageCommands;
import Middleware.MessageHandler;
import Middleware.MessageType;

public class HorizontalService implements ServiceProvider, Runnable {
	private CaDSEV3RobotHAL caller = null;
	private MessageHandler messageHandler;
	private boolean running = false;
	private int current = 0;
	private int goal = 0;
	private boolean left = false;

	public HorizontalService(int port) {
		running = true;
		messageHandler = new MessageHandler(port);
	}

	@Override
	public void run() {
		Message msg = null;
		caller = CaDSEV3RobotHAL.getInstance();

		System.out.println("horizontal service started");
		while (running) {
			msg = messageHandler.receiveMessage();
			if (msg != null) {
				handleMessage(msg);
			}
		}
		System.out.println("horizontal service ended");
	}

	@Override
	public void kill() {
		running = false;
	}

	@Override
	public void handleMessage(Message message) {
		switch (message.getCommand()) {
		case SET:
			stopMovement();
			goal = message.getValue();
			System.out.println(goal);
			if (goal > current) {
				left = true;

				new Thread(new Runnable() {
					@Override
					public void run() {
						function1();
					}
				}).start();

			} else if (goal < current) {
				left = false;

				new Thread(new Runnable() {
					@Override
					public void run() {
						function2();
					}
				}).start();

			}
			break;
		case GET:
			Message reply = new Message(MessageType.HORIZONTAL, MessageCommands.REPLY, current);
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
		if(value != null) {
			current = value;
		
			if(left) {
				if(current >= goal) {
					stopMovement();
				}
			} else if(!left) {
				if(current <= goal) {
					stopMovement();
				}
			}
		}
	}

	@Override
	public void function1() {
		caller.moveLeft();
		
	}

	@Override
	public void function2() {
		caller.moveRight();
		
	}

	@Override
	public void stopMovement() {
		caller.stop_h();
		
	}
}

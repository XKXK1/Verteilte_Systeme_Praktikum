package Server;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;

import Middleware.Message;
import Middleware.MessageCommands;
import Middleware.MessageHandler;
import Middleware.MessageType;

public class VerticalService implements ServiceProvider, Runnable {
	private  CaDSEV3RobotHAL caller = null;
	private MessageHandler messageHandler;
	private boolean running = false;
	
	private int current= 0;
	private int goal = -1;
	private boolean up = false;
	
	public VerticalService(int port) {
		running = true;
		messageHandler = new MessageHandler(port);
	}

	@Override
	public void run() {
		Message msg = null;
		caller = CaDSEV3RobotHAL.getInstance();

		System.out.println("vertical service started");
		while (running) {
			msg = messageHandler.receiveMessage();
			if (msg != null) {
				handleMessage(msg);
			}
		}

		System.out.println("vertical service ended");
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
				up = true;
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						caller.moveUp();
					}
				}).start();
				
			} else if (goal < current) {
				up = false;
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						caller.moveDown();
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
		if(value != null) {
			current = value;
		
			if(up) {
				if(current >= goal) {
					caller.stop_v();
				}
			} else if(!up) {
				if(current <= goal) {
					caller.stop_v();
				}
			}


		}
	}

}

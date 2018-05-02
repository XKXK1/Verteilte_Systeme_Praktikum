package Application.Server.Controller.ServiceProvider;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;

import SharedLibraryMW.Message;
import SharedLibraryMW.MessageCommands;
import SharedLibraryMW.MessageHandler;
import SharedLibraryMW.MessageType;

public class HorizontalService implements IServiceProvider, IServices {
	private CaDSEV3RobotHAL caller = null;
	private MessageHandler messageHandler = null;
	private int current = 0;
	private int goal = 0;
	private boolean left = false;

	public HorizontalService() {
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

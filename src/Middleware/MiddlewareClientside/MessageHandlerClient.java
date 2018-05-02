package Middleware.MiddlewareClientside;

import Application.Client.Controller.ServiceConsumer.IServiceConsumer;
import SharedLibraryMW.Message;
import SharedLibraryMW.MessageHandler;
import SharedLibraryMW.UdpHandler;

public class MessageHandlerClient extends MessageHandler implements Runnable {
	private IServiceConsumer _provider = null;
	private boolean _running = true;

	public MessageHandlerClient(int port, IServiceConsumer provider) {
		_running = true;
		this._provider = provider;
		udpHandler = new UdpHandler(port);
	}

	public MessageHandlerClient(IServiceConsumer provider) {
		udpHandler = new UdpHandler();
	}
	
	
	@Override
	public void run() {
		System.out.println("MessageHandler started");

		while (_running) {

			Message msg = null;
			msg = receiveMessage();
			if (msg != null) {
				_provider.handleMessage(msg);
			}

		}
	}
}

package com.jzj.socket;

public class ClsMainClient {

	public static void main(String[] args) {
		TcpClient c1 = new TcpClient() {

			@Override
			public void onReceive(SocketTransceiver st, String s) {
				System.out.println("Client1 Receive: " + s);
			}

			@Override
			public void onDisconnect(SocketTransceiver st) {
				System.out.println("Client1 Disconnect");
			}

			@Override
			public void onConnect(SocketTransceiver transceiver) {
				System.out.println("Client1 Connect");
			}

			@Override
			public void onConnectFailed() {
				System.out.println("Client1 Connect Failed");
			}
		};
		TcpClient c2 = new TcpClient() {

			@Override
			public void onReceive(SocketTransceiver st, String s) {
				System.out.println("Client2 Receive: " + s);
			}

			@Override
			public void onDisconnect(SocketTransceiver st) {
				System.out.println("Client2 Disconnect");
			}

			@Override
			public void onConnect(SocketTransceiver transceiver) {
				System.out.println("Client2 Connect");
			}

			@Override
			public void onConnectFailed() {
				System.out.println("Client2 Connect Failed");
			}
		};
		c1.connect("127.0.0.1", 1234);
		c2.connect("127.0.0.1", 1234);
		delay();
		while (true) {
			if (c1.isConnected()) {
				c1.getTransceiver().send("Hello1");
			} else {
				break;
			}
			delay();
			if (c2.isConnected()) {
				c2.getTransceiver().send("Hello2");
			} else {
				break;
			}
			delay();
		}
	}

	static void delay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

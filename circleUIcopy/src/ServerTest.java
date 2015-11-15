import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import server.CircleServer;

public class ServerTest {
	public static void main(String[] args) throws Exception {
		 CircleServer cServer = new CircleServer();
		 cServer.startService();
//		 String ip = "192.168.23.1";
//		 int port = 1234;
//		 InetSocketAddress address = new InetSocketAddress(ip, port);
//		 Socket socket = new Socket();
//		 socket.connect(address);
//		 System.out.println("Done");
		
//		ServerSocket cs = new ServerSocket(1234);
//		while (true) {
//			System.out.println("Waiting..");
//			cs.accept();
//			System.out.println("Connected");
//		}

	}
}

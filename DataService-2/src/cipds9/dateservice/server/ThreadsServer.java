package cipds9.dateservice.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ThreadsServer {
	
	static final int PORT = 5555;
	
	public static void main(String[] args) throws Exception {
		int threadId = 1;
		
		// Crear socket servidor
		ServerSocket serSock = new ServerSocket(PORT);
		System.out.println("Servidor engegat");

		try {
			// Processar connexions
			do {
				// Esperar connexió
				System.out.println("Esperant connexió");
				Socket sock = serSock.accept();
				System.out.println("Client conncetat: " + sock.toString());
				// Crear fil connexió i engegar
				ConnectionThread conSock = new ConnectionThread(serSock, sock, threadId++);
				conSock.start();
			} while(true);
		} catch (Exception e) {
			System.out.println("Servidor aturat");
		}
	}
}

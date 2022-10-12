package cipds9.dateservice.server;

import java.net.ServerSocket;
import java.net.Socket;

public class SecureThreadsServer {
	
	static final int PORT = 5555;
	
	public static void main(String[] args) throws Exception {
		int numFil = 1;
		
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
				SecureConnectionThread conSock = new SecureConnectionThread(serSock, sock, numFil++);
				conSock.start();
			} while(true);
		} catch (Exception e) {
			System.out.println("Servidor aturat");
		}
	}
}

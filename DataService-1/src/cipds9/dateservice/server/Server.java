package cipds9.dateservice.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
	
	static final int PORT = 5555;

	public static void main(String[] args) throws Exception {

		// Crear socket servidor
		ServerSocket serSock = new ServerSocket(PORT);
		System.out.println("Servidor engegat");
		
		/** 
		 * Comandes 
		 * 0 - Fi connexió
		 * 1 - Continuar
		 * Altre - Tancar servidor
		 */
		int com = 1;
	
		// Processar connexions
		do {
			// Esperar connexió
			System.out.println("Esperant connexió");
			Socket conSock = serSock.accept();
			DataInputStream in = new DataInputStream(conSock.getInputStream());
			DataOutputStream out = new DataOutputStream(conSock.getOutputStream());
			System.out.println("Client conncetat: " + conSock.toString());
			// Processar comandes
			com = commandProcess(in, out);
			// Tancar connexió
			conSock.close();
			in.close();
			out.close();
		} while(com < 2);

		// Tancar servidor
		serSock.close();
		System.out.println("Servidor aturat");
	}
	
	public static int commandProcess(DataInputStream in, DataOutputStream out) throws Exception {
		int com = 1;
		// Processar comandes
		do {
			// Llegir comanda
			System.out.println("Esperant comanda");
			com = in.readInt();
			System.out.println("Comanda client " + Integer.toString(com));
			// Preparar resposta i respondre
			String d = (new Date(System.currentTimeMillis())).toString();
			System.out.println("Data del servidor " + d);
			out.writeUTF(d);
		} while(com == 1);
		return com;
	}
}

package cipds9.dateservice.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	static int PORT = 5555;
	static String ADDR = "127.0.0.1";
	
	public static void main(String[] args) throws Exception {
		
		// Crear socket client i connectar a servidor
		Socket conSock = new Socket(ADDR, PORT);
		DataInputStream in = new DataInputStream(conSock.getInputStream());
		DataOutputStream out = new DataOutputStream(conSock.getOutputStream());
		System.out.println("Client connectat");
		
		/** 
		 * Comandes 
		 * 0 - Fi connexió
		 * 1 - Continuar
		 * Altre - Tancar servidor
		 */
		int com = 1;
		// Lector consola
		Scanner sc = new Scanner(System.in);
		
		// Processar comandes
		while(com == 1) {
			// Demanar comanda
			System.out.println("Comanda (0 - Tancar connexió, 1 - Data, Altre - Tancar client i servidor): ");
			try {
				com = Integer.parseInt(sc.nextLine());
				if (com < 0 || com > 2) com = 2;
			} catch (Exception e) {
				com = 2;
			}
			// Enviar comanda
			out.writeInt(com);
			// Llegir resposta
			String d = null;
			d = in.readUTF(); 
			System.out.println("Resposta servidor " + d);
		}
		
		// Tancar connexió i lector consola
		conSock.close();
		in.close();
		out.close();
		sc.close();
		System.out.println("Client aturat");
	}
}

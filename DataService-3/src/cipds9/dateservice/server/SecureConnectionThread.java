package cipds9.dateservice.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import cipds9.dateservice.misc.StringCipher;

public class SecureConnectionThread extends Thread {
	
	private ServerSocket serSock;
	private Socket sock;
	private DataInputStream in;
	private DataOutputStream out;
	private String threadId;
	
	public SecureConnectionThread(ServerSocket serSock, Socket conSock, int threadId) throws Exception {
		this.serSock = serSock;
		sock = conSock;
		in = new DataInputStream(sock.getInputStream());
		out = new DataOutputStream(sock.getOutputStream());
		this.threadId = Integer.toString(threadId);
	}
	
	@Override
	public void run() {
		int com = 1;
		println("Fil engegat");
		try {
			// Processar comandes
			com = commandProcess(in, out);
			// Tancar connexió
			sock.close();
			in.close();
			out.close();
			// Tancar servidor
			if (com == 2) {
				serSock.close();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		println("Fil aturat");
	}
	
	private int commandProcess(DataInputStream in, DataOutputStream out) throws Exception {
		int com = 1;
		// Processar comandes
		do {
			// Llegir comanda
			println("Esperant comanda");
			com = in.readInt();
			println("Comanda client " + Integer.toString(com));
			// Preparar resposta i respondre
			String d = (new Date(System.currentTimeMillis())).toString();
			println("Data del servidor " + d);
			writeCipherResponse(d);
		} while(com == 1);
		return com;
	}
	
	private void println(String txt) {
		System.out.println(threadId + " " + txt);
	}
	
	private void writeCipherResponse(String response) throws Exception {
		byte[] resBytes = StringCipher.encrypt(response);
		out.writeInt(resBytes.length);
		out.write(resBytes);
	}
}

package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class AnswerHandler implements Runnable {
	Socket serverConnection;
	BufferedReader myI = null;

	public AnswerHandler(Socket connection) {
		serverConnection = connection;

	}

	public String interpretMessage(String[] message) {
		if (message.length == 0) {
			return "LEERE NACHRICHT ERHALTEN";
		}
		if (message[0].substring(0, 0).equals("E")) {
			return "ERROR: " + message[0];
		}
		if (message[0].substring(0, 0).equals("N")) {
			String nachricht = "";
			for (int i = 1; i < message.length; i++) {
				nachricht = nachricht + message[i];
			}
			return "Nachrichten erhalten: " + message[0].substring(1, message[0].length()) + nachricht;
		}
		return null;
	}

	@Override
	public void run() {
		try {
			myI = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));
		} catch (IOException e) {
			System.out.println("IO EXCEPTION");
		}
		while (true) {
			String line;
			try {
				line = myI.readLine();

				if (myI != null) {
					System.out.println(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTest implements Runnable {
	Socket serverConnection;

	public ClientTest(String ip, String port) {
		serverConnection = new Socket();
		try {
			serverConnection.connect(new InetSocketAddress(ip, Integer.parseInt(port)));

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dropConnection() {
		PrintWriter out;
		try {
			out = new PrintWriter(serverConnection.getOutputStream(), true);
			out.println("X ");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void customMessage(String message) {
		PrintWriter out;
		try {
			out = new PrintWriter(serverConnection.getOutputStream(), true);
			out.println(message);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void askMultiTheme(String[] themes) {

		PrintWriter out;
		try {
			String myT = "";
			for (int i = 0; i < themes.length; i++) {
				myT = myT + " " + themes[i];
			}
			out = new PrintWriter(serverConnection.getOutputStream(), true);
			System.out.print("Ask Themes");
			out.println("L " + myT);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void askTheme(String theme) {
		PrintWriter out;
		try {
			out = new PrintWriter(serverConnection.getOutputStream(), true);
			System.out.print("Ask Theme");
			out.println("T " + theme);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void askTime(long time) {
		PrintWriter out;
		try {
			out = new PrintWriter(serverConnection.getOutputStream(), true);
			out.println("W " + time);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void WriteMessages(int n, String[] themes, String[] messages) {
		PrintWriter out;
		try {
			out = new PrintWriter(serverConnection.getOutputStream(), true);
			out.println("P " + n);
			out.flush();
			for (int i = 0; i < n; i++) {
				WriteMessage(System.currentTimeMillis(), themes[i], messages[i]);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void WriteMessage(long timestamp, String theme, String... lines) {
		PrintWriter out;
		try {
			out = new PrintWriter(serverConnection.getOutputStream(), true);
			out.println(lines.length);
			out.println(timestamp + " " + theme);

			for (int i = 0; i < lines.length; i++) {
				// System.out.println(lines[i]);
				out.println(lines[i]);
			}
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Thread myT = new Thread(new ClientTest(args[0], args[1]));
		myT.start();
	}

	@Override
	public void run() {
		// String themes[] = { "Fussball" , "lol" , "GOT", "lol"};
		// String messages[] = { "FC Bayern hat ein Tor geschossen","Es ist
		// ein AD-Carry", "Rob Stark ist tot", "Camille ist OP"};
		// String askThemes[] = {"Fussball", "lol", "Dota"};
		// WriteMessages(themes.length,themes,messages);
		// askTime(0);
		// askTheme("lol");
		// askMultiTheme(askThemes);
		// customMessage("LOLOLOLOLOl");
		// dropConnection();
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(serverConnection.getInputStream()));
		System.out.println("answer start");
		Thread answer = new Thread(new AnswerHandler(serverConnection));
		System.out.println("answer start");

		answer.start();
		System.out.println("answer start");
		PrintWriter out;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			out = new PrintWriter(serverConnection.getOutputStream(), true);
			WriteMessage(System.currentTimeMillis(), "GOT", "Mein name ist Friedemann");
			while (true) {
				String input = in.readLine();
				out.println(input);
				out.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

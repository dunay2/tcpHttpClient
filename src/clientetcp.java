import java.net.*;
import java.io.*;

public class clientetcp {

	static final byte HTTP_PORT = 80;
	static final String MY_MAIL = "mymail@server.edu";

	public static void main(String argv[]) throws IOException {

		if (argv.length == 0) {
			System.err.println("java clientetcp servidor");
			System.exit(1);
		}

		Socket socket = null;
		InetAddress address;
		String mensaje = "";

		try {
			/*
			 * Establish HTTP connection with remote server
			 */
			address = InetAddress.getByName(argv[0]);
			String serverName = argv[0];
			socket = new Socket(address, HTTP_PORT);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			mensaje = "OPTIONS / HTTP/1.1\r\n";
			mensaje = mensaje + "User-Agent: " + MY_MAIL + "\r\n";
			mensaje = mensaje + "Accept: */*\r\n";
			mensaje = mensaje + "Cache-Control: no-cache\r\n";
			mensaje = mensaje + "Host: " + serverName + "\r\n";
			mensaje = mensaje + "accept-encoding: gzip, deflate\r\n";
			mensaje = mensaje + "content-length: 0\r\n";
			mensaje = mensaje + "Connection: keep-alive\r\n";
			mensaje = mensaje + "\r\n";

			System.out.println(mensaje);

			out.writeBytes(mensaje);

			// Get response
			BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;

			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} finally {

			if (socket != null) {
				System.out.println("Cerrando socket cliente ...");
				socket.close();
			}
		}

	}
}

import java.net.*;
import java.io.*;

public class clientetcp {

    public static void main(String argv[]) throws IOException {

        /*
         * Leemos los argumentos del programa para saber a qué
         * servidor hay que conectarse.
         */
        if (argv.length == 0) {
            System.err.println("java clientetcp servidor");
            System.exit(1);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = null;
        InetAddress address;
        byte[] mensaje_bytes = new byte[256];
        String mensaje = "";

        try {
            /*
             * Establecemos una conexión con el servidor en el puerto 6001 y 
             * enviamos lo que el usuario escribe por teclado. Acabamos con la
             * palabra end.
             */
            address = InetAddress.getByName(argv[0]);
            socket = new Socket(address, 6001);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            do {
                mensaje = in.readLine();
                out.writeUTF(mensaje);
            } while (!mensaje.startsWith("end"));

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

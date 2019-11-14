import java.net.*;
import java.io.*;

public class servidortcp {

    public static void main(String argv[]) throws IOException {
        ServerSocket socket = null;
        String mensaje;

        try {

            /*
             * Creamos un socket de tipo servidor que escucha en el puerto
             * TCP 6001 y espera conexiones de los clientes.
             * Este programa recibe lo que viene del socket y lo muestra
             * por pantalla.
             */
            socket = new ServerSocket(6001);
            Socket socket_cli = socket.accept();
            DataInputStream in = new DataInputStream(socket_cli.getInputStream());
            do {
                mensaje = in.readUTF();
                System.out.println(mensaje);
            } while (1 > 0);
        } catch (Exception e) {
            if (socket != null) {
                System.out.println("Cerrando socket servidor ...");
                socket.close();
            }
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}

package dsa.eetac.upc.edu;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    Socket cs; //Socket del cliente
    DataOutputStream salidaServidor; //flujo de datos de salida
    int PUERTO = 1234; //Puerto para la conexión
    String HOST = "localhost"; //Host para la conexión

    public Client() throws IOException //Se usa el constructor para cliente de Conexion
    {
        cs = new Socket(HOST, PUERTO);
    }

    public void startClient() //Método para iniciar el cliente
    {
        try
        {
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());

            //Se enviarán dos mensajes
            for (int i = 0; i < 2; i++)
            {
                //Se escribe en el servidor usando su flujo de datos
                salidaServidor.writeUTF("Este es el mensaje número " + (i+1) + "\n");
            }
            cs.close();//Fin de la conexión
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException
    {
        Client cli = new Client(); //Se crea el cliente

        System.out.println("Iniciando cliente\n");
        cli.startClient(); //Se inicia el cliente
    }
}

package dsa.eetac.upc.edu;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Roberto on 14/02/2017.
 */
public class Server {

    int PUERTO = 1234; //Puerto para la conexión
    String HOST = "localhost"; //Host para la conexión
    String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    ServerSocket ss; //Socket del servidor
    Socket cs; //Socket del cliente
    DataOutputStream salidaCliente; //Flujo de datos de salida


    public Server() throws IOException //Se usa el constructor para servidor de Conexion
    {
        ss = new ServerSocket(PUERTO);//Se crea el socket para el servidor en puerto 1234
        cs = new Socket(); //Socket para el cliente
    }

    public void startServer()//Método para iniciar el servidor
    {
        try
        {
            System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(cs.getOutputStream());

            //Se le envía un mensaje al cliente usando su flujo de salida
            salidaCliente.writeUTF("Petición recibida y aceptada");

            //Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            while((mensajeServidor = entrada.readLine()) != null) //Mientras haya mensajes desde el cliente
            {
                //Se muestra por pantalla el mensaje recibido
                System.out.println(mensajeServidor);
            }

            System.out.println("Fin de la conexión");

            ss.close();//Se finaliza la conexión con el cliente
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws IOException
    {
        Server serv = new Server(); //Se crea el servidor

        System.out.println("Iniciando servidor\n");
        serv.startServer(); //Se inicia el servidor
    }

}

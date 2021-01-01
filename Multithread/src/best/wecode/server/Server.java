package best.wecode.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.io.*;

public class Server {

    public static void main (String[] args) {
        Server tcpServer = new Server();
        tcpServer.start();
    }

    public void start()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(6789);
            while (true)
            {
                System.out.println("Server is listening on: " + serverSocket);
                Socket socket = serverSocket.accept();
                System.out.println("New client: " + socket);
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("Server Error!");
        }
    }
}

class ServerThread extends Thread
{
    ServerSocket server      = null;
    Socket client            = null;
    String stringaRicevuta   = null;
    String stringaModificata = null;

    BufferedReader   inDalClient;
    DataOutputStream outVersoClient;

    public ServerThread (Socket socket) {
        this.client = socket;
    }

    public void run() {
        try {
            comunica();
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void comunica ()throws Exception {

        inDalClient      = new BufferedReader(new InputStreamReader (client.getInputStream()));
        outVersoClient   = new DataOutputStream(client.getOutputStream());
        while (true) {

            stringaRicevuta = inDalClient.readLine();
            if (stringaRicevuta == null || stringaRicevuta.equals("FINE")) {
                outVersoClient.writeBytes(stringaRicevuta+" (=>server in chiusura...)" + '\n');
                System.out.println("Echo sul server in chiusura  :" + stringaRicevuta);
                break;
            }
            else {
                // outVersoClient.writeBytes(+stringaRicevuta);
                outVersoClient.writeBytes(stringaRicevuta+" (ricevuta e ritrasmessa dal server)" + '\n');
                System.out.println("6 Echo sul server :" + stringaRicevuta);
            }
        }

        outVersoClient.close();
        inDalClient.close();
        System.out.println("Closing socket: " + client);
        client.close();
    }
}
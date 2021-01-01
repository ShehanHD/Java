package best.wecode.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    String nomeServer = "localhost";
    int port = 6789;
    Socket socket;
    BufferedReader input;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.input = new BufferedReader(new InputStreamReader(System.in));
        client.socket = new Socket(client.nomeServer,client.port);
        client.outVersoServer = new DataOutputStream(client.socket.getOutputStream());
        client.inDalServer = new BufferedReader(new InputStreamReader (client.socket.getInputStream()));

        boolean s = client.connect();

        if (s) {
            System.out.println("OK");
        }
    }

    public boolean connect() {
        boolean connected = false;

        System.out.println("Connecting...");
        try
        {
            System.out.println("Connected");
            connected = true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Connection Error!");
        }

        return connected;
    }
}

package SocketInt.Client;

import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Socket clientServer = new Socket("localhost", 9999);

            clientServer.getOutputStream().write(0);
            clientServer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

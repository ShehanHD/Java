package SocketInt.Server;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket myServer = new ServerSocket(9999);
            int x;

            while (true) {
                Socket S = myServer.accept();
                x = S.getInputStream().read();
                System.out.println("Int from client: " + x);

                if (x == 0) {
                    myServer.close();
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

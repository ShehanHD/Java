package SocketInt.Client;

import java.net.Socket;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        int x = 1;
        Scanner scan = new Scanner(System.in);

        while (x != 0) {
            x = scan.nextInt();
            send(x);
        }
    }

    static void send(int num) {
        try {
            Socket clientServer = new Socket("localhost", 9999);

            clientServer.getOutputStream().write(num);
            clientServer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

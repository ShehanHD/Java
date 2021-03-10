package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

public class Server {
    private static final Cinema cinema = new Cinema();

    public static void main(String[] args) {
        ServerSocket server = null;

        try{
            server = new ServerSocket(6666);
            server.setReuseAddress(true);

            while (true){
                Socket newClient = server.accept();

                BookingHandler clientSock = new BookingHandler(newClient);
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class BookingHandler implements Runnable{
        private final Socket clientSocket;

        public BookingHandler(Socket newClient) throws IOException {
            clientSocket = newClient;
        }

        public void run(){
            BufferedReader in = null;

            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String line;

                if ((line = in.readLine()) != null){
                    String[] param = line.split("/");

                    switch (param[1]){
                        case "getTimer" -> cinema.getTimer(clientSocket);
                        case "getSeats" -> cinema.getSeats(clientSocket);
                        case "bookSeat" -> cinema.bookSeat(clientSocket, (Integer.parseInt(param[2]) - 1), colToInt(param[3]));
                        case "client" -> cinema.addUser(clientSocket, param[2], param[3], param[4], param[5], param[6]);
                        case "getUsers" -> cinema.printUsers();
                    }
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            finally {
                if (in != null){
                    try {
                        in.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private int colToInt(String col){
            int c = col.charAt(0);

            if (c > 64 && c < 91){
                c = c - 65;
            }
            else{
                c = c - 97;
            }

            return c;
        }
    }
}
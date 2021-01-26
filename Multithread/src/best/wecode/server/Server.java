package best.wecode.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class Server {
    public static void main(String[] args)
    {
        ServerSocket server = null;

        try {
            server = new ServerSocket(6789);
            server.setReuseAddress(true);

            while (true) {
                Socket client = server.accept();

                System.out.println("New client connected " + client.getInetAddress().getHostAddress());

                ClientHandler clientSock = new ClientHandler(client);

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

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        List<User> user = new ArrayList<>();

        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }

        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String line;

                while ((line = in.readLine()) != null) {
                    System.out.printf("Sent from the client: %s\n", line);
                    String[] param = line.split(";");

                    switch (param[0]){
                        case "login" -> out.println(login(param[1], param[2]));
                        case "register" -> out.println(register(param[1], param[2], param[3]));
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private String login(String Username, String Password) {
            user.add(new User("Shehan", "H.D", "1234"));
            user.add(new User("bbb", "aaa", "1234"));

            for (User u : user) {
                if (u.name.equals(Username) && u.password.equals(Password)) {
                    return "true";
                }
            }

            return "false";
        }

        private String register(String name, String surname, String password) {
            user.add(new User(name, surname, password));

            return "true";
        }
    }
}

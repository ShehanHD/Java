package best.wecode.helpers;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;

public class User {
    String name;
    String surname;
    String password;
    DataOutputStream dout;
    DataInputStream dis;
    Socket socket;

    public User(Socket s) throws IOException {
        socket = s;
    }

    public boolean register(String name, String surname, String password, String repeatPassword) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        this.name = cleanString(name);
        this.surname = cleanString(surname);
        if(password.equals(repeatPassword)){
            this.password = password;
        }
        else{
            System.out.println("\nYour passwords doesn't match\n");
            return false;
        }

        out.println("register;"+name+";"+surname+";"+password);
        out.flush();

        return Boolean.valueOf(in.readLine());
    }

    public boolean login(String name, String password) throws IOException {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("login;"+name+";"+password);
            out.flush();

            return Boolean.valueOf(in.readLine());
    }

    private String cleanString(@NotNull String str) throws IOException { return str.strip(); }
}

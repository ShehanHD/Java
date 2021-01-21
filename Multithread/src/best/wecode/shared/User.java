package best.wecode.shared;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.Socket;

public class User {
    String name;
    String surname;
    String password;

    public boolean register(Socket socket, String name, String surname, String password, String repeatPassword) throws IOException {
        this.name = cleanString(name);
        this.surname = cleanString(surname);
        if(password.equals(repeatPassword)){
            this.password = password;
        }
        else{
            System.out.println("\nYour passwords doesn't match\n");
            return false;
        }

        return true;
    }

    public boolean login(Socket socket, String name, String password) throws IOException{
        return true;
    }

    private String cleanString(@NotNull String str) throws IOException { return str.strip(); }
}

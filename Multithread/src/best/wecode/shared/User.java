package best.wecode.shared;

import java.io.IOException;
import java.net.Socket;

public class User {
    String name;
    String surname;
    String password;

    public boolean register(Socket socket, String name, String surname, String password, String repeatPassword) throws IOException {
        this.name = cleanString(name);
        this.surname = cleanString(surname);
        if(comparePassword(password, repeatPassword)){
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

    private String cleanString(String str) throws IOException {
        return str.strip();
    }

    private Boolean comparePassword(String pass1, String pass2){
        return pass1.equals(pass2);
    }

}

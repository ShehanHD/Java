package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Cinema{
    String[][] hall = new String[10][15];
    List<User> user = new ArrayList<>();
    String closingDate = "2021-04-08;12:35:00";

    Cinema(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 15; j++){
                hall[i][j] = "L";
            }
        }
    }

    private String print(){
        StringBuilder str = new StringBuilder();
        for(int i=0; i<10; i++) {
            for(int j=0; j<15; j++) {
                str.append(j != 14 ? hall[i][j] + "," : hall[i][j]);
            }
            str.append(";");
        }

        return str.toString();
    }

    void getSeats(Socket clientSocket) throws IOException {
        DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
        dout.writeUTF(print());
        dout.flush();
        dout.close();
    }

    void getTimer(Socket clientSocket) throws IOException {
        DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

        dout.writeUTF(closingDate);

        dout.flush();
    }

    void bookSeat(Socket clientSocket, int r, int s) throws IOException {
        DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

        if(hall[r][s].equals("L")) {
            hall[r][s] = "O";
            dout.writeUTF(print());
        }
        else{
            dout.writeUTF("0");
        }

        dout.flush();
    }

    void addUser(Socket clientSocket, String n, String s, String t, String r, String c) throws IOException {
        DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
        user.add(new User(n, s, t, r, c));
        dout.writeUTF("1");
        dout.flush();
    }

    void printUsers(){
        for (User user1 : user) {
            System.out.println(user1.name+","+user1.surname+","+user1.telephoneNumber+","+user1.row+","+user1.col+";");
        }
    }
}


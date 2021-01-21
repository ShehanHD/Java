package best.wecode.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    Socket s;
    DataOutputStream dout;
    DataInputStream dis;
    Scanner input;

    public Main() throws IOException {
        s = new Socket("localhost",6666);
        dout = new DataOutputStream(s.getOutputStream());
        dis = new DataInputStream(s.getInputStream());
        input = new Scanner(System.in);
    }

    public static void main(String[] args) throws IOException {
        Main client = new Main();

        client.sentNumbers();
    }

    public void sentNumbers() throws IOException {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);
        String toSend = "";
        String x;
        int i = 0;

        do {
            System.out.print("\nAggiungi numero "+ (i+1) +"\nexit per Uscire\nInserisci > ");
            x = input.next();

            Matcher m = p.matcher(x);

            if(m.matches()) {
                toSend += x+";";
                i++;
            }
            else if(x.contains("exit")){
                System.out.println("\nGrazie! Arrividerci!");
            }
            else {
                System.out.println("\nError! controlla il inserimento");
            }
        }while(!x.contains("exit"));

        dout.writeUTF(toSend);
        String  str = (String)dis.readUTF();

        System.out.println("\nI numeri sono : " + toSend.replace(";", " "));
        System.out.println("La Media = " + str);

        dout.flush();
        dout.close();
        s.close();
    }
}

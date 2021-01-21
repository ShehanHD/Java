package best.wecode.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        try{
            while(true) {
                ServerSocket ss = new ServerSocket(6666);
                System.out.println("listening on port: 6666");
                String regex = "[0-9;]+";
                Pattern p = Pattern.compile(regex);

                Socket s = ss.accept();

                DataInputStream dis = new DataInputStream(s.getInputStream());
                String str = (String) dis.readUTF();
                System.out.println("incoming= " + str);

                Matcher m = p.matcher(str);

                if(m.matches()) {
                    calculateAVG(s, str);
                }
                else{
                    System.out.println("Error!");
                }
                ss.close();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private static void calculateAVG(Socket s, String str) throws IOException {
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        String[] numbers = str.split("[;]");
        double sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            sum += Float.parseFloat(numbers[i]);
        }

        dout.writeUTF(String.valueOf(sum/ numbers.length));
        dout.flush();
        dout.close();
    }
}

package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try{
            while(true) {
                ServerSocket ss = new ServerSocket(6666);
                String regex = "[0-9-*/+]+";
                Pattern p = Pattern.compile(regex);

                Socket s = ss.accept();

                DataInputStream dis = new DataInputStream(s.getInputStream());
                String str = (String) dis.readUTF();
                System.out.println("incoming= " + str);

                Matcher m = p.matcher(str);

                if(m.matches()) {
                    calculator(s, str);
                }
                else{
                    transform(s, str);
                }
                ss.close();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    static void transform(Socket s, String str){
        try{
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            if(str.equals(str.toUpperCase())) {
                dout.writeUTF(str.toLowerCase());
            }
            else{
                dout.writeUTF(str.toUpperCase());
            }

            dout.flush();
            dout.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    static void calculator(Socket s, String str) {
        try {
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            String[] operandi = str.split("[+-/*]");
            System.out.println(operandi);
            Float op1 = Float.parseFloat(operandi[0]);
            Float op2 = Float.parseFloat(operandi[1]);

            if (str.contains("+")) {
                System.out.println(op1 + op2);
                dout.writeUTF(String.valueOf(op1 + op2));
                dout.flush();
            } else if (str.contains("-")) {
                System.out.println(op1 - op2);
                dout.writeUTF(String.valueOf(op1 - op2));
                dout.flush();
            } else if (str.contains("*")) {
                System.out.println(op1 * op2);
                dout.writeUTF(String.valueOf(op1 * op2));
                dout.flush();
            } else if (str.contains("/")) {
                System.out.println(op1 / op2);
                dout.writeUTF(String.valueOf(op1 / op2));
                dout.flush();
            } else {
                System.out.println("Error: hint => num1 [-+/*] num2");
            }

            dout.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}

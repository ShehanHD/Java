package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            Scanner input = new Scanner(System.in);
            int x = 0;
            do {
                System.out.print("\n1 per Convertitore stringa maiuscolo/minuscolo\n2 per Calcolatrice\n0 per Uscire\nInserisci > ");
                x = input.nextInt();

                switch (x){
                    case 1:
                        toUpper();
                        break;
                    case 2:
                        Calculator();
                        break;
                    default:
                        System.out.println("Riprova con un indice valido!!!\n");
                        continue;
                }
            }while(x != 0);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    static void toUpper(){
        try{
            Socket s=new Socket("localhost",6666);
            Scanner input = new Scanner(System.in);

            System.out.print("Insert your String to Convert: ");
            String toConvert = input.next();

            DataOutputStream dout=new DataOutputStream(s.getOutputStream());

            dout.writeUTF(toConvert);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            String  str = (String)dis.readUTF();

            System.out.println("From Server= "+str);

            dout.flush();
            dout.close();

            s.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    static void Calculator(){
        try{
            Socket s=new Socket("localhost",6666);
            Scanner input = new Scanner(System.in);

            System.out.print("Inserisci il tua funziona: ");
            String eq = input.next();

            DataOutputStream dout=new DataOutputStream(s.getOutputStream());

            dout.writeUTF(eq);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            String  str = (String)dis.readUTF();

            System.out.println("\nRisultato è " + str);

            dout.flush();
            dout.close();

            s.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}

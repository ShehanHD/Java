package best.wecode.client;

import best.wecode.shared.Api;
import best.wecode.shared.Item;
import best.wecode.shared.User;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    String host = "localhost";
    int port = 6789;
    Socket socket;
    Scanner input;

    public Client() {
        input = new Scanner(System.in);
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        boolean isConnected = client.connect();

        if (isConnected) {
            System.out.println("Connected");
            client.menu();
        }
        else{
            System.out.println("Connection Error!");
        }
    }

    public boolean connect() {
        boolean connected = false;

        System.out.println("Connecting...");
        try
        {
            socket = new Socket(host, port);
            connected = true;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return connected;
    }

    public void menu() throws IOException {
        String uInput;

        do {
            System.out.print("\nPress 1 for Login\nPress 2 for Registration\nPress 0 For exit\ninsert> ");
            uInput = input.next();

            switch (uInput) {
                case "1" -> login();
                case "2" -> register();
                case "0" -> System.out.println("Thank You!");
                default -> System.out.println("Please check your input!");
            }
        }while (!uInput.equals("0"));

    }

    private void login() throws IOException{
        User user = new User();
        String name;
        String password;
        boolean done;

        do {
            System.out.print("Username: ");
            name = input.next();
            System.out.print("Password: ");
            password = input.next();

            done = user.login(socket, name, password);

            if(!done){
                System.out.println("Please check your name or password");
            }
            else{
                dashboard();
            }
        }while(!done);

    }

    private void register() throws IOException {
        User user = new User();
        String name;
        String surname;
        String password;
        String repeatPassword;
        String uInput;
        boolean done;

        do {
            System.out.print("Name: ");
            name = input.next();
            System.out.print("Surname: ");
            surname = input.next();
            System.out.print("Password: ");
            password = input.next();
            System.out.print("Repeat-Password: ");
            repeatPassword = input.next();

            done = user.register(socket, name, surname, password, repeatPassword);

            if(!done){
                System.out.println("\nOops, Something went wrong!\nInsert anything for try again!\n0 for Exit\nInsert> ");
                uInput = input.next();

                if(uInput.contains("0")){
                    done = true;
                }
            }
            else{
                dashboard();
            }
        }while(!done);
    }

    private void dashboard() throws IOException {
        String uInput;
        Api api = new Api(socket);
        List<Item> items;

        do {
            System.out.println("\tDASHBOARD\n");
            // print items
            items = api.getItems();
            printDashboard(items);

            System.out.println("\n0 to exit\nOr select your item code\n> ");
            uInput = input.next();

            // call bidding room
            biddingRoom(uInput);

        }while (!uInput.contains("0"));
    }

    private void printDashboard(List<Item> items){
        System.out.println("\tDashboard\n");

        System.out.println("\tItem COD\tName\tStarting Bid\tLast Bid\n");
    }

    private void biddingRoom(String id) throws IOException {
        Api api = new Api(socket);
        Item item;

        item = api.getItem(id);
    }
}

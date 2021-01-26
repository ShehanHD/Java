package best.wecode.client;

import best.wecode.helpers.Api;
import best.wecode.helpers.Item;
import best.wecode.helpers.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
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

        //int[] x = {1, 1, 1};
        //String xx = Arrays.toString(x);
        //String[] xxx = (xx.replaceAll("\\[|\\]", "").split(", "));
        //System.out.println(Arrays.toString(xxx));

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
        User user = new User(socket);
        String name;
        String password;
        Boolean done;
        DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        do {
            System.out.print("Username: ");
            name = input.next();
            System.out.print("Password: ");
            password = input.next();

            done = user.login(name, password);

            if(!done){
                System.out.println("Please check your name or password");
            }
            else{
                dashboard();
            }
        }while(!done);

    }

    private void register() throws IOException {
        User user = new User(socket);
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

            done = user.register(name, surname, password, repeatPassword);

            if(!done){
                System.out.println("\nOops, Something went wrong!\nInsert anything for try again!\n0 for Exit\nInsert> ");
                uInput = input.next();

                if(uInput.equals("0")){
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
            System.out.println("\t\t\t\t\t\t\t\t\t\tDASHBOARD");
            // print items
            items = api.getItems();
            printDashboard(items);

            System.out.print("\n0 to logout\nOr select your item code\nInsert > ");
            uInput = input.next();

            // call bidding room
            if(!uInput.equals("0")) {
                biddingRoom(uInput);
            }

        }while (!uInput.equals("0"));
    }

    private void printDashboard(List<Item> items){
        System.out.format("%15s%15s%15s%15s%15s%15s\n", "Item COD", "Name", "Starting Bid", "Last Bid", "Starts At", "Ends At");

        for (Item item : items) {
            System.out.format("%15s%15s%15s%15s%15s%15s\n", item.cod, item.name, item.openingBid, item.lastBid, item.startsAt, item.endsAt);
        }
    }

    private void biddingRoom(String id) throws IOException {
        Api api = new Api(socket);
        Item item;
        String uInput;

        do {
            item = api.getItem(id);

            System.out.format("%15s%15s%15s%15s%15s%15s\n", "Item COD", "Name", "Starting Bid", "Last Bid", "Starts At", "Ends At");
            System.out.format("%15s%15s%15s%15s%15s%15s\n", item.cod, item.name, item.openingBid, item.lastBid, item.startsAt, item.endsAt);

            System.out.print("\n0 to go back to Dashboard\nOr insert a new Bid\nInsert > ");
            uInput = input.next();

            if(!uInput.equals("0")){
                if (api.newBid(uInput)) {
                    System.out.println("New bid successfully submitted");
                } else {
                    System.out.println("Something went wrong, please try again");
                }
            }
        }while(!uInput.equals("0"));

    }
}

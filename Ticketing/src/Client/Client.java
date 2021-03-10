package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Client {
    private static final String host = "localhost";
    private static final int port = 6666;
    private static Socket socket;
    private static Scanner input;
    private static long[] countDown = new long[0];
    private static String line = "";

    public Client() throws IOException {
        socket = new Socket(host, port);
        input = new Scanner(System.in);
    }

    public static void main(String[] args) throws IOException, ParseException {
        Client client = new Client();
        String userInput;

        do {
            countDown = client.getTimer();

            if ((countDown[0] == 0 && countDown[1] >= 30) || (countDown[0] > 0)) {
                socket = new Socket(host, port);
                System.out.println("Tra "+countDown[0]+" giorni, "+((countDown[1]/60))+" ore e "+((countDown[1]%60))+" minuti a scadere il tempo di prenotazioni");

                System.out.print("\n1 per visualizza sala\n2 per prenotare una sedia\n0 per Uscire\nScelta > ");
                userInput = input.next();

                switch (userInput) {
                    case "1" -> client.getSeats();
                    case "2" -> client.bookSeat();
                    case "3" -> client.getUsers();
                }
            }
            else{
                System.out.println("Prenotazioni sono chiusi");
                userInput = "0";
            }
        }while (!userInput.equals("0"));

        socket.close();
    }

    public void send(String s) throws IOException {
        PrintWriter dout = new PrintWriter(socket.getOutputStream(), true);
        dout.println(s);
    }

    public String get() throws IOException {
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        return dis.readUTF();
    }

    private void getUsers() throws IOException {
        send("api/getUsers");
    }

    public long[] getTimer() throws IOException, ParseException {

        if (line.isEmpty()) {
            send("api/getTimer");
            line = get();
        }

        String[] dt = line.split(";");

        String dateNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String timeNow = new SimpleDateFormat("HH:mm:ss").format(new Date());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        Date tShow = format.parse(dt[1]);
        Date tNow = format.parse(timeNow);

        LocalDate showDay = LocalDate.parse(dt[0] , DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate today = LocalDate.parse(dateNow , DateTimeFormatter.ISO_LOCAL_DATE);

        Duration date = Duration.between(today.atStartOfDay(), showDay.atStartOfDay());
        Duration dateTime = Duration.between(tNow.toInstant(), tShow.toInstant());

        long x = dateTime.toMinutes() < 0 ? dateTime.toMinutes() * -1 : dateTime.toMinutes();

        return new long[]{date.toDays(), x};
    }

    public void getSeats() throws IOException {
        send("api/getSeats");
        printHall(get());
    }

    public void bookSeat() throws IOException {
        String row;
        String col;

        System.out.println("\t\t\tPRENOTAZIONE BIGLIETTO\n");

        System.out.print("Fila[1-10]  => ");
        row = input.next();

        System.out.print("Sedia[A-O]  => ");
        col = input.next();

        if (row.matches("^([1-9]|10)$") && col.matches("^([A-O]|[a-o])$") ) {
            String s = "api/bookSeat/" + row + "/" + col;

            send(s);
            String str = get();

            if (str.equals("0")) {
                System.out.println("Questa sedia e gia riservata!");
            }
            else if(str.equals("expire")){
                System.out.println("Tempo Scaduto");
            }
            else {
                confirmation(row, col);
            }
        }
        else {
            System.out.println("Inserimento stato sbagliato!");
        }
    }

    private void confirmation(String row, String col) throws IOException {
        socket = new Socket(host, port);
        String name, surname, tele, s2;

        System.out.print("Per confemare il prenotazione inserisci i tuoi dati anegrfici!\nNome\t:");
        name = input.next();
        System.out.print("Cognome\t:");
        surname = input.next();
        System.out.print("Telefono\t:");
        tele = input.next();

        s2 = "api/client/"+name+"/"+surname+"/"+tele+"/"+row+"/"+col;
        send(s2);

        String str2 = get();

        if (str2.equals("0")){
            System.out.println("Errore, reprova!");
        }
        else if(str2.equals("expire")){
            System.out.println("Tempo Scaduto");
        }
        else {
            System.out.println("prenotazione di successo!");
        }
    }

    private void printHall(String matrix){
        String[] rows = matrix.split(";");

        System.out.println("\n\t\t\t\t\t\t\tFilm Hall\n");
        for (int i = 0; i < 11; i++) {
            if (i == 0) {
                System.out.println("\t| A | B | C | D | E | F | G | H | I | J | K | L | M | N | 0 |");
            }
            else{
                System.out.println(i + "\t| " + rows[i - 1].replace(",", " | ") + " |");
            }
            System.out.println("\t|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|");
        }
    }
}

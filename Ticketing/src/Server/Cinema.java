package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Cinema {
    String[][] hall = new String[10][15];
    List<User> user = new ArrayList<>();
    String closingDate = "2021-04-08;12:35:00";

    Cinema() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                hall[i][j] = "L";
            }
        }
    }

    private String print() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
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

    synchronized void bookSeat(Socket clientSocket, int r, int s) throws IOException, ParseException {
        long[] countDown = getTimer();
        DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

        if ((countDown[0] == 0 && countDown[1] >= 30) || (countDown[0] > 0)) {
            if (hall[r][s].equals("L")) {
                hall[r][s] = "O";
                dout.writeUTF(print());
            } else {
                dout.writeUTF("0");
            }
        } else {
            dout.writeUTF("expire");
        }
        dout.flush();
    }

    synchronized void addUser(Socket clientSocket, String n, String s, String t, String r, String c)
            throws IOException, ParseException {
        long[] countDown = getTimer();
        DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

        if ((countDown[0] == 0 && countDown[1] >= 30) || (countDown[0] > 0)) {
            user.add(new User(n, s, t, r, c));
            dout.writeUTF("1");
        } else {
            dout.writeUTF("expire");
        }
        dout.flush();
    }

    void printUsers() {
        for (User user1 : user) {
            System.out.println(user1.name + "," + user1.surname + "," + user1.telephoneNumber + "," + user1.row + ","
                    + user1.col + ";");
        }
    }

    public long[] getTimer() throws IOException, ParseException {
        String[] dt = closingDate.split(";");

        String dateNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String timeNow = new SimpleDateFormat("HH:mm:ss").format(new Date());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        Date tShow = format.parse(dt[1]);
        Date tNow = format.parse(timeNow);

        LocalDate showDay = LocalDate.parse(dt[0], DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate today = LocalDate.parse(dateNow, DateTimeFormatter.ISO_LOCAL_DATE);

        Duration date = Duration.between(today.atStartOfDay(), showDay.atStartOfDay());
        Duration dateTime = Duration.between(tNow.toInstant(), tShow.toInstant());

        long x = dateTime.toMinutes() < 0 ? dateTime.toMinutes() * -1 : dateTime.toMinutes();

        return new long[] { date.toDays(), x };
    }
}

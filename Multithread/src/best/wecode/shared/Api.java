package best.wecode.shared;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Api {


    private final DataOutputStream outToServer;
    private final BufferedReader inFromServer;

    public Api(Socket socket) throws IOException {
        outToServer = new DataOutputStream(socket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public List<Item> getItems(){
        List<Item> iList = new ArrayList<>();

        // Get data from server, unpack and prepare to create Items

        // use a loop to populate the list
            iList.add(new Item("100", "a", "s", "a", "a", "2021-01-25", "2021-01-30"));

        return iList;
    }

    public Item getItem(String id){
        // Get data from server
        Item item = new Item("100", "a", "a", "s", "s", "2021-01-25", "2021-01-30");

        return item;
    }
}

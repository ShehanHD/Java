package best.wecode.cinema.client;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    public List<Client> getClients(){
        return List.of(new Client(1L, "tests", "again", "aaa"));
    }
}

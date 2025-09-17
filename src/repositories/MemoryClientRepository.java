package repositories;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import modal.Client;
import repositories.ClientDao;

public class MemoryClientRepository implements ClientDao {

    private Map<String, Client> clientStorage = new HashMap<>();
    @Override
    public void save(String email, String password , String name) {
        Client newClient = new Client(name, email, password);
        clientStorage.put(email, newClient);
    }

    @Override
    public Collection<Client> getAllClients() {
        return clientStorage.values();
    }

    @Override
    public Optional<Client> getClientByEmail(String email) {
        return Optional.ofNullable(clientStorage.get(email));
    }
}

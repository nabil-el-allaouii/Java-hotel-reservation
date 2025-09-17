package repositories;

import java.util.Collection;
import java.util.Optional;

import modal.Client;

public interface ClientDao {
    void save(String email, String password, String name);
    Collection<Client> getAllClients();
    Optional<Client> getClientByEmail(String email);
}

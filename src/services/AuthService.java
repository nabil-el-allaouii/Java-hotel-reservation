package services;

import modal.Client;
import repositories.ClientDao;
import repositories.MemoryClientRepository;

public class AuthService {
    private final ClientDao clientRepo;
    private Client client = null;

    public AuthService(ClientDao clientRepo) {
        this.clientRepo = clientRepo;
    }

    public void register(String email, String password, String name) {
        clientRepo.save(email, password, name);
    }

    public Client login(String email, String password){
        if(!clientRepo.getClientByEmail(email).isPresent()){
            throw new IllegalAccessError("No user with this email");
        }
        Client client = clientRepo.getClientByEmail(email).get();
        if(!client.getPassword().equals(password)){
            throw new IllegalAccessError("Wrong email or password");
        }
        return client;
    }
    public boolean isLoggedIn(){
        return this.client != null;
    }

}

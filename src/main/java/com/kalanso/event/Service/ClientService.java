package com.kalanso.event.Service;

import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService extends CrudService<Client, Long> {

    @Autowired
    GestionnaireRepo gestionnaireRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    Rolerepo rolerepo;
    @Autowired
    VoyageRepo voyageRepo;
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    private ContexHolder contexHolder;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private StatutRsvtRepo statutRsvtRepo;
    @Autowired
    private Utilisateur_repo utilisateur_repo;

    public ClientService(ClientRepo clientRepo) {
        super(clientRepo);
    }

    @Override
    public Iterable<Client> getAll() {
        return super.getAll();
    }

    public long NbrUser(){
        return clientRepo.findByUsernameNF().stream().count();
    }

    public Iterable<Reservation> getReservation() {
        ReservationStatut statut = statutRsvtRepo.findByStatut("VALABLE");
       Client client = (Client) contexHolder.utilisateur();
        return reservationRepo.findByClientAndStatut(client, statut);
    }

    public List<Client> getClient(){
        return clientRepo.findByUsernameNF();
    }

    public Client getByUsername(){
        Client client = (Client) contexHolder.utilisateur();
        return clientRepo.findByUsername(client.getUsername());
    }

    /*@Override
    public Client create(Client entity) {
        //Role role = new Role();
        //role.setRole("Client");
        Role role = rolerepo.findByRole("Client");
        entity.setRole(role);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.create(entity);
    }*/

    public int ajouterClient(Client entity, MultipartFile image) throws IOException {

        //String client = (String) entity;
        Client clientT = entity;

        Optional<Utilisateur> utilisateur = utilisateur_repo.findByusername(entity.getTelephone());

        System.out.print(entity);

        System.out.print(entity);
        if(utilisateur.isPresent()){
            Utilisateur user = utilisateur.get();
            //System.out.print("ffffffffffffffffffffffffffffffffffffffffff"+user);
            return 1;
        }else{
            if (image != null && !image.isEmpty()) {
                entity.setImage(image.getBytes());
            }else {
                entity.setImage(null);
            }
            Role role = rolerepo.findByRole("Client");
            entity.setRole(role);
            entity.setUsername(entity.getTelephone());
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            clientRepo.save(entity);
            return 200;
        }
    }

    @Override
    public Client update(Long aLong, Client entity, String... ignoreProperties) {
        return super.update(aLong, entity, "id","role","statut","password");
    }

    @Override
    public void delete(Long aLong) {
        super.delete(aLong);
    }

    public List<Voyage> GetvoyageAgence(Long A){
        return voyageRepo.findByAgence_Id(A);
    }




}

package com.kalanso.event.Service;

import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class ReservationService extends CrudService<Reservation, Long> {

    @Autowired
    GestionnaireRepo gestionnaireRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    VoyageRepo voyageRepo;
    @Autowired
    ContexHolder contexHolder;
    @Autowired
    StatutRsvtRepo StatutRrepo;
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private Rolerepo rolerepo;
    @Autowired
    private StatutRsvtRepo statutRsvtRepo;

    public ReservationService(ReservationRepo reservationRepo) {
        super(reservationRepo);
    }

    @Override
    public Optional<Reservation> getById(Long aLong) {
        return super.getById(aLong);
    }

    public int generateRandomNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // Génère un nombre entre 100000 et 999999
    }

    @Override
    public Iterable<Reservation> getAll() {
        return super.getAll();
    }

    public Optional<Reservation> GetReservationById(Long aLong) {
        return reservationRepo.findById(aLong);
    }


    public Long GetNbrReservation(){
        Utilisateur user = contexHolder.utilisateur();

        ReservationStatut valable = statutRsvtRepo.findByStatut("Valable");
        ReservationStatut expirer = statutRsvtRepo.findByStatut("Expirer");

        if ( Objects.equals(user.getRole().getRole(), "AdminC")){
            AdminComp adminComp = (AdminComp) user;

            System.out.println(user);
            return reservationRepo.findByStatutAndCompagnie(valable, expirer, adminComp.getCompagnie()).stream().count() ;
        }else {
            //if( Objects.equals(user.getRole().getRole(), "AdminA")){
            //AdminAgence adminAgence = (AdminAgence) user;
            //return reservationRepo.findByStatut(valable, expirer, adminAgence.getAgence()).stream().count();
        //}else{
            return reservationRepo.findByStatut(valable,expirer).stream().count();
        }
    }




    //@Override
    public int createR(Reservation entity) {
        Optional<Voyage> voyage = voyageRepo.findById(entity.getVoyage().getId());
        Voyage voyage1 = null;
        if (voyage.isPresent()) {
            entity.setCompagnie(voyage.get().getCompagnie());
            voyage1 = voyage.get();
        } else {
            // Gérer le cas où le voyage n'existe pas
            return 404;  // ou une autre valeur d'erreur appropriée
        }
        Client client = (Client) contexHolder.utilisateur();
        //Statut active = Statut.Active;
        ReservationStatut active = StatutRrepo.findByStatut("Valable");
        Reservation reservation = reservationRepo.findByClientAndVoyageAndDateAndStatut(client, voyage1, entity.getDate(), active);
        //List<Reservation> reservation = reservationRepo.findByVoyage(entity.getVoyage());
        //int result = 0;

        if (reservation != null) {
            //result = 1;
            System.out.print("\"Vous avez déja reserver pour ce voyage\"" + reservation);
            return 1;
        } else {
            //result = 200;


            ReservationStatut statut = StatutRrepo.findByStatut("Valable");
            entity.setStatut(statut);

            int code = this.generateRandomNumber();
            entity.setCode(code);
            entity.setClient(client);
            //return super.create(entity);
            reservationRepo.save(entity);
            System.out.print("\"Reservation effectuer avec succès\"");
            return 200;
        }


    }


    public int AgenceRervt(Reservation entity) {
        Optional<Voyage> voyage = voyageRepo.findById(entity.getVoyage().getId());
        Voyage voyage1 = null;
        if (voyage.isPresent()) {
            entity.setCompagnie(voyage.get().getCompagnie());
            voyage1 = voyage.get();
        } else {
            return 404;  // ou une autre valeur d'erreur appropriée
        }
        Role role = rolerepo.findByRole("Client");
        entity.getClient().setUsername("Fuctif");
        entity.getClient().setPassword("Fuctif");
        entity.getClient().setRole(role);
        //client.setUsername("Fuctif");
        //client.setPassword("Fuctif");



        ReservationStatut statut = StatutRrepo.findByStatut("Valable");
        entity.setStatut(statut);

        int code = this.generateRandomNumber();
        entity.setCode(code);
        entity.setNumeropayement(00000000);
        //return super.create(entity);
        clientRepo.save(entity.getClient());
        reservationRepo.save(entity);
        System.out.print("\"Reservation effectuer avec succès\"");
        return 200;

    }







    /*public Reservation create(Reservation entity, Voyage v) {
        //Optional<Voyage> voyage = voyageRepo.findById(entity.getVoyage().getId());
        //if(voyage.isPresent()){
        ////    entity.setCompagnie(voyage.get().getCompagnie());
        //}
        int code = this.generateRandomNumber();
        entity.setCode(code);
        entity.setClient((Client) contexHolder.utilisateur());
        return super.create(entity);
    }*/

    @Override
    public Reservation update(Long aLong, Reservation entity, String... ignoreProperties) {
        return super.update(aLong, entity, ignoreProperties);
    }

    public List<Reservation> GetR_byVoyage(int voyageId) {
        LocalDate today = LocalDate.now();
        Date sqlDate = Date.valueOf(today);
        return reservationRepo.findByVoyageAndDate(voyageId, sqlDate);

    }




    public int AnnulerReservation(Long NumeroP, long reservationId){
        Reservation reservation =  reservationRepo.findById(reservationId).get();
        if (NumeroP == reservation.getNumeropayement()){
            ReservationStatut Annuler = StatutRrepo.findByStatut("Annuler");
            reservation.setStatut(Annuler);
            reservationRepo.save(reservation);
            //System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            return 1;
        }else{
            //System.out.print("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
            return 2;
        }


    }

    // Méthode qui sera exécutée automatiquement tous les jours à minuit
    @Scheduled(cron = "0 26 12 * * ?")
    public void ExpirerReservation(){
        ReservationStatut Expirer = StatutRrepo.findByStatut("EXPIRER");

        LocalDate today = LocalDate.now();
        List<Reservation> reservation = reservationRepo.findAll();

        for (Reservation reserve : reservation){
            LocalDate reservationDate = reserve.getDate().toLocalDate();
            if(reservationDate.isBefore(today)){
                reserve.setStatut(Expirer);
                reservationRepo.save(reserve);
                //System.out.print("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
            }
        }
    }


}

package com.kalanso.event;



import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication //(exclude = { SecurityAutoConfiguration.class })
public class EventApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}




	@Bean
	public CommandLineRunner commandLineRunne1(Rolerepo rolerepo, StatutRsvtRepo StatutRrepo) {
		return args -> {

			//Role role1 = rolerepo.findByRole("ADMIN");
			Role role2 = rolerepo.findByRole("AdminC");
			Role role3 = rolerepo.findByRole("AdminA");
			Role role5 = rolerepo.findByRole("Client");

			//role1 == null ||

			if (role2 == null || role3 == null || role5 == null) {
				/*Role R_admin = new Role();
				R_admin.setRole("ADMIN");
				rolerepo.save(R_admin);*/

				Role R_gestionnaire = new Role();
				R_gestionnaire.setRole("AdminC");
				rolerepo.save(R_gestionnaire);

				Role R_agence = new Role();
				R_agence.setRole("AdminA");
				rolerepo.save(R_agence);

				//Role R_S_agence = new Role();
				//R_S_agence.setRole("SOUS-AGENCE");
				//rolerepo.save(R_S_agence);

				Role R_client = new Role();
				R_client.setRole("Client");
				rolerepo.save(R_client);


				ReservationStatut RS1 = StatutRrepo.findByStatut("Valable");
				ReservationStatut RS2 = StatutRrepo.findByStatut("Expirer");
				ReservationStatut RS3 = StatutRrepo.findByStatut("Annuler");

				if (RS1 == null || RS2 == null || RS3 == null) {
					ReservationStatut statut1 = new ReservationStatut();
					statut1.setStatut("Valable");
					StatutRrepo.save(statut1);

					ReservationStatut statut2 = new ReservationStatut();
					statut2.setStatut("Expirer");
					StatutRrepo.save(statut2);

					ReservationStatut statut3 = new ReservationStatut();
					statut3.setStatut("Annuler");
					StatutRrepo.save(statut3);


				}
			}




		};


	}




	@Bean
	public CommandLineRunner commandLineRunneJour(JourRepo jourRepo) {
		return args -> {

			jour jour1 = jourRepo.findByjour("Lundi");
			jour jour2 = jourRepo.findByjour("Mardi");
			jour jour3 = jourRepo.findByjour("Mercredi");
			jour jour4 = jourRepo.findByjour("Jeudi");
			jour jour5 = jourRepo.findByjour("Vendredi");
			jour jour6 = jourRepo.findByjour("Samedi");
			jour jour7 = jourRepo.findByjour("Dimanche");


			if (jour1 == null || jour2 == null || jour3 == null ||
					jour4 == null || jour5 == null || jour6 == null
					|| jour6 == null) {

				jour day1 = new jour();
				day1.setJour("Lundi");
				jourRepo.save(day1);

				jour day2 = new jour();
				day2.setJour("Mardi");
				jourRepo.save(day2);

				jour day3 = new jour();
				day3.setJour("Mercredi");
				jourRepo.save(day3);

				jour day4 = new jour();
				day4.setJour("Jeudi");
				jourRepo.save(day4);

				jour day5 = new jour();
				day5.setJour("Vendredi");
				jourRepo.save(day5);

				jour day6 = new jour();
				day6.setJour("Samedi");
				jourRepo.save(day6);

				jour day7 = new jour();
				day7.setJour("Dimanche");
				jourRepo.save(day7);

			}
		};

	}




	@Bean
	public CommandLineRunner commandLineRunneVille(VilleRepo villeRepo) {
		return args -> {

			Ville ville0 = villeRepo.findByVille("Bamako");
			Ville ville1 = villeRepo.findByVille("Kayes");
			Ville ville2 = villeRepo.findByVille("Koulikoro");
			Ville ville3 = villeRepo.findByVille("Sikasso");
			Ville ville4 = villeRepo.findByVille("Ségou");
			Ville ville5 = villeRepo.findByVille("Mopti");
			Ville ville6 = villeRepo.findByVille("Tombouctou");
			Ville ville7 = villeRepo.findByVille("Gao");
			Ville ville8 = villeRepo.findByVille("Kidal");
			Ville ville9 = villeRepo.findByVille("Fana");
			Ville ville10 = villeRepo.findByVille("Bougouni");
			Ville ville11 = villeRepo.findByVille("San");
			Ville ville12 = villeRepo.findByVille("Douentza");
			Ville ville13 = villeRepo.findByVille("Bandiagara");
			Ville ville14 = villeRepo.findByVille("Kita");
			Ville ville15 = villeRepo.findByVille("Nara");
			Ville ville16 = villeRepo.findByVille("Koutiala");
			Ville ville17 = villeRepo.findByVille("Dioïla");


			if (
					ville0 == null || ville1 == null || ville2 == null ||
					ville3 == null || ville4 == null || ville5 == null ||
					ville6 == null || ville7 == null || ville8 == null ||
					ville9 == null || ville10 == null || ville11 == null ||
					ville12 == null || ville13 == null || ville14 == null ||
					ville15 == null || ville16 == null || ville17 == null
					) {

				Ville V1 = new Ville();
				V1.setVille("Bamako");
				villeRepo.save(V1);

				Ville V2 = new Ville();
				V2.setVille("Kayes");
				villeRepo.save(V2);

				Ville V3 = new Ville();
				V3.setVille("Koulikoro");
				villeRepo.save(V3);

				Ville V4 = new Ville();
				V4.setVille("Sikasso");
				villeRepo.save(V4);

				Ville V5 = new Ville();
				V5.setVille("Ségou");
				villeRepo.save(V5);

				Ville V6 = new Ville();
				V6.setVille("Mopti");
				villeRepo.save(V6);

				Ville V7 = new Ville();
				V7.setVille("Tombouctou");
				villeRepo.save(V7);

				Ville V8 = new Ville();
				V8.setVille("Gao");
				villeRepo.save(V8);

				Ville V9 = new Ville();
				V9.setVille("Kidal");
				villeRepo.save(V9);

				Ville V10 = new Ville();
				V10.setVille("Fana");
				villeRepo.save(V10);

				Ville V11 = new Ville();
				V11.setVille("Bougouni");
				villeRepo.save(V11);

				Ville V12 = new Ville();
				V12.setVille("San");
				villeRepo.save(V12);

				Ville V13 = new Ville();
				V13.setVille("Douentza");
				villeRepo.save(V13);

				Ville V14 = new Ville();
				V14.setVille("Bandiagara");
				villeRepo.save(V14);

				Ville V15 = new Ville();
				V15.setVille("Kita");
				villeRepo.save(V15);

				Ville V16 = new Ville();
				V16.setVille("Nara");
				villeRepo.save(V16);

				Ville V17 = new Ville();
				V17.setVille("Koutiala");
				villeRepo.save(V17);

				Ville V18 = new Ville();
				V18.setVille("Dioïla");
				villeRepo.save(V18);



			}
		};

	}





	@Bean
	public CommandLineRunner commandLineRunnerAdmin(Rolerepo rolerepo , PasswordEncoder passwordEncoder, Utilisateur_repo utilisateurRepo ) {
		return args -> {Role roleUsers = rolerepo.findByRole("Admin");
			if (roleUsers == null) {
				Role roleUser = new Role();
				roleUser.setRole("Admin");
				System.out.println("hello3");
				rolerepo.save(roleUser);
			}

			Optional<Utilisateur> utilisateur = utilisateurRepo.findByusername("admin@gmail.com");
			System.out.println("hello1");
			if(utilisateur.isEmpty()){

				Admin admin = new Admin();
				Role role = rolerepo.findByRole("ADMIN");
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+role);
				admin.setUsername("admin@gmail.com");
				admin.setNom("Admin");
				admin.setPrenom("Admin");
				admin.setPassword(passwordEncoder.encode("12345678"));
				admin.setTelephone("+22398101237");
				admin.setRole(role);
				utilisateurRepo.save(admin);
			}
		};
	}

}






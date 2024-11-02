package com.kalanso.event.Controller;

import com.kalanso.event.Model.*;
import com.kalanso.event.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins="*")
//@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminA_Service adminAService;
    @Autowired
    private AdminC_Service adminCService;
    @Autowired
    private ClientService clientService;


    @CrossOrigin(origins="http://localhost:4200/")
    @GetMapping("/currentuser/get")
    public Utilisateur getCurrentUser() {
        return adminService.getCurrentUser();
    }

    //ADMIN

    @PostMapping("/admin/ajout")
    public Admin AjoutAdmin(@RequestBody Admin V) {
        return adminService.create(V) ;
    }

    @GetMapping("/admin/get")
    public Iterable<Admin> getAdmin(Voyage V) {
        return adminService.getAll() ;
    }



    @PutMapping("/admin/modif/{id}")
    public Admin ModifierAdmin(@PathVariable Long id, @RequestBody Admin V) {
        return adminService.update(id,V) ;
    }

    @DeleteMapping("/admin/sup/{id}")
    public void SupprimerAdmin(@PathVariable Long id) {
        adminService.delete(id);
    }


    @CrossOrigin(origins="http://localhost:4200/")
    @GetMapping("/admin/client/get")
    public Iterable<Client> getClient() {
        return clientService.getClient() ;
    }






    //ADMIN_COMPAGNIE

    @PostMapping("/admin_c/ajout")
    public AdminComp AjoutAdminC(@RequestBody AdminComp V) {
        return adminCService.create(V) ;
    }
    @GetMapping("/admin_c/get")
    public Iterable<AdminComp> getAdminC() {
        return adminCService.getAll() ;
    }

    @PutMapping("/admin_c/modif/{id}")
    public AdminComp ModifierAdminC(@PathVariable Long id,@RequestBody AdminComp V) {
        return adminCService.update(id,V) ;
    }

    @DeleteMapping("/admin_c/sup/{id}")
    public void SupprimerAdminC(@PathVariable Long id) {
        adminCService.delete(id);
    }

    @PatchMapping("/admin/bloquer/{id}")
    //@PreAuthorize("hasRole('Admin')")
    public String Bloquer(@PathVariable Long id){
        return adminService.Bloquer(id);
    }

    /*public ResponseEntity<String> bloquerAdminCompagnie(@RequestBody AdminComp adminComp) {
        // Récupérer l'AdminCompagnie par ID
        //AdminComp adminComp = adminCService.findById(id);
        if (adminComp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AdminCompagnie non trouvé");
        }

        // Appeler la méthode pour bloquer l'AdminCompagnie et les entités associées
        bloqué(adminComp);

        // Retourner une réponse
        return ResponseEntity.ok("AdminCompagnie et toutes les entités associées ont été bloqués");
    }*/




    //ADMIN_AGENCE

    @PostMapping("/admin_a/ajout")
    public AdminAgence AjoutAdminA(@RequestBody AdminAgence V) {
        return adminAService.create(V) ;
    }
    @GetMapping("/admin_a/get")
    public Iterable<AdminAgence> getAdminA() {
        return adminAService.GetAdminA() ;
    }

    @PutMapping("/admin_a/modif/{id}")
    public AdminAgence ModifierAdminA(@PathVariable Long id, @RequestBody AdminAgence V) {
        return adminAService.update(id,V) ;
    }

    @DeleteMapping("/admin_a/sup/{id}")
    public void SupprimerAdminA(@PathVariable Long id) {
        adminAService.delete(id);
    }

    @PatchMapping("/admin_a/bloquer/{id}")
    //@PreAuthorize("hasRole('Admin')")
    public String BloquerA(@PathVariable Long id){
        return adminCService.Bloquer(id);
    }

}

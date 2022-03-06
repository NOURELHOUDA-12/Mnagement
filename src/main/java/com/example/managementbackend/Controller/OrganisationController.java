package com.example.managementbackend.Controller;

import com.example.managementbackend.Repository.OrganisationRepository;
import com.example.managementbackend.exception.ResourceNotFoundException;
import com.example.managementbackend.model.Organisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@RestController
public class OrganisationController {
    @Autowired
    private OrganisationRepository organisationRepo;

    @GetMapping("/organisations")
    public List<Organisation> getAll() {
        return organisationRepo.findAll();
    }

    @GetMapping("/oneorganisations/{organId}")
    public Optional<Organisation> getOrganById(@PathVariable Long organId) {
        return organisationRepo.findById(organId).map(organisation -> organisationRepo.findById(organId)).orElseThrow(() -> new ResourceNotFoundException("organId " + organId + " not found"));
    }

    @PostMapping("/organisations")
    public Organisation createOrganisation(@Valid @RequestBody Organisation organisation) {
        return organisationRepo.save(organisation);
    }

    @PutMapping("/organisations/{organId}")
    public Organisation updateOrganisation(@PathVariable Long organId, @Valid @RequestBody Organisation organisationRequest) {
        return organisationRepo.findById(organId).map(organisation -> {
            organisation.setNom(organisationRequest.getNom());
            organisation.setCode(organisationRequest.getCode());
            organisation.setSecteur_d_activite(organisationRequest.getSecteur_d_activite());
            organisation.setEmail(organisationRequest.getEmail());
            organisation.setPays(organisationRequest.getPays());
            organisation.setRegion(organisationRequest.getRegion());
            organisation.setAdresse(organisationRequest.getAdresse());
            organisation.setTel(organisationRequest.getTel());
            organisation.setType(organisationRequest.getType());
            organisation.setNomDG(organisationRequest.getNomDG());
            organisation.setTelDG(organisationRequest.getTelDG());
            organisation.setEmailDG(organisationRequest.getEmailDG());
            organisation.setNomAdmin(organisationRequest.getNomAdmin());
            organisation.setTelAdmin(organisationRequest.getTelAdmin());
            organisation.setEmailAdmin(organisationRequest.getEmailAdmin());



            return organisationRepo.save(organisation);
        }).orElseThrow(() -> new ResourceNotFoundException("organId " + organId + " not found"));
    }


    @DeleteMapping("/organisations/{organId}")
    public ResponseEntity<?> deleteOrganisation(@PathVariable Long organId) {
        return organisationRepo.findById(organId).map(organisation -> {
            organisationRepo.delete(organisation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("organId " + organId + " not found"));
    }


    //methodes pour l'entreprise



/*    @GetMapping("/organisations/{organId}/entreprises")
    public List<Organisation> getAllEntreprisesByOrganId(@PathVariable(value = "organId") Long organId) {
        return organisationRepo.findBySuporganId(organId);
    }

   /* @GetMapping("/oneentreprise/{entrepId}")
    public Optional<Entreprise> getEntrepById(@PathVariable Long entrepId) {
        return organisationRepo.findById(entrepId).map(entreprise -> entrepriseRepo.findById(entrepId)).orElseThrow(() -> new ResourceNotFoundException("entrepId " + entrepId + " not found"));
    }

    @PostMapping("/organisations/{organId}/entreprises")
    public Organisation createEntreprise(@PathVariable (value = "organId") Long organId,
                                         @Valid @RequestBody Organisation entreprise) {
        return organisationRepo.findById(organId).map(organisation -> {
            entreprise.setSuporgan(organisation);
            return organisationRepo.save(entreprise);
        }).orElseThrow(() -> new ResourceNotFoundException("organId " + organId + " not found"));
    }

   /* @PutMapping("/organisations/{organId}/entreprises/{entrepId}")
    public Entreprise updateEntreprise(@PathVariable (value = "organId") Long organId,
                                 @PathVariable (value = "entrepId") Long entrepId,
                                 @Valid @RequestBody Entreprise entrepriseRequest) {
        if(!organisationRepo.existsById(organId)) {
            throw new ResourceNotFoundException("organId " + organId + " not found");
        }

        return entrepriseRepo.findById(organId).map(entreprise -> {
            entreprise.setNom(entrepriseRequest.getNom());
            entreprise.setCode(entrepriseRequest.getCode());
            entreprise.setSecteur_d_activité(entrepriseRequest.getSecteur_d_activité());
            entreprise.setEmail(entrepriseRequest.getEmail());
            entreprise.setPays(entrepriseRequest.getPays());
            entreprise.setRegion(entrepriseRequest.getRegion());
            entreprise.setAdresse(entrepriseRequest.getAdresse());
            entreprise.setTel(entrepriseRequest.getTel());
            entreprise.setType(entrepriseRequest.getType());
            entreprise.setNomDG(entrepriseRequest.getNomDG());
            entreprise.setTelDG(entrepriseRequest.getTelDG());
            entreprise.setEmailDG(entrepriseRequest.getEmailDG());
            entreprise.setNomAdmin(entrepriseRequest.getNomAdmin());
            entreprise.setTelAdmin(entrepriseRequest.getTelAdmin());
            entreprise.setEmailAdmin(entrepriseRequest.getEmailAdmin());

            return entrepriseRepo.save(entreprise);
        }).orElseThrow(() -> new ResourceNotFoundException("entrepriseId " + entrepId+ "not found"));
    }

    @DeleteMapping("/organisations/{organId}/entreprises/{entrepId}")
    public ResponseEntity<?> deleteEntreprise(@PathVariable (value = "organId") Long organId,
                                              @PathVariable (value = "entrepId") Long entrepId) {
        return organisationRepo.findByIdAndSuporganId(entrepId, organId).map(entreprise -> {
            organisationRepo.delete(entreprise);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("entreprise not found with id " + entrepId+ " and organisationId " + organId));
    }
    */
}

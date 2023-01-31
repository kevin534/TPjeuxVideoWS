package com.ipi.tpexamspring.controllers;

import com.ipi.tpexamspring.models.Avis;
import com.ipi.tpexamspring.models.Jeu;
import com.ipi.tpexamspring.repositories.AvisRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/avis")
public class AvisController {

    private final AvisRepository avisRepository;

    public AvisController(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    //TODO: Ajouter un Avis
    @PostMapping("/addAvis/{id}")
    public ResponseEntity<Avis> saveAvis(@PathVariable(name = "id") Jeu jeu, @Valid @RequestBody Avis avis, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        else{
            avis.setJeu(jeu);
            avisRepository.save(avis);
            return  new ResponseEntity<>(avis,HttpStatus.CREATED);
        }
    }

    //TODO: Supprimer un Avis par son id
    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable(name = "id",required = false) Avis avis)
    {
        if(avis == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Avis Introuvable");
        }
        else{
            avisRepository.delete(avis);
        }
    }

    //TODO: Récupérer tous les Avis d'un jeux by jeuId
    @GetMapping("/jeux/{id}")
    List<Avis> getAll(@PathVariable(name = "id", required = false) Jeu jeu){
        if(jeu == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Serie Introuvable");
        }
        else {
            return avisRepository.findByJeuId(jeu.getId());
        }
    }

    //TODO: réchercher tous les Avis
    @GetMapping("/getAll")
    public List<Avis> findAll(){
        return avisRepository.findAll();
    }

}

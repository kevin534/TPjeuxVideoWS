package com.ipi.tpexamspring.controllers;

import com.ipi.tpexamspring.models.Jeu;
import com.ipi.tpexamspring.repositories.JeuRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/jeux")
public class JeuController {

    private final JeuRepository jeuRepository;

    public JeuController(JeuRepository jeuRepository) {
        this.jeuRepository = jeuRepository;
    }

    //TODO: Ajouter un Jeux
    @PostMapping("/addJeux")
    public ResponseEntity<Jeu> saveJeux(@Valid @RequestBody Jeu jeu, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        else{
            jeuRepository.save(jeu);
            return  new ResponseEntity<>(jeu,HttpStatus.CREATED);
        }
    }

    //TODO: modifier un Jeux par son id
    @PutMapping(value = "/{id}")
    public ResponseEntity<Jeu> updateJeuxById(@PathVariable(name = "id" ,required = false) Jeu jeu, @Valid  @RequestBody Jeu jeuUpdate, BindingResult bindingResult){
        if(jeu == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Jeux Introuvable");
        }
        else{
            if(bindingResult.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
            }
            else{
                jeuUpdate.setId(jeu.getId());
                jeuRepository.save(jeuUpdate);
                return  new ResponseEntity<>(jeuUpdate,HttpStatus.OK);
            }
        }
    }

    //TODO: supprimer un Jeux par son id
    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable(name = "id",required = false) Jeu jeu)
    {
        if(jeu == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Jeux Introuvable");
        }
        else{
            jeuRepository.delete(jeu);
        }
    }

    //TODO: Récupérer un Jeux par son id
    @GetMapping("/{id}")
    Jeu getOne(@PathVariable(name = "id", required = false) Jeu jeu){
        if(jeu == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Jeux Introuvable");
        }
        else {
            return jeu;
        }
    }

    //TODO: réchercher tous les Jeux
    @GetMapping("/getAll")
    public List<Jeu> findAll(){
        return jeuRepository.findAll();
    }
}

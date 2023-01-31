package com.ipi.tpexamspring.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "jeu")
public class Jeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    @NotNull(message = "Le champ nom ne peut pas etre null")
    @NotBlank(message =  "Le champ nom ne peut pas etre vide")
    private String nom;

    @Column(name = "description")
    @NotNull(message = "Le champ description ne peut pas etre null")
    @NotBlank(message =  "Le champ description ne peut pas etre vide")
    private String description;

    @NotNull(message = "Le champ date ne peut pas etre null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateSortie;

    @OneToMany(mappedBy="jeu", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Avis> avis;

    public Jeu() {
    }

    public Jeu(String nom, String description, Date dateSortie) {
        this.nom = nom;
        this.description = description;
        this.dateSortie = dateSortie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }

    @Override
    public String toString() {
        return "Jeu{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateSortie=" + dateSortie +
                '}';
    }
}

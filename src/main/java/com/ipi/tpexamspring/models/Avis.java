package com.ipi.tpexamspring.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "avis")
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ date ne peut pas etre null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateEnvoie;

    @Column(name = "note")
    @NotNull(message = "Le champ note ne peut pas etre null")
    private int note;

    @Column(name = "description")
    @NotNull(message = "Le champ description ne peut pas etre null")
    @NotBlank(message =  "Le champ description ne peut pas etre vide")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Jeu jeu;

    public Avis() {
    }

    public Avis(Date dateEnvoie, int note, String description) {
        this.dateEnvoie = dateEnvoie;
        this.note = note;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateEnvoie() {
        return dateEnvoie;
    }

    public void setDateEnvoie(Date dateEnvoie) {
        this.dateEnvoie = dateEnvoie;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "id=" + id +
                ", dateEnvoie=" + dateEnvoie +
                ", note=" + note +
                ", description='" + description + '\'' +
                '}';
    }
}

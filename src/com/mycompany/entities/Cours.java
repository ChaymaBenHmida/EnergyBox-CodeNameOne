/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author azizo
 */
public class Cours {
    private int id,ageMinCours;
    private double prixCours;
    private String nomCours,nomCoach,descriptionCours;

    public Cours() {
    }

    public Cours(int id, String nomCours, String nomCoach, int ageMinCours, double prixCours, String descriptionCours) {
        this.id = id;
        this.nomCours = nomCours;
        this.nomCoach = nomCoach;
        this.ageMinCours = ageMinCours;
        this.prixCours = prixCours;
        this.descriptionCours = descriptionCours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgeMinCours() {
        return ageMinCours;
    }

    public void setAgeMinCours(int ageMinCours) {
        this.ageMinCours = ageMinCours;
    }

    public double getPrixCours() {
        return prixCours;
    }

    public void setPrixCours(double prixCours) {
        this.prixCours = prixCours;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public String getNomCoach() {
        return nomCoach;
    }

    public void setNomCoach(String nomCoach) {
        this.nomCoach = nomCoach;
    }

    public String getDescriptionCours() {
        return descriptionCours;
    }

    public void setDescriptionCours(String descriptionCours) {
        this.descriptionCours = descriptionCours;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Dell
 */

public class Plat {
    private int id,nbp;
    private String nom,description,calories,etat,user_id,image;
    private float prix;

    public Plat() {
    }

    public Plat(int id, int nbp, String nom, String description, String calories, String etat, String user_id, String image, float prix) {
        this.id = id;
        this.nbp = nbp;
        this.nom = nom;
        this.description = description;
        this.calories = calories;
        this.etat = etat;
        this.user_id = user_id;
        this.image = image;
        this.prix = prix;
    }

    public Plat(int nbp, String nom, String description, String calories, String etat, String user_id, String image, float prix) {
        this.nbp = nbp;
        this.nom = nom;
        this.description = description;
        this.calories = calories;
        this.etat = etat;
        this.user_id = user_id;
        this.image = image;
        this.prix = prix;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbp() {
        return nbp;
    }

    public void setNbp(int nbp) {
        this.nbp = nbp;
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

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Plat{" + "id=" + id + ", nbp=" + nbp + ", nom=" + nom + ", description=" + description + ", calories=" + calories + ", etat=" + etat + ", user_id=" + user_id + ", image=" + image + ", prix=" + prix + '}';
    }
}

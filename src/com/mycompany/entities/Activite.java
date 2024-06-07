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
public class Activite {
    private int id;
    private String nomActivite,dureeActivite,tenueActivite;
    private String difficulteActivite,imageActivite,descriptionActivite;

    public Activite() {
    }

    public Activite(int id, String nomActivite, String dureeActivite, String tenueActivite, String difficulteActivite, String imageActivite, String descriptionActivite) {
        this.id = id;
        this.nomActivite = nomActivite;
        this.dureeActivite = dureeActivite;
        this.tenueActivite = tenueActivite;
        this.difficulteActivite = difficulteActivite;
        this.imageActivite = imageActivite;
        this.descriptionActivite = descriptionActivite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public String getDureeActivite() {
        return dureeActivite;
    }

    public void setDureeActivite(String dureeActivite) {
        this.dureeActivite = dureeActivite;
    }

    public String getTenueActivite() {
        return tenueActivite;
    }

    public void setTenueActivite(String tenueActivite) {
        this.tenueActivite = tenueActivite;
    }

    public String getDifficulteActivite() {
        return difficulteActivite;
    }

    public void setDifficulteActivite(String difficulteActivite) {
        this.difficulteActivite = difficulteActivite;
    }

    public String getImageActivite() {
        return imageActivite;
    }

    public void setImageActivite(String imageActivite) {
        this.imageActivite = imageActivite;
    }

    public String getDescriptionActivite() {
        return descriptionActivite;
    }

    public void setDescriptionActivite(String descriptionActivite) {
        this.descriptionActivite = descriptionActivite;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + ", nomActivite=" + nomActivite + 
                ", dureeActivite=" + dureeActivite + ", tenueActivite=" + tenueActivite + 
                ", difficulteActivite=" + difficulteActivite + ", imageActivite=" + imageActivite + 
                ", descriptionActivite=" + descriptionActivite + '}';
    }

    
    
    

    
}

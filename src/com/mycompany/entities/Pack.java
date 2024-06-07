/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author wiem
 */
public class Pack {
    private int id;
    private String typePack ;
    private double montantPack;
    private int dureePack ;
    private String descriptionPack;
    private int placesPack;
    private int disponibilitePack;

    public Pack() {
    }

    public Pack(int id, String typePack, double montantPack, int dureePack, String descriptionPack, int placesPack, int disponibilitePack) {
        this.id = id;
        this.typePack = typePack;
        this.montantPack = montantPack;
        this.dureePack = dureePack;
        this.descriptionPack = descriptionPack;
        this.placesPack = placesPack;
        this.disponibilitePack = disponibilitePack;
    }

    public int getId() {
        return id;
    }

    public String getTypePack() {
        return typePack;
    }

    public double getMontantPack() {
        return montantPack;
    }

    public int getDureePack() {
        return dureePack;
    }

    public String getDescriptionPack() {
        return descriptionPack;
    }

    public int getPlacesPack() {
        return placesPack;
    }

    public int getDisponibilitePack() {
        return disponibilitePack;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypePack(String typePack) {
        this.typePack = typePack;
    }

    public void setMontantPack(double montantPack) {
        this.montantPack = montantPack;
    }

    public void setDureePack(int dureePack) {
        this.dureePack = dureePack;
    }

    public void setDescriptionPack(String descriptionPack) {
        this.descriptionPack = descriptionPack;
    }

    public void setPlacesPack(int placesPack) {
        this.placesPack = placesPack;
    }

    public void setDisponibilitePack(int disponibilitePack) {
        this.disponibilitePack = disponibilitePack;
    }

    
    
}

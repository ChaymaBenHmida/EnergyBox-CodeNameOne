/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;


/**
 *
 * @author wiem
 */
public class Abonnement {
     private int id;
     private Date dateAchat;
     private Date dateFin;
     private String etatAbonnement;
     private String codePromo;
     private double montantAbonnement ;
     private int pack;

    public Abonnement() {
    }

    public Abonnement(int id, Date dateAchat, Date dateFin, int pack, String etatAbonnement, String codePromo, double montantAbonnement) {
        this.id = id;
        this.dateAchat = dateAchat;
        this.dateFin = dateFin;
        this.pack = pack;
        this.etatAbonnement = etatAbonnement;
        this.codePromo = codePromo;
        this.montantAbonnement = montantAbonnement;
    }

    public Abonnement(int id, int packId, String etatAbonnement, String codePromo, double montantAbonnement) {
        this.id = id;
         this.pack = pack;
        this.etatAbonnement = etatAbonnement;
        this.codePromo = codePromo;
        this.montantAbonnement = montantAbonnement;
    }

    public void setPack(int pack) {
        this.pack = pack;
    }

    public int getPack() {
        return pack;
    }

    public int getId() {
        return id;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getEtatAbonnement() {
        return etatAbonnement;
    }

    public String getCodePromo() {
        return codePromo;
    }

    public double getMontantAbonnement() {
        return montantAbonnement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setEtatAbonnement(String etatAbonnement) {
        this.etatAbonnement = etatAbonnement;
    }

    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }

    public void setMontantAbonnement(double montantAbonnement) {
        this.montantAbonnement = montantAbonnement;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", dateAchat=" + dateAchat + ", date fin=" + dateFin + ", etatAbonnement=" + etatAbonnement + ", codePromo=" + codePromo + ", montantAbonnement=" + montantAbonnement + '}';
    }

    public int getNombreJours() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author Salima
 */
public class competition {
    private int idCompetition;
    private String nomCompetition;
    private int fraisCompetition;
    private Date dateCompetition;
    private int nbMaxInscrit;
    private String etatCompetition;
    private String nbrParticipant;


    public competition(String nomCompetition, int fraisCompetition, Date dateCompetition, int nbMaxInscrit, String etatCompetition) {
        this.nomCompetition = nomCompetition;
        this.fraisCompetition = fraisCompetition;
        this.dateCompetition = dateCompetition;
        this.nbMaxInscrit = nbMaxInscrit;
        this.etatCompetition = etatCompetition;
    }

    public competition() {
    }

    public int getIdCompetition() {
        return idCompetition;
    }

    public String getNomCompetition() {
        return nomCompetition;
    }

    public int getFraisCompetition() {
        return fraisCompetition;
    }

    public Date getDateCompetition() {
        return dateCompetition;
    }

    public int getNbMaxInscrit() {
        return nbMaxInscrit;
    }

    public String getEtatCompetition() {
        return etatCompetition;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    public void setNomCompetition(String nomCompetition) {
        this.nomCompetition = nomCompetition;
    }

    public void setFraisCompetition(int fraisCompetition) {
        this.fraisCompetition = fraisCompetition;
    }

    public void setDateCompetition(Date dateCompetition) {
        this.dateCompetition = dateCompetition;
    }

    public void setNbMaxInscrit(int nbMaxInscrit) {
        this.nbMaxInscrit = nbMaxInscrit;
    }

    public void setEtatCompetition(String etatCompetition) {
        this.etatCompetition = etatCompetition;
    }
    @Override
public String toString() {
    return "competition{" + "nomCompetition=" + nomCompetition + ", fraisCompetition=" + fraisCompetition + ", dateCompetition=" + dateCompetition + ", nbMaxInscrit=" + nbMaxInscrit + ", etatCompetition=" + etatCompetition + ", nbrParticipant=" + nbrParticipant + '}';
}

public void setNbrParticipant(String nbrParticipant) {
    this.nbrParticipant = nbrParticipant;
}

public String getNbrParticipant() {
    return nbrParticipant;
}
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author azizo
 */
public class Planning {
    private int id;
    private int cours_id;
    private Date datePlanning;
    private String jourPlanning;
    private int heurePlanning;
    

    public Planning() {
    }

    public Planning(int id, Date datePlanning, String jourPlanning, int heurePlanning) {
        this.id = id;
        this.datePlanning = datePlanning;
        this.jourPlanning = jourPlanning;
        this.heurePlanning = heurePlanning;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public int getCours_id() {
        return cours_id;
    }

    public void setCours_id(int cours_id) {
        this.cours_id = cours_id;
    }*/
    
    

    public Date getDatePlanning() {
        return datePlanning;
    }

    public void setDatePlanning(Date datePlanning) {
        this.datePlanning = datePlanning;
    }

    public String getJourPlanning() {
        return jourPlanning;
    }

    public void setJourPlanning(String jourPlanning) {
        this.jourPlanning = jourPlanning;
    }

    public int getHeurePlanning() {
        return heurePlanning;
    }

    public void setHeurePlanning(int heurePlanning) {
        this.heurePlanning = heurePlanning;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Salima
 */
public class Ticket {
    private int id;
    private String description;
    private int idCompetition;

    public Ticket() {
    }

    public Ticket(String description) {
        this.description = description;
    }

    public Ticket(String description, int idCompetition) {
        this.description = description;
        this.idCompetition = idCompetition;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getIdCompetition() {
        return idCompetition;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", description=" + description + ", idCompetition=" + idCompetition + '}';
    }
    
    
    
}

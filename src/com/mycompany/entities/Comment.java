/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Eya Krichene
 */
public class Comment {
    private int id;
    private String description;
    private String dateComment;
   

    public Comment() {
    }

    public Comment(int id ,String description, String dateComment, int idPost) {
        this.id = id;
        this.description = description;
        this.dateComment = dateComment;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateComment() {
        return dateComment;
    }

    public void setDateComment(String dateComment) {
        this.dateComment = dateComment;
    }

  

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", description=" + description + ", dateComment=" + dateComment + '}';
    }
    
}

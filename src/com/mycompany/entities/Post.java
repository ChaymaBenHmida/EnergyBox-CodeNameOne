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
public class Post {
  private int id ;
  private String title,details,image;
  private String datePost;


    public Post(int id, String title, String details, String image, String datePost) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.image = image;
        this.datePost = datePost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", details=" + details + ", image=" + image + ", datePost=" + datePost + '}';
    }

    public Post() {
    }
  
  
    
}

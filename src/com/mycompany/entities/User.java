/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.io.Serializable;

/**
 *
 * @author maham
 */
public class User {

    //private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    private String username;
    private int  num_tel;
    private String password;
    private String image;
    private String role;
  // private String dateN;

    public User(int id, String email, String username, int num_tel, String password, String photo, String role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.num_tel = num_tel;
        this.password = password;
        this.image = photo;
        this.role = role;
       
    }

    public User(String username, int numTel, String email, String password, String photo) {
        this.email = email;
        this.username = username;
        this.num_tel = numTel;
        this.password = password;
        this.image = photo;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public User() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumTel() {
        return num_tel;
    }

    public void setNumTel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return image;
    }

    public void setPhoto(String photo) {
        this.image = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

   
    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", email=" + email + ", username=" + username + ", numTel=" + num_tel + ",password=" + password + ", photo=" + image + ", role=" + role + '}';
    }

}

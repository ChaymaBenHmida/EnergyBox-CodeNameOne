/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.Preferences;
import java.util.Date;

/**
 *
 * @author maham
 */
public class SessionManager {
      
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    public static int idU;
    public static int num_tel;
    public static String username,email, password, image;
    public static String role;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getIdUtilisateur() {
        return pref.get("id",idU);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setIdUtilisateur(int idutilisateur) {
        pref.set("id",idutilisateur);//nsajl id user connecté  w na3tiha identifiant "id";
    }


    public static String getNom() {
        return pref.get("nom",username);
    }

    public static void setNom(String nom) {
         pref.set("nom",username);
    }
    
     
    public static String getEmail() {
        return pref.get("mail",email);
    }

    public static void setEmail(String mail) {
         pref.set("mail",email);
    }

    public static String getPassowrd() {
        return pref.get("mot_de_passe",password);
    }

    public static void setPassword(String mot_de_passe) {
         pref.set("mot_de_passe",password);
    }

    public static String getImage() {
        return pref.get("image",image);
    }

    public static void setImage(String image) {
         pref.set("image",image);
    }
      
     public static String getRole() {
        return pref.get("role",role);
    }

    public static void setRole(String role) {
         pref.set("role",role);
    }
    
     public static int getNumTel () {
        return pref.get("num_tel",num_tel);
    }

    public static void setNumTel(int num_tel) {
        pref.set("num_tel",num_tel);
    }
    
     
}

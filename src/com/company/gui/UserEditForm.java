/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUtilisateur;

/**
 *
 * @author maham
 */
public class UserEditForm extends Form{
     public UserEditForm(User c){
        
        Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container CnBtn=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button btnModifier=new Button("Modifier");
        Button btnAnnuler = new Button("Annuler");
        
        TextField email = new TextField(c.getEmail(), "email" , 20 , TextField.ANY);
        TextField username = new TextField(c.getUsername(), "username" , 20 , TextField.ANY);
        
        
        
        cn.add(email);
        cn.add(username);
       
        CnBtn.add(btnModifier);
        CnBtn.add(btnAnnuler);
        
        btnModifier.addPointerPressedListener(l ->   { 
            c.setUsername(username.getText());
            c.setEmail(email.getText());
           
            if(ServiceUtilisateur.getInstance().ModifierUser(c)) { // if true
                new ListUsers().show();
            }
        });
        
       
       btnAnnuler.addActionListener(e -> {
           new ListUsers().show();
       });
       
       
       add(cn);
       add(CnBtn);
    }
}

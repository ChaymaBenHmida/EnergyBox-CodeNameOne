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
import com.mycompany.entities.Cours;
import com.mycompany.services.ServiceCours;

/**
 *
 * @author azizo
 */
public class CoursEditForm extends Form{
    
    public CoursEditForm(Cours c){
        
        Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container CnBtn=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button btnModifier=new Button("Modifier");
        Button btnAnnuler = new Button("Annuler");
        
        TextField nom = new TextField(c.getNomCours() , "Nom Cours" , 20 , TextField.ANY);
        TextField nomC = new TextField(c.getNomCoach() , "Nom Coach" , 20 , TextField.ANY);
        TextField prix = new TextField(String.valueOf(c.getPrixCours()) , "Prix Cours" , 20 , TextField.ANY);
        TextField age = new TextField(String.valueOf(c.getAgeMinCours()) , "Age Min" , 20 , TextField.ANY);
        TextField description = new TextField(String.valueOf(c.getDescriptionCours()) , "Description Cours" , 20 , TextField.ANY);
        
        
        cn.add(nom);
        cn.add(nomC);
        cn.add(prix);
        cn.add(age);
        cn.add(description);
        CnBtn.add(btnModifier);
        CnBtn.add(btnAnnuler);
        
        btnModifier.addPointerPressedListener(l ->   { 
            c.setNomCours(nom.getText());
            c.setNomCoach(nomC.getText());
            c.setPrixCours(Double.parseDouble(prix.getText()));
            c.setAgeMinCours(Integer.parseInt(age.getText()));
            c.setDescriptionCours(description.getText());
            if(ServiceCours.getInstance().ModifierCours(c)) { // if true
                new CoursViewForm().show();
            }
        });
        
       
       btnAnnuler.addActionListener(e -> {
           new CoursViewForm().show();
       });
       
       
       add(cn);
       add(CnBtn);
    }
}

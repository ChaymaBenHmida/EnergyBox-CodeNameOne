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
import com.mycompany.entities.Activite;
import com.mycompany.services.ServiceActivite;

/**
 *
 * @author azizo
 */
public class ActiviteEditForm extends Form{
    
    public ActiviteEditForm(Activite a){
        
        Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container CnBtn=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button btnModifier=new Button("Modifier");
        Button btnAnnuler = new Button("Annuler");
        
        TextField nom = new TextField(a.getNomActivite() , "Nom Activite" , 20 , TextField.ANY);
        TextField duree = new TextField(a.getDureeActivite() , "Duree Activite" , 20 , TextField.ANY);
        TextField tenue = new TextField(String.valueOf(a.getTenueActivite()) , "Tenue Activite" , 20 , TextField.ANY);
        TextField diff = new TextField(String.valueOf(a.getDifficulteActivite()) , "Difficulte Activite" , 20 , TextField.ANY);
        TextField image = new TextField(String.valueOf(a.getImageActivite()) , "Image Activite" , 20 , TextField.ANY);
        TextField description = new TextField(String.valueOf(a.getDescriptionActivite()) , "Description Activite" , 20 , TextField.ANY);
        
        
        cn.add(nom);
        cn.add(duree);
        cn.add(tenue);
        cn.add(diff);
        cn.add(image);
        cn.add(description);
        CnBtn.add(btnModifier);
        CnBtn.add(btnAnnuler);
        
        btnModifier.addPointerPressedListener(l ->   { 
            a.setNomActivite(nom.getText());
            a.setDureeActivite(duree.getText());
            a.setTenueActivite(tenue.getText());
            a.setDifficulteActivite(diff.getText());
            a.setImageActivite(image.getText());
            a.setDescriptionActivite(description.getText());
            if(ServiceActivite.getInstance().ModifierActivite(a)) { // if true
                new ActiviteViewForm().show();
            }
        });
        
       
       btnAnnuler.addActionListener(e -> {
           new ActiviteViewForm().show();
       });
       
       
       add(cn);
       add(CnBtn);
    }
}

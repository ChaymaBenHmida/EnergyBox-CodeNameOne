/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.competition;
import com.mycompany.services.ServiceCompetition;

/**
 *
 * @author Salima
 */
public class CompetitionEditForm extends Form{
    
    public CompetitionEditForm(competition c){
        
        Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container CnBtn=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button btnModifier=new Button("Modifier");
        Button btnAnnuler = new Button("Annuler");
        
        TextField nom = new TextField(c.getNomCompetition() , "Nom Competition" , 20 , TextField.ANY);
        TextField frais = new TextField(String.valueOf(c.getFraisCompetition()) , "Frais Competition" , 20 , TextField.ANY);
        TextField nbMInscrit = new TextField(String.valueOf(c.getNbMaxInscrit()) , "Nombre Max D'inscrit" , 20 , TextField.ANY);
        TextField etat = new TextField(String.valueOf(c.getEtatCompetition()) , "Etat Competition" , 20 , TextField.ANY);
        
        cn.add(nom);
        cn.add(frais);
        //cn.add(date);
        cn.add(nbMInscrit);
        cn.add(etat);
       // cn.add(nbParticipant);
        CnBtn.add(btnModifier);
        CnBtn.add(btnAnnuler);
        
btnModifier.addPointerPressedListener(l ->   { 
    c.setNomCompetition(nom.getText());
    c.setFraisCompetition(Integer.parseInt(frais.getText())); // Convertir le texte en entier
    c.setNbMaxInscrit(Integer.parseInt(nbMInscrit.getText())); // Convertir le texte en entier
    c.setEtatCompetition(etat.getText());
    if(ServiceCompetition.getInstance().ModifierCompetition(c)) { // if true
                  //  iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    refreshTheme();//Actualisation
                    Dialog.show("EnergyBox","Modication avec Succées","Annuler","OK");
    }
});    
btnAnnuler.addPointerPressedListener(l ->   { 
        new CompetitionViewForm().show();
    
});
       add(cn);
       add(CnBtn);
    }
}

    


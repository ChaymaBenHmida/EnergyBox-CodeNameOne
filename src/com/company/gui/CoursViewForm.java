/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.Cours;
import com.mycompany.services.ServiceCours;
import java.util.ArrayList;

/**
 *
 * @author azizo
 */
public class CoursViewForm extends Form{
    
    Form current;
    public CoursViewForm()
    {
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Afficher Cours");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        ArrayList<Cours>list = new ArrayList<>();
        list = ServiceCours.getInstance().AfficherCours();
        
        for(Cours c : list ) {
            
            Label nomCoursTxt = new Label("Nom Cours : "+c.getNomCours());
            Label prixCoursTxt = new Label("Prix Cours : "+c.getPrixCours());
            Label nomCoachTxt = new Label("Nom Coach : "+c.getNomCoach());
            Label ageMinTxt = new Label("Age Minimale : "+c.getAgeMinCours());
            Label descriptionCoursTxt = new Label("Description Cours : "+c.getDescriptionCours());
            Label separator = new Label("**************************");
            
            Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            cn.add(nomCoursTxt);
            cn.add(prixCoursTxt);
            cn.add(nomCoachTxt);
            cn.add(ageMinTxt);
            cn.add(descriptionCoursTxt);
            
            
            Label CoursSupprimer = new Label(" ");
            CoursSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(CoursSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            CoursSupprimer.setIcon(suprrimerImage);
            CoursSupprimer.setTextPosition(RIGHT);

            //click delete icon
            CoursSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if(dig.show("Suppression","Vous voulez supprimer ce Cours ?","Annuler","Oui")) {
                    dig.dispose();
                }
                else {
                    dig.dispose();
                     }
                    //n3ayto l suuprimer men service Reclamation
                    if(ServiceCours.getInstance().SupprimerCours(c.getId())) {
                        new CoursViewForm().show();
                    }

            });
            
            //Update icon 
            Label CoursModifier = new Label(" ");
            CoursModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(CoursModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);

            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            CoursModifier.setIcon(mFontImage);
            CoursModifier.setTextPosition(LEFT);


            CoursModifier.addPointerPressedListener(l -> {
                new CoursEditForm(c).show();
            });


            cn.add(CoursSupprimer);
            cn.add(CoursModifier);
            cn.add(separator);

            add(cn);
        }
        
        add(viewContainer);
        
        getToolbar().addCommandToLeftBar("Back",null,(ActionListener) (ActionEvent evt1) -> {
                new CoursForm().showBack();
        });
        
        /*FIN VIEW*/
    }
}

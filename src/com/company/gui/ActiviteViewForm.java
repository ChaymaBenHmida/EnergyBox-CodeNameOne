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
import com.mycompany.entities.Activite;
import com.mycompany.services.ServiceActivite;
import java.util.ArrayList;

/**
 *
 * @author azizo
 */
public class ActiviteViewForm extends Form{
    
    Form current;
    public ActiviteViewForm(){
        /*VIEW*/
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Afficher Activités");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        ArrayList<Activite>list = ServiceActivite.getInstance().AfficherActivite();
        
        for(Activite act : list ) {
            
            Label nomActTxt = new Label("Nom Activite : "+act.getNomActivite());
            Label dureeActTxt = new Label("Duree Activite : "+act.getDureeActivite());
            Label tenueActTxt = new Label("Tenue Activite : "+act.getTenueActivite());
            Label diffActTxt = new Label("Difficulte Activite : "+act.getDifficulteActivite());
            Label imageActTxt = new Label("Image Activite : "+act.getImageActivite());
            Label descriptionActTxt = new Label("Description Activite : "+act.getDescriptionActivite());
            Label separator = new Label("**************************");
            
            Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            cn.add(nomActTxt);
            cn.add(dureeActTxt);
            cn.add(tenueActTxt);
            cn.add(diffActTxt);
            cn.add(imageActTxt);
            cn.add(descriptionActTxt);
            
            
            Label ActSupprimer = new Label(" ");
            ActSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(ActSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            ActSupprimer.setIcon(suprrimerImage);
            ActSupprimer.setTextPosition(RIGHT);

            //click delete icon
            ActSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if(dig.show("Suppression","Vous voulez supprimer cet Activite ?","Annuler","Oui")) {
                    dig.dispose();
                }
                else {
                    dig.dispose();
                     }
                    //n3ayto l suuprimer men service Reclamation
                    if(ServiceActivite.getInstance().SupprimerActivite(act.getId())) {
                        new ActiviteViewForm().show();
                    }

            });
            
            //Update icon 
            Label ActModifier = new Label(" ");
            ActModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(ActModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);

            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            ActModifier.setIcon(mFontImage);
            ActModifier.setTextPosition(LEFT);


            ActModifier.addPointerPressedListener(l -> {
                new ActiviteEditForm(act).show();
            });


            cn.add(ActSupprimer);
            cn.add(ActModifier);
            cn.add(separator);

            add(cn);
        }
        
        add(viewContainer);
        
        
        

        
        
        
        getToolbar().addCommandToLeftBar("Back",null,(ActionListener) (ActionEvent evt1) -> {
                new ActiviteForm().showBack();
        });
        
        /*FIN VIEW*/
    }
}

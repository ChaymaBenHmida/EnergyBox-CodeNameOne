/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
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
import com.mycompany.entities.Ticket;
import com.mycompany.services.ServiceTicket;
import java.util.ArrayList;

/**
 *
 * @author Salima
 */
public class TicketViewForm extends Form{
    Form current;
    public TicketViewForm(){
        /*VIEW*/
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Afficher Tickets");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        ArrayList<Ticket>list = ServiceTicket.getInstance().AfficheTicket();
        
        for(Ticket t : list ) {
            
            Label idTck = new Label("id Ticket: "+t.getId());
            Label descTck = new Label("Description Ticket : "+t.getDescription());

            Label separator = new Label("**************************");
            
            Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            cn.add(idTck);
            cn.add(descTck);

            
            Label TicketSupprimer = new Label(" ");
            TicketSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(TicketSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            TicketSupprimer.setIcon(suprrimerImage);
            TicketSupprimer.setTextPosition(RIGHT);

            //click delete icon
            TicketSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if(dig.show("Suppression","Vous voulez supprimer ce Ticket ?","Annuler","Oui")) {
                    dig.dispose();
                }
                else {
                    dig.dispose();
                     }
                    //n3ayto l suuprimer men service Reclamation
                    if(ServiceTicket.getInstance().SupprimerTicket(t.getId())) {
                        new TicketViewForm().show();
                    }

            });
            
            //Update icon 
            Label TicketModifier = new Label(" ");
            TicketModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(TicketModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);

            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            TicketModifier.setIcon(mFontImage);
            TicketModifier.setTextPosition(LEFT);


            TicketModifier.addPointerPressedListener(l -> {
                try{
                    new TicketEditForm(t).showBack();
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            });

            cn.add(TicketSupprimer);
            cn.add(TicketModifier);
            cn.add(separator);

            add(cn);
        }
        
        add(viewContainer);
        
        
        

        
        
        
        getToolbar().addCommandToLeftBar("Back",null,(ActionListener) (ActionEvent evt1) -> {
                new TicketForm().showBack();
        });
        
        /*FIN VIEW*/
    }
    
}

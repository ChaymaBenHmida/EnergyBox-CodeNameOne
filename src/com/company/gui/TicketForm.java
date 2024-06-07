/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.Ticket;
import com.mycompany.services.ServiceTicket;

/**
 *
 * @author Salima
 */
public class TicketForm extends Form{
    
    Form current;
    public TicketForm() {
                
        current=this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Ajouter Un Ticket");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
          /*AJOUT*/
        
        Container addContainer = new Container(new FlowLayout(CENTER,CENTER));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button ListBtn=new Button("Consulter Liste");
        Button AjouterBtn=new Button("Ajouter");
        
        TextField desc=new TextField("","Description Ticket");
        addContainer.add(desc);
    
        btnContainer.add(AjouterBtn);
        btnContainer.add(ListBtn);
        btnContainer.setLayout(BoxLayout.y());
        
        AjouterBtn.addActionListener((e) -> {
            try {
                if(desc.getText().equals("") ) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                                    

            Ticket t = new Ticket(
                String.valueOf(desc.getText())
            );

                    
                    System.out.println("data Ticket == "+t);
                    
                    ServiceTicket.getInstance().AjouterTicket(t);

                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    refreshTheme();//Actualisation
                    Dialog.show("EnergyBox","Ajout avec Succés","Annuler","OK");
                }
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
        });
        
        ListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new TicketViewForm().show();
            }
        });
        
        add(addContainer);
        add(btnContainer);     
        
        /*FIN AJOUT*/
        
               /*NAVBAR*/
        getToolbar()
                .addMaterialCommandToSideMenu("Home",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm().show();
            }
        } );
        getToolbar()
                .addMaterialCommandToSideMenu("Competitions",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new CompetitionForm().show();
            }
        } );

        getToolbar()
                .addMaterialCommandToSideMenu("Ticket",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new TicketForm().show();
            }
        } );
        
        
     
}
    
}

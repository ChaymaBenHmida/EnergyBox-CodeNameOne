/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
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
import com.mycompany.entities.competition;
import com.mycompany.services.ServiceCompetition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Salima
 */


public class CompetitionForm extends Form{
    
    Form current;
    public CompetitionForm() {
                
        current=this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Ajouter Une Competition");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        
        
           /*AJOUT*/
        
        Container addContainer = new Container(new FlowLayout(CENTER,CENTER));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button ListBtn=new Button("Consulter Liste");
        Button AjouterBtn=new Button("Ajouter");
        
        TextField nomCmp=new TextField("","Nom Competition");
        TextField fraisCmp=new TextField("","Frais Competition");
        TextField dateCmp=new TextField("","Date Competition");
        TextField nbMaxI=new TextField("","Nombre Max D'inscrit");
        TextField etatCmp=new TextField("","Etat Competition");
        
        addContainer.add(nomCmp);
        addContainer.add(fraisCmp);
        addContainer.add(dateCmp);
        addContainer.add(nbMaxI);
        addContainer.add(etatCmp);
        
        btnContainer.add(AjouterBtn);
        btnContainer.add(ListBtn);
        btnContainer.setLayout(BoxLayout.y());
        
        AjouterBtn.addActionListener((e) -> {
            try {
                if(nomCmp.getText().equals("") || fraisCmp.getText().equals("") || dateCmp.getText().equals("") || nbMaxI.getText().equals("") || etatCmp.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                                    


                   

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
            Date date = null;
            try {
                date = format.parse(dateCmp.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            competition c = new competition(
                String.valueOf(nomCmp.getText()),
                Integer.parseInt(String.valueOf(fraisCmp.getText())),
                date,
                Integer.parseInt(String.valueOf(nbMaxI.getText())),
                String.valueOf(etatCmp.getText())
            );

                    
                    System.out.println("data Competition == "+c);
                    
                    ServiceCompetition.getInstance().AjouterCompetition(c);

                    
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
                new CompetitionViewForm().show();
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

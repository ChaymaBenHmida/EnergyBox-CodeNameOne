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
import com.mycompany.entities.Activite;
import com.mycompany.services.ServiceActivite;

/**
 *
 * @author azizo
 */
public class ActiviteForm extends Form{
    
    Form current;
    public ActiviteForm() {
                
        current=this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Ajouter Activité");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        
        
        
        
    
        /*AJOUT*/
        
        Container addContainer = new Container(new FlowLayout(CENTER,CENTER));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button ListBtn=new Button("Consulter Liste");
        Button AjouterBtn=new Button("Ajouter");
        
        TextField nomAct=new TextField("","Nom Activité");
        TextField dureeAct=new TextField("","Durée Activité");
        TextField tenueAct=new TextField("","Tenue Activité");
        TextField diffAct=new TextField("","Difficulté Activité");
        TextField imageAct=new TextField("","Image Activité");
        TextField descriptionAct=new TextField("","Description Activité");
        
        addContainer.add(nomAct);
        addContainer.add(dureeAct);
        addContainer.add(tenueAct);
        addContainer.add(diffAct);
        addContainer.add(imageAct);
        addContainer.add(descriptionAct);
        
        btnContainer.add(AjouterBtn);
        btnContainer.add(ListBtn);
        btnContainer.setLayout(BoxLayout.y());
        
        AjouterBtn.addActionListener((e) -> {
            try {
                if(nomAct.getText().equals("") || dureeAct.getText().equals("") || tenueAct.getText().equals("") || diffAct.getText().equals("") || imageAct.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                                        
                    //njibo iduser men session (current user)
                    Activite a = new Activite(
                            30,
                            String.valueOf(nomAct.getText()),
                            String.valueOf(dureeAct.getText()),
                            String.valueOf(tenueAct.getText()),
                            String.valueOf(diffAct.getText()),
                            String.valueOf(imageAct.getText()),
                            String.valueOf(descriptionAct.getText())
                                  );
                    
                    System.out.println("data Activite == "+a);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceActivite.getInstance().AjouterActivite(a);
                    
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
                new ActiviteViewForm().show();
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
                .addMaterialCommandToSideMenu("Activités",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ActiviteForm().show();
            }
        } );
        getToolbar()
                .addMaterialCommandToSideMenu("Cours",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new CoursForm().show();
            }
        } );
        getToolbar()
                .addMaterialCommandToSideMenu("Planning",FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new PlanningForm().show();
            }
        } );
        
        
    }

    
    
       
}

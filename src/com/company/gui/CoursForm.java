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
import com.mycompany.entities.Cours;
import com.mycompany.services.ServiceCours;

/**
 *
 * @author azizo
 */
public class CoursForm extends Form{
    
    Form current;
    public CoursForm() {
        current=this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Ajouter Cours");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        
        /*AJOUT*/
        Container addContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button ListBtn=new Button("Consulter Liste");
        Button AjouterBtn=new Button("Ajouter");
        
        TextField nomCours=new TextField("","Nom Cours");
        TextField prixCours=new TextField("","Prix Cours");
        TextField nomCoachCours=new TextField("","Nom Coach");
        TextField ageMinCours=new TextField("","Age Minimale");
        TextField descriptionCours=new TextField("","Description Cours");
        
        addContainer.add(nomCours);
        addContainer.add(prixCours);
        addContainer.add(nomCoachCours);
        addContainer.add(ageMinCours);
        addContainer.add(descriptionCours);
        
        btnContainer.add(AjouterBtn);
        btnContainer.add(ListBtn);
        
        AjouterBtn.addActionListener((e) -> {
            try {
                if(nomCours.getText().equals("") || prixCours.getText().equals("") || nomCoachCours.getText().equals("") || ageMinCours.getText().equals("") || descriptionCours.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                                        
                    //njibo iduser men session (current user)
                    Cours c = new Cours(
                            30,
                            String.valueOf(nomCours.getText()),
                            String.valueOf(nomCoachCours.getText()),
                            Integer.parseInt(ageMinCours.getText()),
                            Float.parseFloat(prixCours.getText()),
                            String.valueOf(descriptionCours.getText())
                                  );
                    
                    System.out.println("data Cours == "+c);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServiceCours.getInstance().AjouterCours(c);
                    
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
                new CoursViewForm().show();
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

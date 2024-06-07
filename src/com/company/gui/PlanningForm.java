/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.InfiniteProgress;
import java.text.SimpleDateFormat;
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
import com.mycompany.entities.Planning;
import com.mycompany.services.ServicePlanning;
import java.util.Date;

/**
 *
 * @author azizo
 */
public class PlanningForm extends Form{
    Form current;
    public PlanningForm() {
        
        current=this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Ajouter Planning");
        getContentPane().setScrollVisible(true);
        
        tb.addSearchCommand(e ->{
            
        });
        
        /*AJOUT*/
        
        Container addContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button ListBtn=new Button("Consulter Liste");
        Button AjouterBtn=new Button("Ajouter");
        
        TextField datePlanning=new TextField("","Date Planning");
        TextField jourPlanning=new TextField("","Jour Planning");
        TextField heurePlanning=new TextField("","Heure Planning");
        //ComboBox <Cours> cours = new ComboBox<>();

        addContainer.add(datePlanning);
        addContainer.add(jourPlanning);
        addContainer.add(heurePlanning);
        //addContainer.add(cours);
        
        btnContainer.add(AjouterBtn);
        btnContainer.add(ListBtn);
        
        AjouterBtn.addActionListener((e) -> {
            try {
                if(datePlanning.getText().equals("") || jourPlanning.getText().equals("") || heurePlanning.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate= String.valueOf(datePlanning.getText());
                    Date dateDB = format.parse(formattedDate);
                    
                    Planning p = new Planning(
                            30,
                            dateDB,
                            String.valueOf(jourPlanning.getText()),
                            Integer.parseInt(heurePlanning.getText())
                                  );
                    
                    System.out.println("data Planning == "+p);
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServicePlanning.getInstance().AjouterPlanning(p);
                    
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
                new PlanningViewForm().show();
            }
        });
        
        
        add(addContainer);
        add(btnContainer);
        
        /*FIN AJOUT*/
                
        
        
        
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

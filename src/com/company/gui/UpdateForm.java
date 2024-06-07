/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Plat;
import com.mycompany.piMobile.MyApplication;
import com.mycompany.services.PlatServices;
import java.text.SimpleDateFormat;

/**
 *
 * @author Dell
 */
public class UpdateForm extends BaseForm1{
     Form current ;
    public UpdateForm(Resources res ) {
      super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField nom = new TextField(MyApplication.plat.getNom(), "Nom", 20, TextField.EMAILADDR);
        TextField description = new TextField(MyApplication.plat.getDescription(), "Descripton", 20, TextField.EMAILADDR);
        TextField calories = new TextField(MyApplication.plat.getCalories(), "Calories", 20, TextField.EMAILADDR);
        TextField etat = new TextField(MyApplication.plat.getEtat(), "Etat", 20, TextField.EMAILADDR);
        TextField nbp = new TextField(String.valueOf(MyApplication.plat.getNbp()), "Nbp", 20, TextField.EMAILADDR);
        TextField image = new TextField(MyApplication.plat.getImage(), "Image", 20, TextField.EMAILADDR);
        TextField prix = new TextField(String.valueOf(MyApplication.plat.getPrix()), "Prix", 20, TextField.EMAILADDR);
        TextField user = new TextField(MyApplication.plat.getUser_id(), "user id", 20, TextField.EMAILADDR);
        
        
      
        
        Picker start = new Picker();
start.setType(Display.PICKER_TYPE_DATE);
        description.setSingleLineTextArea(false);
        calories.setSingleLineTextArea(false);
        etat.setSingleLineTextArea(false);
        nbp.setSingleLineTextArea(false);
        image.setSingleLineTextArea(false);
        prix.setSingleLineTextArea(false);
        user.setSingleLineTextArea(false);
        
        Button next = new Button("Modifier");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
     
        
        Container content = BoxLayout.encloseY(
                new Label("Modifier Un Plat", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(description),
                    createLineSeparator(),
                new FloatingHint(calories),
                    createLineSeparator(),
                new FloatingHint(etat),
                    createLineSeparator(),
                new FloatingHint(nbp),
                    createLineSeparator(),
                new FloatingHint(image),
                    createLineSeparator(),
                new FloatingHint(prix),
                    createLineSeparator(),
                new FloatingHint(user),
  
                start
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next
            
        ));
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  
                    
                    
                    
              
              

   try {
                
                if(nom.getText().equals("") || description.getText().equals("") || calories.getText().equals("") || nbp.getText().equals("") || prix.getText().equals("") || image.getText().equals("") || etat.getText().equals("") || user.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
                    
                    
                    //njibo iduser men session (current user)
                    Plat plat = new Plat(
                            Integer.parseInt(String.valueOf(nbp.getText()).toString()),
                            String.valueOf(nom.getText()).toString(),
                            String.valueOf(description.getText()).toString(),
                              String.valueOf(calories.getText()).toString(),
                            String.valueOf(etat.getText()).toString(),
                              String.valueOf(user.getText()).toString(),
                            String.valueOf(image.getText()).toString(),
                            Float.parseFloat(String.valueOf(prix.getText()).toString())
                                 ) ;
                            
                                 
                    
                    System.out.println("data  plat == "+plat);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    PlatServices.getInstance().updatePlat(plat);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListREclamationForm
                    new PlatList(res).show();
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
                
                    
                    
          
                            
        
            
            
            }
        });
    }
}

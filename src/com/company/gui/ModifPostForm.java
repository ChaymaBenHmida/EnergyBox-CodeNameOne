/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.piMobile.MyApplication;

/**
 *
 * @author Eya Krichene
 */
public class ModifPostForm extends Form{
    public ModifPostForm(){ 
    
     this.setTitle("Modifier Post");
        Container addContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
       
        Button AjouterBtn=new Button("Valider");
                Button annuler=new Button("Annuler");
        Button SuppBtn=new Button("Supprimmer");

        
        TextField titre=new TextField(MyApplication.p.getTitle(),"Titre Post");
        TextField descp=new TextField(MyApplication.p.getDetails(),"Description Post");
       
        
  
        addContainer.add(titre);
        addContainer.add(descp);
       
      
        
        btnContainer.add(AjouterBtn);
         btnContainer.add(SuppBtn);
        btnContainer.add(annuler);
       
       
        annuler.addActionListener(eee->{
             
               new BlogForm().show();
           });
        
        this.add(addContainer);
        this.add(btnContainer);
          SuppBtn.addActionListener(eees->{
                MyApplication.modifidp(MyApplication.p.getId());
                ConnectionRequest req1 = new ConnectionRequest();
      String url1 = "http://127.0.0.1:8000/testdeletee?id="+MyApplication.idp;
         req1.setUrl(url1);
          req1.addResponseListener(eess->{
        String reponse1 = new String(req1.getResponseData());
        
               new BlogForm().show();
          });
           NetworkManager.getInstance().addToQueueAndWait(req1);
          });
        AjouterBtn.addActionListener(e->{
        ConnectionRequest req = new ConnectionRequest();
      String url = "http://127.0.0.1:8000/edit/json?title=" + titre.getText()
            + "&det=" + descp.getText()
              +"&id="+MyApplication.idp;
         req.setUrl(url);
        req.addResponseListener(ee->{
        String reponse = new String(req.getResponseData());
        System.out.println(reponse);
  
        new BlogForm().show();
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        });
    }
}

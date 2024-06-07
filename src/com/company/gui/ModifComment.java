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
import com.mycompany.entities.Post;
import com.mycompany.piMobile.MyApplication;

/**
 *
 * @author Eya Krichene
 */
public class ModifComment extends Form{
    public ModifComment(Post pp){
    
    this.setTitle("Modifier Commentaire");
        Container addContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
       
        Button AjouterBtn=new Button("Valider");
                Button annuler=new Button("Annuler");
       

        
        TextField titre=new TextField(MyApplication.c.getDescription(),"Comment");
       
        
  
        addContainer.add(titre);
       
      
        
        btnContainer.add(AjouterBtn);
        btnContainer.add(annuler);
       
       
        annuler.addActionListener(eee->{
             
               new CommentForm(pp).show();
           });
        
        this.add(addContainer);
        this.add(btnContainer);
         
        AjouterBtn.addActionListener(e->{
        ConnectionRequest req = new ConnectionRequest();
      String url = "http://127.0.0.1:8000/edittJ?content="+titre.getText()
              +"&id="+MyApplication.c.getId();
         req.setUrl(url);
        req.addResponseListener(ee->{
        String reponse = new String(req.getResponseData());
        System.out.println(reponse);
  
        new CommentForm(pp).show();
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        });
    }
}

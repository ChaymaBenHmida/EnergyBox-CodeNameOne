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

/**
 *
 * @author Eya Krichene
 */
public class AjoutCommentaire extends Form{
    public AjoutCommentaire(Post p){
      
        this.setTitle("Ajouter Commentaire");
        Container addContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
       
        Button AjouterBtn=new Button("Ajouter");
                        Button annuler=new Button("Annuler");

        
       
        TextField descp=new TextField("","Comment");
       
        
  
      
        addContainer.add(descp);
       
      
        
        btnContainer.add(AjouterBtn);
        btnContainer.add(annuler);
        this.add(addContainer);
        this.add(btnContainer);
         annuler.addActionListener(eeehdee->{
            
               new CommentForm(p).show();
           });
        AjouterBtn.addActionListener(e->{
        ConnectionRequest req = new ConnectionRequest();
      String url = "http://127.0.0.1:8000/new/json?post=" + p.getId()
            + "&contenu=" + descp.getText();
         req.setUrl(url);
        req.addResponseListener(ee->{
        String reponse = new String(req.getResponseData());
        System.out.println(reponse);
  
        new CommentForm(p).show();
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        });
    }
}

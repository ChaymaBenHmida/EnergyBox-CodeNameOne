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
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Post;
import com.mycompany.piMobile.MyApplication;
import com.mycompany.services.PostServices;
import java.util.ArrayList;

/**
 *
 * @author Eya Krichene
 */
public class TriForm extends Form{
    public TriForm(){
        
        
       this.setTitle("Posts");
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        
       
        
        ConnectionRequest req = new ConnectionRequest();
        PostServices ser = new PostServices();
      String url = "http://127.0.0.1:8000/trip";
         req.setUrl(url);
          req.addResponseListener(e->{
        String reponse = new String(req.getResponseData());
        System.out.println("reponse ::: "+reponse);
        
            ArrayList<Post> posts = ser.parsePost(reponse);
                    

            for (Post post : posts) {

           Label title= new Label("Titre post : " +post.getTitle());
        Label details = new Label("Détails post : " + post.getDetails());
        Label image = new Label("image post : " + post.getImage());
        Label datePost = new Label("Date post : " + post.getDatePost());       

        Label line = new Label("----------------------------------------------------------" );
        Button ModifBtn=new Button("Modifier");
        Button SuppBtn=new Button("Comments");
        viewContainer.add(title);
        viewContainer.add(details);
        viewContainer.add(image);
        viewContainer.add(datePost);
     
  //       viewContainer.add(Listcategp);
           viewContainer.add(ModifBtn);
           viewContainer.add(SuppBtn);
                     

           viewContainer.add(line);
          
           ModifBtn.addActionListener(eee->{
                MyApplication.modifidp(post.getId());
                MyApplication.p = post;
               new ModifPostForm().show();
           });
          SuppBtn.addActionListener(eees->{
             
                MyApplication.p = post;
               new CommentForm(post).show();
        
              
          });
          
      
          
        
        
        
                            }
            Button BackM=new Button("Home");
                    Button ajoutPost=new Button("Ajouter Post");
                            viewContainer.add(ajoutPost);
                            viewContainer.add(BackM);
           ajoutPost.addActionListener(eee->{
             
               new AjoutPostForm().show();
           });
BackM.addActionListener(eeaa->{
 new HomeForm().show();
});
        });
         
        NetworkManager.getInstance().addToQueueAndWait(req);
         this.add(viewContainer);  
        
    }
}

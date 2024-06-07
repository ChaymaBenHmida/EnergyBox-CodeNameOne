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
import com.mycompany.entities.Comment;
import com.mycompany.entities.Post;
import com.mycompany.piMobile.MyApplication;
import com.mycompany.services.CommentServices;
import java.util.ArrayList;

/**
 *
 * @author Eya Krichene
 */
public class CommentForm extends Form{
    public CommentForm(Post pp){
    
     this.setTitle("Comments");
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

       
        
        ConnectionRequest req = new ConnectionRequest();
        CommentServices ser = new CommentServices();
      String url = "http://127.0.0.1:8000/testC?idp="+pp.getId();
        req.setUrl(url);
       

          req.addResponseListener(e->{
        String reponse = new String(req.getResponseData());
        System.out.println("reponse ::: "+reponse);
        
            ArrayList<Comment> comments = ser.parseCom(reponse);
            
            for (Comment post : comments) {

           Label description= new Label("Comment : " +post.getDescription());
        Label date = new Label("Date : " + post.getDateComment());
        

        Label line = new Label("----------------------------------------------------------" );
     Button ModifBtn=new Button("Modifier");
        Button SuppBtn=new Button("Supprimer");
        viewContainer.add(description);
        viewContainer.add(date);
       
     
  //       viewContainer.add(Listcategp);
        viewContainer.add(ModifBtn);
           viewContainer.add(SuppBtn);
//                     

           viewContainer.add(line);
          
       ModifBtn.addActionListener(eee->{
            MyApplication.c = post;
//              
          new ModifComment(pp).show();
       });
         SuppBtn.addActionListener(eees->{
               MyApplication.modifidp(post.getId());
              ConnectionRequest req1 = new ConnectionRequest();
      String url1 = "http://127.0.0.1:8000/deleteeeJ?id="+post.getId();
        req1.setUrl(url1);
        req1.addResponseListener(eess->{
      String reponse1 = new String(req1.getResponseData());
      System.out.println("reponse ::: "+reponse1);
              new CommentForm(pp).show();
         });
          NetworkManager.getInstance().addToQueueAndWait(req1);
         });
          
        
        
        
                            }
            Button BackM=new Button("Back to post");
                    Button ajoutPost=new Button("Ajouter Commentaire");
                            viewContainer.add(ajoutPost);
                            viewContainer.add(BackM);
           ajoutPost.addActionListener(eee->{
               new AjoutCommentaire(pp).show();
           });
BackM.addActionListener(eeaa->{
 new BlogForm().show();
});
        });
         
        NetworkManager.getInstance().addToQueueAndWait(req);
         this.add(viewContainer);
        
    }
    
}

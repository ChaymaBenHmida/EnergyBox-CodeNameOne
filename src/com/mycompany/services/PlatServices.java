/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Plat;
import com.mycompany.piMobile.MyApplication;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public class PlatServices {
      public static PlatServices instance = null ;
    public int resultCode;
    public static boolean resultOk = true;
    private ArrayList<Plat> listplat;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static PlatServices getInstance() {
        if(instance == null )
            instance = new PlatServices();
        return instance ;
    }
    
    
    
    public PlatServices() {
        req = new ConnectionRequest();
        
    }
    //affichage
    public ArrayList<Plat>AffichagePlat() {
        ArrayList<Plat> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"AllPlatsjson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapannonce = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapannonce.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Plat plat = new Plat();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        float nbp = Float.parseFloat(obj.get("nbp").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                        String description = obj.get("description").toString();
                        String calories = obj.get("calories").toString();
                        String etat = obj.get("etat").toString();
                      //  String UserId = obj.get("UserId").toString();
                        String image = obj.get("image").toString();
                        
                        
                        
                        plat.setId((int)id) ; 
                        plat.setNbp((int)nbp) ;
                        plat.setPrix(prix) ; 
                        plat.setNom(nom);
                        plat.setDescription(description);
                        plat.setCalories(calories);
                        plat.setEtat(etat);
                      //  plat.setUser_id(UserId);
                        plat.setImage(image);
                        
//insert data into ArrayList result
                        result.add(plat);

                    }
                    
                }catch(IOException | NumberFormatException | NullPointerException ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    public boolean deletePlat(int id){
     String url = Statics.BASE_URL+"DeletePlatsjson?id="+id;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
         @Override
         public void actionPerformed(NetworkEvent evt) {
             req.removeResponseCodeListener(this);//To change body of generated methods, choose Tools | Templates.
         }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
         public boolean ajoutPlat(Plat plat) {
        
        String url =Statics.BASE_URL+"AddPlatsjson/new?nom="+plat.getNom()+"&prix="+plat.getPrix()+"&desc="+plat.getDescription()+"&calories="+plat.getCalories()+"&etat="+plat.getEtat()+"&nbp="+plat.getNbp()+"&image="+plat.getImage()+"&user="+80; // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        ConnectionRequest req = new ConnectionRequest(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOk = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
    }
  public boolean updatePlat(Plat plat) {
        
        String url =Statics.BASE_URL+"UpatePlatsjson?nom="+plat.getNom()+"&prix="+plat.getPrix()+"&desc="+plat.getDescription()+"&calories="+plat.getCalories()+"&etat="+plat.getEtat()+"&nbp="+plat.getNbp()+"&image="+plat.getImage()+"&user="+plat.getUser_id()+"&id="+MyApplication.plat.getId(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        ConnectionRequest req = new ConnectionRequest(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOk = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
    }
  
   public ArrayList<Plat>TriPlat() {
        ArrayList<Plat> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"AllPlatsjsonTri";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapannonce = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapannonce.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Plat plat = new Plat();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        float nbp = Float.parseFloat(obj.get("nbp").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                        String description = obj.get("description").toString();
                        String calories = obj.get("calories").toString();
                        String etat = obj.get("etat").toString();
                      //  String UserId = obj.get("UserId").toString();
                        String image = obj.get("image").toString();
                        
                        
                        
                        plat.setId((int)id) ; 
                        plat.setNbp((int)nbp) ;
                        plat.setPrix(prix) ; 
                        plat.setNom(nom);
                        plat.setDescription(description);
                        plat.setCalories(calories);
                        plat.setEtat(etat);
                    //    plat.setUser_id(UserId);
                        plat.setImage(image);
                        
//insert data into ArrayList result
                        result.add(plat);

                    }
                    
                }catch(IOException | NumberFormatException | NullPointerException ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
   
      public ArrayList<Plat>SearchPlat(int prix) {
        ArrayList<Plat> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"RechercheMobile/"+prix;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapannonce = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapannonce.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Plat plat = new Plat();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        float nbp = Float.parseFloat(obj.get("nbp").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                        String description = obj.get("description").toString();
                        String calories = obj.get("calories").toString();
                        String etat = obj.get("etat").toString();
                        String UserId = obj.get("UserId").toString();
                        String image = obj.get("image").toString();
                        
                        
                        
                        plat.setId((int)id) ; 
                        plat.setNbp((int)nbp) ;
                        plat.setPrix(prix) ; 
                        plat.setNom(nom);
                        plat.setDescription(description);
                        plat.setCalories(calories);
                        plat.setEtat(etat);
                        plat.setUser_id(UserId);
                        plat.setImage(image);
                        
//insert data into ArrayList result
                        result.add(plat);

                    }
                    
                }catch(IOException | NumberFormatException | NullPointerException ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
}

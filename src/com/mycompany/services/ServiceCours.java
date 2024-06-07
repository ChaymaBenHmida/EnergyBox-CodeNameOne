/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Cours;
import com.mycompany.utils.ConnectionApi;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azizo
 */
public class ServiceCours {
    //singleton
    public static ServiceCours instance = null;
    
    private ConnectionRequest req;
    
    public static boolean resultOk = true;
    
    public static ServiceCours getInstance(){
        if(instance == null)
            instance = new ServiceCours();
        return instance;
    }
    
    public ServiceCours(){
        req = new ConnectionRequest();
    }
    
    
    public void AjouterCours(Cours c){
        
        String url=ConnectionApi.BASE_URL+"cours/newJSON?nomCours="+c.getNomCours()+
                "&prixCours="+c.getPrixCours()+
                "&nomCoach="+c.getNomCoach()+
                "&ageMinCours="+c.getAgeMinCours()+
                "&descriptionCours="+c.getDescriptionCours();
        
        req.setUrl(url);
        req.addResponseListener((e)->{
           
            String str = new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    public ArrayList<Cours>AfficherCours(){
        ArrayList<Cours> result =new ArrayList<>();
        
        String url =ConnectionApi.BASE_URL+"cours/viewJSON";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                
                jsonp=new JSONParser();
                
                try{
                    Map<String,Object>mapCours = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)mapCours.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps){
                        Cours a=new Cours();
                        
                        float id=Float.parseFloat(obj.get("id").toString());
                        
                        String nomCours = obj.get("nomCours").toString();
                        String prixCours = obj.get("prixCours").toString();
                        String nomCoach = obj.get("nomCoach").toString();
                        String ageMinCours = obj.get("ageMinCours").toString();
                        String descriptionCours = obj.get("descriptionCours").toString();
                        int age=(int)Double.parseDouble(ageMinCours);
                        
                        
                        a.setId((int)id);
                        a.setNomCours(nomCours);
                        a.setPrixCours(Double.parseDouble(String.valueOf(prixCours)));
                        a.setNomCoach(nomCoach);
                        a.setAgeMinCours(age);
                        a.setDescriptionCours(descriptionCours);
                        
                        result.add(a);
                        
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return result;
    }
    
    
    //Suppression Cours
    public boolean SupprimerCours(int id ) {
        String url = ConnectionApi.BASE_URL +"cours/deleteJSON/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    //Modification Cours
    public boolean ModifierCours(Cours c) {
        String url = ConnectionApi.BASE_URL +"cours/editJSON/"+c.getId()+"?nomCours="+c.getNomCours()+
                "&prixCours="+c.getPrixCours()+
                "&nomCoach="+c.getNomCoach()+
                "&ageMinCours="+c.getAgeMinCours()+
                "&descriptionCours="+c.getDescriptionCours();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;
        
    }
}

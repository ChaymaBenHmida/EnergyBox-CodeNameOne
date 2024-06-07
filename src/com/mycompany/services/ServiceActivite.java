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
import com.mycompany.entities.Activite;
import com.mycompany.utils.ConnectionApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author azizo
 */
public class ServiceActivite {
    
    //singleton
    public static ServiceActivite instance = null;
    
    private ConnectionRequest req;
    
    public static boolean resultOk = true;
    
    public static ServiceActivite getInstance(){
        if(instance == null)
            instance = new ServiceActivite();
        return instance;
    }
    
    public ServiceActivite(){
        req = new ConnectionRequest();
    }
    
    public void AjouterActivite(Activite activite){
        
        String url=ConnectionApi.BASE_URL+"activite/newJSON?nomActivite="+activite.getNomActivite()+
                "&dureeActivite="+activite.getDureeActivite()+
                "&tenueActivite="+activite.getTenueActivite()+
                "&difficulteActivite="+activite.getDifficulteActivite()+
                "&imageActivite="+activite.getImageActivite()+
                "&descriptionActivite="+activite.getDescriptionActivite();
        
        req.setUrl(url);
        req.addResponseListener((e)->{
           
            String str = new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    //Affichage des Activites
    public ArrayList<Activite>AfficherActivite(){
        ArrayList<Activite> result =new ArrayList<>();
        
        String url =ConnectionApi.BASE_URL+"activite/viewJSON";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                
                jsonp=new JSONParser();
                
                try{
                    Map<String,Object>mapActivites = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)mapActivites.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps){
                        Activite a=new Activite();
                        
                        float id=Float.parseFloat(obj.get("id").toString());
                        
                        String nomActivite = obj.get("nomActivite").toString();
                        String dureeActivite = obj.get("dureeActivite").toString();
                        String tenueActivite = obj.get("tenueActivite").toString();
                        String difficulteActivite = obj.get("difficulteActivite").toString();
                        String imageActivite = obj.get("imageActivite").toString();
                        String descriptionActivite = obj.get("descriptionActivite").toString();
                        
                        
                        a.setId((int)id);
                        a.setNomActivite(nomActivite);
                        a.setDureeActivite(dureeActivite);
                        a.setTenueActivite(tenueActivite);
                        a.setDifficulteActivite(difficulteActivite);
                        a.setImageActivite(imageActivite);
                        a.setDescriptionActivite(descriptionActivite);
                        
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
    
    //Detail Activite
    public Activite DetailActivite(int id, Activite act){
        String url= ConnectionApi.BASE_URL+"activite/showJSON/"+id;
        
        req.setUrl(url);
        
        String str = new String(req.getResponseData());
        req.addResponseListener((evt)->{
            JSONParser jsonp = new JSONParser();
            try{
                Map<String,Object> obj=jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                act.setNomActivite(obj.get("nomActivite").toString());
                act.setDureeActivite(obj.get("dureeActivite").toString());
                act.setTenueActivite(obj.get("tenueActivite").toString());
                act.setDifficulteActivite(obj.get("difficulteActivite").toString());
                act.setImageActivite(obj.get("imageActivite").toString());
                act.setDescriptionActivite(obj.get("descriptionActivite").toString());
                
            }catch(IOException ex){
                System.out.println("Error related to sql : { "+ex.getMessage());
            }
            
            System.out.println("data = "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return act;
    }
    
    
    //Suppression Activite
    public boolean SupprimerActivite(int id ) {
        String url = ConnectionApi.BASE_URL +"activite/deleteJSON/"+id;
        
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
    
    
    //Modification Activite
    public boolean ModifierActivite(Activite act) {
        String url = ConnectionApi.BASE_URL +"activite/editJSON/"+act.getId()+"?nomActivite="+act.getNomActivite()+
                "&dureeActivite="+act.getDureeActivite()+
                "&tenueActivite="+act.getTenueActivite()+
                "&difficulteActivite="+act.getDifficulteActivite()+
                "&imageActivite="+act.getImageActivite()+
                "&descriptionActivite="+act.getDescriptionActivite();
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

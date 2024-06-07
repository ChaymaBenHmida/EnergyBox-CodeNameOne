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
import com.mycompany.entities.Planning;
import com.mycompany.utils.ConnectionApi;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azizo
 */
public class ServicePlanning {
    //singleton
    public static ServicePlanning instance = null;
    
    public static boolean resultOk = true;
        
    private ConnectionRequest req;
    
    public static ServicePlanning getInstance(){
        if(instance == null)
            instance = new ServicePlanning();
        return instance;
    }
    
    public ServicePlanning(){
        req = new ConnectionRequest();
    }
    
    public void AjouterPlanning(Planning planning){
        
        String url=ConnectionApi.BASE_URL+"planning/newJSON?datePlanning="+planning.getDatePlanning()+
                "&jourPlanning"+planning.getJourPlanning()+
                "&heurePlanning="+planning.getHeurePlanning();
        
        
        req.setUrl(url);
        req.addResponseListener((e)->{
           
            String str = new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
    public ArrayList<Planning>AfficherPlanning(){
        ArrayList<Planning> result =new ArrayList<>();
        
        String url =ConnectionApi.BASE_URL+"planning/viewJSON";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                
                jsonp=new JSONParser();
                
                try{
                    Map<String,Object>mapPlanning = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>)mapPlanning.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps){
                        Planning p=new Planning();
                        
                        float id=Float.parseFloat(obj.get("id").toString());
                        
                        String datePlanning = obj.get("datePlanning").toString();
                        String jourPlanning = obj.get("jourPlanning").toString();
                        String heurePlanning = obj.get("heurePlanning").toString();
                        //String ageMinCours = obj.get("ageMinCours").toString();
                        int heure=(int)Double.parseDouble(heurePlanning);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = dateFormat.parse(datePlanning);
                        
                        
                        p.setId((int)id);
                        p.setDatePlanning(date);
                        p.setJourPlanning(jourPlanning);
                        p.setHeurePlanning(heure);
                        //a.setAgeMinCours(age);
                        
                        
                        result.add(p);
                        
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
    public boolean SupprimerPlanning(int id ) {
        String url = ConnectionApi.BASE_URL +"planning/deleteJSON/"+id;
        
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
    public boolean ModifierPlanning(Planning p) {
        String url = ConnectionApi.BASE_URL +"planning/editJSON/"+p.getId()+
                "?datePlanning="+p.getDatePlanning()+
                "&jourPlanning="+p.getJourPlanning()+
                "&heurePlanning="+p.getHeurePlanning();
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

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
import com.mycompany.entities.Ticket;
import com.mycompany.utils.ConnectionApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Salima
 */
public class ServiceTicket {
     public static ServiceTicket instance = null;
    
    private ConnectionRequest req;
    
    public static boolean resultOk = true;
    
    public static ServiceTicket getInstance(){
        if(instance == null)
            instance = new ServiceTicket();
        return instance;
    }
       public ServiceTicket(){
        req = new ConnectionRequest();
    }
       
    public void AjouterTicket(Ticket ticket){
        
        String url=ConnectionApi.BASE_URL+"ticket/newJsonn?descriptionTicket="+ticket.getDescription();                
        
        req.setUrl(url);
        req.addResponseListener((e)->{
           
            String str = new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
public ArrayList<Ticket> AfficheTicket() {
    ArrayList<Ticket> result = new ArrayList<>();
    String url = ConnectionApi.BASE_URL + "ticket/json";
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String,Object> mapTickets = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapTickets.get("root");

                for (Map<String, Object> obj : listOfMaps) {
                    Ticket t = new Ticket();

                    float id = Float.parseFloat(obj.get("id").toString());                       
                    String descriptionTicket= obj.get("descriptionTicket").toString();
                    t.setId((int) id);
                    t.setDescription(descriptionTicket);
                
                    System.out.println(t.toString());
                    result.add(t);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);

    return result;
}
  public Ticket DetailTicket(int id, Ticket t){
        String url= ConnectionApi.BASE_URL+"ticket/"+id+"/json/";
        
        req.setUrl(url);
        
        String str = new String(req.getResponseData());
req.addResponseListener((evt)->{
    JSONParser jsonp = new JSONParser();
    try{
        Map<String,Object> obj=jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
        
        t.setId(Integer.parseInt(obj.get("id").toString()));
        t.setDescription(obj.get("descriptionTicket").toString());
        
    } catch(IOException ex){
        System.out.println("Error related to sql : { "+ex.getMessage());
    } 
    
    System.out.println("data = "+str);
});

        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return t;
    }

 
    //Suppression Ticket
    public boolean SupprimerTicket(int id ) {
        String url = ConnectionApi.BASE_URL +"ticket/"+id+"/deleteJson";
        
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
  
        public boolean ModifierTicket(Ticket tck) {
        String url = ConnectionApi.BASE_URL +"ticket/editJson/"+tck.getId()+"?descriptionTicket="+tck.getDescription();
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

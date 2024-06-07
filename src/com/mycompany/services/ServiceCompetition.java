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
import com.mycompany.entities.competition;
import com.mycompany.utils.ConnectionApi;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.ParseException;


/**
 *
 * @author Salima
 */
public class ServiceCompetition {
     //singleton
    public static ServiceCompetition instance = null;
    
    private ConnectionRequest req;
    
    public static boolean resultOk = true;
    
    public static ServiceCompetition getInstance(){
        if(instance == null)
            instance = new ServiceCompetition();
        return instance;
    }
    
    public ServiceCompetition(){
        req = new ConnectionRequest();
    }
    
    public void AjouterCompetition(competition competition){
        
        String url=ConnectionApi.BASE_URL+"competition/newJson?nomCompetition="+competition.getNomCompetition()
                +"&fraisCompetition="+competition.getFraisCompetition()
                +"&dateCompetition="+competition.getDateCompetition()
                +"&nbrMaxInscrit="+competition.getNbMaxInscrit()
                +"&etatCompetition="+competition.getEtatCompetition();
        
        req.setUrl(url);
        req.addResponseListener((e)->{
           
            String str = new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    //Affichage des Competitions
 public ArrayList<competition> AfficherCompetition() {
    ArrayList<competition> result = new ArrayList<>();
    String url = ConnectionApi.BASE_URL + "competition/CompetitionsJson";
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String,Object> mapCompetitions = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapCompetitions.get("root");

                for (Map<String, Object> obj : listOfMaps) {
                    competition c = new competition();

                    int id = ((Double) obj.get("id")).intValue();
                    String nomCompetition = obj.get("nomCompetition").toString();
                    int fraisCompetition = ((Double) obj.get("fraisCompetition")).intValue();
                    String dateCompetition = obj.get("dateCompetition").toString();
                    int nbrMaxInscrit = ((Double) obj.get("nbrMaxInscrit")).intValue();
                    String etatCompetition = obj.get("etatCompetition").toString();
                    String nbrParticipant = obj.get("nbrParticipant").toString();
                    int nbParticipant=(int)Double.parseDouble(nbrParticipant);

                    c.setIdCompetition((int) id);
                    c.setNomCompetition(nomCompetition);
                    c.setFraisCompetition((int) fraisCompetition);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
                    Date date = dateFormat.parse(dateCompetition);
                    c.setDateCompetition(date);
                    c.setNbMaxInscrit(nbrMaxInscrit);
                    c.setEtatCompetition(etatCompetition);
                    c.setNbrParticipant(String.valueOf(nbParticipant));
                    System.out.println(c.toString());
                      result.add(c);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);

    return result;
}


    //Detail Competition
    public competition DetailCompetition(int id, competition cmp){
        String url= ConnectionApi.BASE_URL+"competition/"+id+"/json/";
        
        req.setUrl(url);
        
        String str = new String(req.getResponseData());
req.addResponseListener((evt)->{
    JSONParser jsonp = new JSONParser();
    try{
        Map<String,Object> obj=jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
        
        cmp.setNomCompetition(obj.get("nomCompetition").toString());
        cmp.setFraisCompetition((int) Float.parseFloat(obj.get("fraisCompetition").toString()));
        cmp.setDateCompetition(new SimpleDateFormat("dd-MM-yyyy").parse(obj.get("dateCompetition").toString()));
        cmp.setNbMaxInscrit(Integer.parseInt(obj.get("nbrMaxInscrit").toString()));
        cmp.setEtatCompetition(obj.get("etatCompetition").toString());
        cmp.setNbrParticipant(String.valueOf(Integer.parseInt(obj.get("nbrParticipant").toString())));



        
        
    } catch(IOException ex){
        System.out.println("Error related to sql : { "+ex.getMessage());
    } catch(ParseException ex) {
        System.out.println("Error related to parsing : { "+ex.getMessage());
    }
    
    System.out.println("data = "+str);
});

        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return cmp;
    }
  
    
    //Suppression Competition
    public boolean SupprimerCompetition(int id ) {
        String url = ConnectionApi.BASE_URL +"competition/deleteJson/"+id;
        
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
   
    //Modification Competition
    public boolean ModifierCompetition(competition cmp) {
        String url = ConnectionApi.BASE_URL +"competition/editJson/"+cmp.getIdCompetition()+"?nomCompetition="+cmp.getNomCompetition()+
                "&fraisCompetition="+cmp.getFraisCompetition()+
                "&etatCompetition="+cmp.getEtatCompetition()+
                "&nbrMaxInscrit="+cmp.getNbMaxInscrit();
                          
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

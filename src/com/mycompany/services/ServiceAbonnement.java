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
import com.mycompany.entities.Abonnement;
import com.mycompany.utils.Statics;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author wiem
 */
public class ServiceAbonnement {

    public static ServiceAbonnement instance = null;

    private ConnectionRequest req;

    public static boolean resultOk = true;

    public static ServiceAbonnement getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnement();
        }
        return instance;
    }

    public ServiceAbonnement() {
        req = new ConnectionRequest();
    }

    public void AjouterAbonnement(Abonnement pack) throws ParseException {
        
       
            java.util.Date now = new java.util.Date();
            Calendar calendar = Calendar.getInstance();
            Date currentTime = calendar.getTime();
            String d = currentTime.toString();
           // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //Date date = format.parse(d);
            String url = Statics.BASE_URL + "abonnement/addAbonnementJson/new?dateAchat=" + currentTime
                    + "&dateFin=" + currentTime
                    + "&pack=" + 2
                    + "&etatAbonnement=" + pack.getEtatAbonnement()
                    + "&codePromo=" + pack.getCodePromo()
                    + "&montantAbonnement=" + pack.getMontantAbonnement();
            
            req.setUrl(url);
            req.addResponseListener((e) -> {
                
                String str = new String(req.getResponseData());
                System.out.println("data = " + str);
            });
            
            NetworkManager.getInstance().addToQueueAndWait(req);
       
       

    }

    public ArrayList<Abonnement> AfficherAbonnement() {
        ArrayList<Abonnement> result = new ArrayList<>();

        String url = Statics.BASE_URL + "abonnement/a";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;

                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapPacks = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapPacks.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Abonnement a = new Abonnement();

                        int id = 0;
                        try {
                            id = (int) Float.parseFloat(obj.get("id").toString());
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing ID: " + e.getMessage());
                        }

                        String dateAchat = obj.get("dateAchat").toString();
                        String dateFin = obj.get("dateFin").toString();
                        String etatAbonnement = obj.get("etatAbonnement").toString();

                        double m = 0.0;
                        /* try {
                            m = Double.parseDouble(obj.get("montantAbonnement").toString());
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing montantPack: " + e.getMessage());
                        }*/
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateA = dateFormat.parse(dateAchat);
                        Date dateF = dateFormat.parse(dateFin);
                        a.setId((int) id);
                        a.setDateAchat((Date) dateA);
                        a.setDateFin((Date) dateF);
                        a.setEtatAbonnement(etatAbonnement);
                        a.setMontantAbonnement((double) m);
                        result.add(a);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }

    public boolean SupprimerAbonnement(int id) {
        String url = Statics.BASE_URL + "abonnement/deleteAbonnementJson/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public boolean ModifierAbonnement(Abonnement act) {
        String url = Statics.BASE_URL + "abonnement/updateAbonnementJson/" + act.getId() + "?dateAchat=" + act.getDateAchat()
                + "&dateFin=" + act.getDateFin()
                + "&etatAbonnement=" + act.getEtatAbonnement()
                + "&codePromo=" + act.getCodePromo()
                + "&montantAbonnement=" + act.getMontantAbonnement();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }
}

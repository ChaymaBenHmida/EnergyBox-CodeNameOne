/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycompany.entities.User;
import com.mycompany.utils.ConnectionApi;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.company.gui.ListUsers;
import com.company.gui.SignUp;
import com.mycompany.piMobile.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;

/**
 *
 * @author maham
 */
public class ServiceUtilisateur {

    //singleton 
    public static ServiceUtilisateur instance = null;

    public boolean resultOK;
    String json;
    public ArrayList<User> users;
    public static boolean kifeh;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceUtilisateur getInstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }

    public ServiceUtilisateur() {
        req = new ConnectionRequest();

    }

    //Signup
    public boolean Register(User u) {
        //http://127.0.0.1:8000/signup?username=mahmah&numTel=20202020&email=mahmah@gmail.com&password=123456789&image=mm.png#trace-box-1
        //http:/ /1 27.0.0.1:8000/signup?email=tettttst@gmail.com&alias=sfgs&nom=rgdgh& prenom=fqqq&dateNais=2022&region=azer&phone=1235&pass=azertyu&image=ghj
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = ConnectionApi.BASE_URL + "signup?username=" + u.getUsername() + "&numTel=" + u.getNumTel() + "&email=" + u.getEmail() + "&password=" + u.getPassword() + "&image=" + u.getPhoto();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean singin(String email, String password) {

        String url = ConnectionApi.BASE_URL + "signin?email=" + email + "&password=" + password;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                String reponse = new String(req.getResponseData());
                System.out.println(reponse);
                String result = reponse.substring(1, reponse.length() - 1);
                if (result.equalsIgnoreCase("invalid")) {
                    kifeh = false;
                } else {
                    MyApplication.modifIdU(email);
                    System.out.println(MyApplication.idU);
                    kifeh = true;

                }

            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return kifeh;
    }

    public ArrayList<User> AfficherUser() {
        ArrayList<User> result = new ArrayList<>();

        String url = ConnectionApi.BASE_URL + "All";
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;

                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapUsers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUsers.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        User a = new User();

                        float id = Float.parseFloat(obj.get("id").toString());
                        String email = obj.get("email").toString();
                        String username = obj.get("username").toString();
                        a.setId((int) id);
                        a.setEmail(email);
                        a.setUsername(username);

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
    //Suppression Cours

    public boolean SupprimerUsers(int id) {
        String url = ConnectionApi.BASE_URL + id + "/deletemobile";

        req.setUrl(url);
        //req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean ModifierUser(User u) {
        String url = ConnectionApi.BASE_URL + u.getId() + "/editAdminMobile?username=" + u.getUsername() + "&email=" + u.getEmail();
        req.setUrl(url);
        //   req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOK;

    }

}

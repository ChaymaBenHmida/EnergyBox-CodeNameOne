package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Pack;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author azizo
 */
public class ServicePack {

    //singleton
    public static ServicePack instance = null;

    private ConnectionRequest req;

    public static boolean resultOk = true;

    public static ServicePack getInstance() {
        if (instance == null) {
            instance = new ServicePack();
        }
        return instance;
    }

    public ServicePack() {
        req = new ConnectionRequest();
    }

    public void AjouterPack(Pack pack) {

        String url = Statics.BASE_URL + "pack/addPackJson/new?typePack=" + pack.getTypePack()
                + "&montantPack=" + pack.getMontantPack()
                + "&dureePack=" + pack.getDureePack()
                + "&descriptionPack=" + pack.getDescriptionPack()
                + "&placesPack=" + pack.getPlacesPack()
                + "&disponibilitePack=" + pack.getDisponibilitePack();

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data = " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    //Affichage des Activites
    public ArrayList<Pack> AfficherPack() {
        ArrayList<Pack> result = new ArrayList<>();

        String url = Statics.BASE_URL + "pack/listp";
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
                        Pack a = new Pack();

                        int id = 0;
                        try {
                            id = (int) Float.parseFloat(obj.get("id").toString());
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing ID: " + e.getMessage());
                        }

                        String typePack = obj.get("typePack").toString();

                        double montantPack = 0.0;
                        try {
                            montantPack = Double.parseDouble(obj.get("montantPack").toString());
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing montantPack: " + e.getMessage());
                        }

                        double d = 1;
                        try {
                            if (obj.containsKey("dureePack")) {
                                d = Double.parseDouble(obj.get("dureePack").toString());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing dureePack: " + e.getMessage());
                        }

                        String descriptionPack = obj.get("descriptionPack").toString();

                        double placesPack = 9;
                        try {
                            if (obj.containsKey("placesPack")) {
                                placesPack = Double.parseDouble(obj.get("placesPack").toString());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing placesPack: " + e.getMessage());
                        }

                        double disponibilitePack = 9;
                        try {
                            if (obj.containsKey("disponibilitePack")) {
                                disponibilitePack = Double.parseDouble(obj.get("disponibilitePack").toString());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing disponibilitePack: " + e.getMessage());
                        }

                        a.setId((int)id);
                        a.setTypePack(typePack);
                        a.setMontantPack(montantPack);
                        a.setDureePack((int)d);
                        a.setDescriptionPack(descriptionPack);
                        a.setPlacesPack((int)placesPack);
                        a.setDisponibilitePack((int)disponibilitePack);

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


    //Suppression pack
    public boolean SupprimerPack(int id) {
        String url = Statics.BASE_URL + "pack/deletePackJson/" + id;

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

    //Modification pack
    public boolean ModifierPack(Pack act) {
        String url = Statics.BASE_URL + "pack/updatePackJson/" + act.getId() + "?typePack=" + act.getTypePack()
                + "&montantPack=" + act.getMontantPack()
                + "&dureePack=" + act.getDureePack()
                + "&descriptionPack=" + act.getDescriptionPack()
                + "&placesPack=" + act.getPlacesPack()
                + "&disponibilitePack=" + act.getDisponibilitePack();
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

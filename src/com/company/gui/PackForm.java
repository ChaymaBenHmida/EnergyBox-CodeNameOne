/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.Pack;
import com.mycompany.services.ServicePack;


/**
 *
 * @author azizo
 */
public class PackForm extends Form {

    Form current;

    public PackForm() {

        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);

        tb.setTitle("Ajouter Pack");
        getContentPane().setScrollVisible(true);

        tb.addSearchCommand(e -> {

        });

        /*AJOUT*/
        Container addContainer = new Container(new FlowLayout(CENTER, CENTER));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button ListBtn = new Button("Consulter Liste");
        Button AjouterBtn = new Button("Ajouter");

        TextField typePack = new TextField("", "Type Pack");
        TextField montantPack = new TextField("", "montant Pack");
        TextField dureePack = new TextField("", "duree Pack");
        TextField descriptionPack = new TextField("", " description Pack");
        TextField placesPack = new TextField("", "places Pack");
        TextField disponibilitePack = new TextField("", "disponibilite Pack");

        addContainer.add(typePack);
        addContainer.add(montantPack);
        addContainer.add(dureePack);
        addContainer.add(descriptionPack);
        addContainer.add(placesPack);
        addContainer.add(disponibilitePack);

        btnContainer.add(AjouterBtn);
        btnContainer.add(ListBtn);
        btnContainer.setLayout(BoxLayout.y());

        AjouterBtn.addActionListener((e) -> {
            try {
                if (typePack.getText().equals("") || montantPack.getText().equals("") || dureePack.getText().equals("")
                        || disponibilitePack.getText().equals("")
                        || descriptionPack.getText().equals("") || placesPack.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data

                    final Dialog iDialog = ip.showInfiniteBlocking();

                    //njibo iduser men session (current user)
                    Pack a = new Pack(
                            31,
                            String.valueOf(typePack.getText()),
                            Float.parseFloat(montantPack.getText()),
                            Integer.parseInt(dureePack.getText()),
                            String.valueOf(descriptionPack.getText()),
                            Integer.parseInt(placesPack.getText()),
                            Integer.parseInt(disponibilitePack.getText())
                    );

                    System.out.println("data Pack == " + a);

                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    ServicePack.getInstance().AjouterPack(a);

                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    refreshTheme();//Actualisation
                    Dialog.show("EnergyBox", "Ajout avec Succés", "Annuler", "OK");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ListBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new PackViewForm().show();
            }
        });

        add(addContainer);
        add(btnContainer);

        /*FIN AJOUT*/
 /*NAVBAR*/
        getToolbar()
                .addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new HomeForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Packs", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PackForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Abonnements", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new AbonnementViewForm().show();
                    }
                });
       

    }

}

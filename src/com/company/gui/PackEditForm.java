/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Pack;
import com.mycompany.services.ServicePack;



/**
 *
 * @author azizo
 */
public class PackEditForm extends Form {

    public PackEditForm(Pack a ) {

        Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container CnBtn = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button btnModifier = new Button("Modifier");
        Button btnAnnuler = new Button("Annuler");

        TextField tp = new TextField(a.getTypePack(), "typePack", 20, TextField.ANY);
        TextField mp = new TextField(String.valueOf(a.getMontantPack()), " montant Pack", 20, TextField.ANY);
        TextField dp = new TextField(String.valueOf(a.getDureePack()), "duree Pack", 20, TextField.ANY);
        TextField dcp = new TextField(String.valueOf(a.getDescriptionPack()), " description Pack", 20, TextField.ANY);
        TextField pp = new TextField(String.valueOf(a.getPlacesPack()), "places Pack", 20, TextField.ANY);
        TextField dnp = new TextField(String.valueOf(a.getDisponibilitePack()), " disponibilite Pack", 20, TextField.ANY);

        cn.add(tp);
        cn.add(mp);
        cn.add(dp);
        cn.add(dcp);
        cn.add(pp);
        cn.add(dnp);
        CnBtn.add(btnModifier);
        CnBtn.add(btnAnnuler);
        btnModifier.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                a.setTypePack(tp.getText());
                a.setMontantPack(Double.parseDouble(mp.getText()));
                a.setDureePack(Integer.parseInt(dp.getText()));
                a.setDescriptionPack(dcp.getText());
                a.setPlacesPack(Integer.parseInt(pp.getText()));
                a.setDisponibilitePack(Integer.parseInt(dnp.getText()));
                if (ServicePack.getInstance().ModifierPack(a)) { // if true
                    new PackViewForm().show();
                }
            }
        });

        btnAnnuler.addActionListener(e -> {
            new PackViewForm().show();
        });

        add(cn);
        add(CnBtn);
    }
}

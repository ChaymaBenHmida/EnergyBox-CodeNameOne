/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.Pack;
import com.mycompany.services.ServicePack;
import java.util.ArrayList;

/**
 *
 * @author azizo
 */
public class PackViewForm extends Form {

    Form current;

    public PackViewForm() {
        /*VIEW*/
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);

        tb.setTitle("Afficher Packs");
        getContentPane().setScrollVisible(true);

        tb.addSearchCommand(e -> {

        });
        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
              
        ArrayList<Pack> list = ServicePack.getInstance().AfficherPack();

        for (Pack p : list) {

            Label tp = new Label("Type Pack : " + p.getTypePack());
            Label mp = new Label("montantPack : " + p.getMontantPack());
            Label dp = new Label("duree Pack : " + p.getDureePack());
            Label dcp = new Label("DescriptionPack: " + p.getDescriptionPack());
            Label pp = new Label("placesPack : " + p.getPlacesPack());
            Label dbnp = new Label("disponibilite Packe : " + p.getDisponibilitePack());
            Label separator = new Label("**************************");

            Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            cn.add(tp);
            cn.add(mp);
            cn.add(dp);
            cn.add(dcp);
            cn.add(pp);
            cn.add(dbnp);
            cn.add(separator);

            Label ActSupprimer = new Label(" ");
            ActSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(ActSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            ActSupprimer.setIcon(suprrimerImage);
            ActSupprimer.setTextPosition(RIGHT);

            //click delete icon
            ActSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if (dig.show("Suppression", "Vous voulez supprimer ce Pack ?", "Annuler", "Oui")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }
                //n3ayto l suuprimer men service Reclamation
                if (ServicePack.getInstance().SupprimerPack(p.getId())) {
                    new PackViewForm().show();
                }

            });

            //Update icon 
            Label ActModifier = new Label(" ");
            ActModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(ActModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);

            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            ActModifier.setIcon(mFontImage);
            ActModifier.setTextPosition(LEFT);

            ActModifier.addPointerPressedListener(l -> {
                new PackEditForm(p).show();
            });

            cn.add(ActSupprimer);
            cn.add(ActModifier);

            add(cn);
        }

        add(viewContainer);

        getToolbar().addCommandToLeftBar("Back", null, (ActionListener) (ActionEvent evt1) -> {
            new PackForm().showBack();
        });

        /*FIN VIEW*/
    }
}

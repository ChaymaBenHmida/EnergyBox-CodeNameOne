/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUtilisateur;
import java.util.ArrayList;

/**
 *
 * @author maham
 */
public class ListUsers extends Form {

    Form current;

    public ListUsers() {
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);

        tb.setTitle("Afficher Users");
        getContentPane().setScrollVisible(true);

        tb.addSearchCommand(e -> {

        });

        Container viewContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<User> list = new ArrayList<>();
        list = ServiceUtilisateur.getInstance().AfficherUser();

        for (User c : list) {
            Label id = new Label("id : " + c.getId());

            Label email = new Label("email : " + c.getEmail());
            Label username = new Label("Username : " + c.getUsername());

            Label separator = new Label("**************************");

            Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cn.add(id);
            cn.add(email);
            cn.add(username);

            Label CoursSupprimer = new Label(" ");
            CoursSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(CoursSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            CoursSupprimer.setIcon(suprrimerImage);
            CoursSupprimer.setTextPosition(RIGHT);

            //click delete icon
            CoursSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if (dig.show("Suppression", "Vous voulez supprimer ce user ?", "Annuler", "Oui")) {
                    dig.dispose();
                }
                //n3ayto l suuprimer men service Reclamation
                if (ServiceUtilisateur.getInstance().SupprimerUsers(c.getId())) {
                    new ListUsers().show();
                }
            });
            //Update icon 
            Label CoursModifier = new Label(" ");
            CoursModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(CoursModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);

            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            CoursModifier.setIcon(mFontImage);
            CoursModifier.setTextPosition(LEFT);

            CoursModifier.addPointerPressedListener(l -> {
                new UserEditForm(c).show();
            });
            cn.add(CoursSupprimer);
            cn.add(CoursModifier);
            cn.add(separator);

            add(cn);
        }

        add(viewContainer);

//        getToolbar().addCommandToLeftBar("Back",null,(ActionListener) (ActionEvent evt1) -> {
//                new CoursForm().showBack();
//        });
        /*FIN VIEW*/
    }

    ListUsers(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

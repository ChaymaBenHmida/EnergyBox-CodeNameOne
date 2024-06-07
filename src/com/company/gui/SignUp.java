/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.FloatingHint;
//import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUtilisateur;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author maham
 */
public class SignUp extends Form {

    public SignUp(Form previous) {
        setTitle("RegistrationForm Admin");
        setLayout(BoxLayout.y());

        TextField tfusername = new TextField("", "username");
        TextField tfnumTel = new TextField("", "num_tel");
        TextField tfemail = new TextField("", "email");
        TextField tfpassword = new TextField("", "password");
        TextField tfimage = new TextField("", "image");

        Button btnValider = new Button("Valider");
        Button btnLogin = new Button("Login");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfusername.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    User t = new User(tfusername.getText(), Integer.parseInt(tfnumTel.getText()), tfemail.getText(), tfpassword.getText(), tfimage.getText());
                    if (ServiceUtilisateur.getInstance().Register(t)) {
                        Dialog.show("Success", "User ajouté ", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }

                }

            }
        });
        //btnLogin.addActionListener(e -> newsignin(Email, password).show());

        addAll(tfusername, tfnumTel, tfemail, tfpassword, tfimage, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}

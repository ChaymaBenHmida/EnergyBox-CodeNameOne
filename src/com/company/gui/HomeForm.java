/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author azizo
 */
public class HomeForm extends Form {
Resources theme;
    public HomeForm() {
        
 theme = UIManager.initFirstTheme("/theme");
        Container homeContainer = new Container(new FlowLayout(CENTER, CENTER));
        Container btnContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Button activite = new Button("Activites");
        Button cours = new Button("Cours");
        Button planning = new Button("Planning");
        Button Abonnement = new Button("Abonnement");
        Button Pack = new Button("Pack");
        Button competition = new Button("Compétition");
        Button ticket = new Button("Ticket");
        Button blog = new Button("Blog");
        Button plats = new Button("Plats");

        setLayout(BoxLayout.y());
        setTitle("EnergyBox | CrossFitCenter");

        Label welcome = new Label("Welcome To EnergyBox");

        activite.addActionListener(e -> new ActiviteForm().show());
        cours.addActionListener(e -> new CoursForm().show());
        planning.addActionListener(e -> new PlanningForm().show());
        Abonnement.addActionListener(e -> new AbonnementViewForm().show());
        Pack.addActionListener(e -> new PackForm().show());
        competition.addActionListener(e -> new CompetitionForm().show());
        ticket.addActionListener(e -> new TicketForm().show());
        blog.addActionListener(e -> new BlogForm().show());
        plats.addActionListener(e -> new PlatList(UIManager.initFirstTheme("/theme1")).show());
        homeContainer.add(welcome);

        btnContainer.add(activite);
        btnContainer.add(cours);
        btnContainer.add(planning);
        btnContainer.add(Abonnement);
        btnContainer.add(Pack);
        btnContainer.add(competition);
        btnContainer.add(ticket);
        btnContainer.add(blog);
         btnContainer.add(plats);

        add(homeContainer);
        add(btnContainer);

        getToolbar()
                .addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new HomeForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Utilisateur", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PlanningForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Activités", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new ActiviteForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Cours", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new CoursForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Planning", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PlanningForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Abonnement", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new AbonnementViewForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Pack", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PackForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Compétition", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new CompetitionForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Ticket", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new TicketForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Matériaux", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PlanningForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Charge", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PlanningForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Fournisseur", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PlanningForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Menu", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PlanningForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Plat", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PlanningForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Réservation", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PlanningForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Sponsor", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new PlanningForm().show();
                    }
                });
        getToolbar()
                .addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_CALL_RECEIVED, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        new BlogForm().show();
                    }
                });

    }

}

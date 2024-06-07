/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

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
import com.mycompany.entities.competition;
import com.mycompany.services.ServiceCompetition;
import java.util.ArrayList;

/**
 *
 * @author Salima
 */
public class CompetitionViewForm extends Form {    
    Form current;
    public CompetitionViewForm(){
        /*VIEW*/
        current = this;
        Toolbar tb = new Toolbar(false);
        current.setToolbar(tb);
        
        tb.setTitle("Afficher Competitions");
        getContentPane().setScrollVisible(true);
        
               /*TextField searchTF = new TextField("", "Search ..", 20, TextField.ANY);
        searchTF.getUnselectedStyle().setFgColor(0x000000);
        add(searchTF);*/
        
        
        ArrayList<competition>list = ServiceCompetition.getInstance().AfficherCompetition();
        
        for(competition cmp : list ) {
            
            Label nomCmpTxt = new Label("Nom Competition : "+cmp.getNomCompetition());
            Label fraisCmpTxt = new Label("Frais Competition : "+cmp.getFraisCompetition());
            Label dateCmpTxt = new Label("Date Competition : "+cmp.getDateCompetition());
            Label nbMaxInTxt = new Label("Nombre Max D'inscrit : "+cmp.getNbMaxInscrit());
            Label etatCmpTxt = new Label("Etat De Competition : "+cmp.getEtatCompetition());
            Label nbPcpCmpTxt = new Label("Nombre De Participants : "+cmp.getNbrParticipant());
            Label separator = new Label("**************************");
            
            Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            cn.add(nomCmpTxt);
            cn.add(fraisCmpTxt);
            cn.add(dateCmpTxt);
            cn.add(nbMaxInTxt);
            cn.add(etatCmpTxt);
            cn.add(nbPcpCmpTxt);
            
           
            Label cmpSupprimer = new Label(" ");
            cmpSupprimer.setUIID("NewsTopLine");
            Style supprmierStyle = new Style(cmpSupprimer.getUnselectedStyle());
            supprmierStyle.setFgColor(0xf21f1f);

            FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
            cmpSupprimer.setIcon(suprrimerImage);
            cmpSupprimer.setTextPosition(RIGHT);

            //click delete icon
            cmpSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Suppression");

                if(dig.show("Suppression","Vous voulez supprimer cette Competition ?","Annuler","Oui")) {
                    dig.dispose();
                }
                else {
                    dig.dispose();
                     }
                   
                    if(ServiceCompetition.getInstance().SupprimerCompetition(cmp.getIdCompetition())) {
                        new CompetitionViewForm().show();
                    }

            });
            
            //Update icon 
            Label cmpModifier = new Label(" ");
            cmpModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(cmpModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);

            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            cmpModifier.setIcon(mFontImage);
            cmpModifier.setTextPosition(LEFT);


            cmpModifier.addPointerPressedListener(l -> {
                new CompetitionEditForm(cmp).show();
            });


            cn.add(cmpSupprimer);
            cn.add(cmpModifier);
            cn.add(separator);

            add(cn);
        }
        
        
        
        
        
        getToolbar().addCommandToLeftBar("Back",null,(ActionListener) (ActionEvent evt1) -> {
                new CompetitionForm().showBack();
        });
        
        /*FIN VIEW*/
    }
    
         /*   searchTF.addDataChangedListener((type, index) -> {
            String filterText = searchTF.getText();
            list.clear();
            for (competition cmp : list) {
                if (cmp.getNomCompetition().toLowerCase().contains(filterText.toLowerCase())) {
                    produitsTemp.add(pro);
                }
            }
            produitsContainer.removeAll();
            for (cours rec : produitsTemp) {

                String urlImage = "back-logo.jpg";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 

                Image placeHolder = Image.createImage(120, 90);
                EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
                URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);

                int height = Display.getInstance().convertToPixels(11.5f);
                int width = Display.getInstance().convertToPixels(14f);

                Button image = new Button(urlim.fill(width, height));
                image.setUIID("Label");
                Container oneCont = BorderLayout.west(image);

                //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
               Label nomTxt = new Label("nom : " + rec.getNom_cours(), "NewsTopLine2");
            
            Label descp_pTxt = new Label("descp_p : " + rec.getActivite(), "NewsTopLine2");

                createLineSeparator();

                //supprimer button
                Label lSupprimer = new Label(" ");
                lSupprimer.setUIID("NewsTopLine");
                Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
                supprmierStyle.setFgColor(0xf21f1f);

                FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
                lSupprimer.setIcon(suprrimerImage);
                lSupprimer.setTextPosition(RIGHT);

                //click delete icon
                lSupprimer.addPointerPressedListener(l -> {
                    Dialog dig = new Dialog("Suppression");
                    if (dig.show("Suppression", "Vous voulez supprimer ce produit ?", "Annuler", "Oui")) {
                        dig.dispose();
                    } else {
                        dig.dispose();
                    }
                    //delete product using service
                    boolean success = coursServices.getInstance().deleteCours(rec.getId());
                    if (success) {
                        Dialog.show("Suppression", "Produit supprimé avec succès", "OK", null);
                        new ListCoursForm(res).show();
                    } else {
                        Dialog.show("Erreur", "Erreur lors de la suppression du produit", "OK", null);
                    }
                });

                //Update icon 
                Label lModifier = new Label(" ");
                lModifier.setUIID("NewsTopLine");
                Style modifierStyle = new Style(lModifier.getUnselectedStyle());
                modifierStyle.setFgColor(0xf7ad02);

                FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
                lModifier.setIcon(mFontImage);
                lModifier.setTextPosition(LEFT);

                lModifier.addPointerPressedListener(l -> {
                    //System.out.println("hello update");
                    new EditCoursForm(res, rec).show();
                });
                //Update icon 
                Label lModifie = new Label(" ");
                lModifie.setUIID("NewsTopLine");
                Style modifierStyl = new Style(lModifie.getUnselectedStyle());
                modifierStyl.setFgColor(0xf7ad02);

                FontImage mFontImae = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyl);
                lModifie.setIcon(mFontImae);
                lModifie.setTextPosition(LEFT);

                lModifie.addPointerPressedListener(l -> {
                    //System.out.println("hello update");
                    new recSearch(res).show();

                });

                oneCont.add(BorderLayout.CENTER, BoxLayout.encloseY(
                        BoxLayout.encloseX(nomTxt),
                
                BoxLayout.encloseX(descp_pTxt),
                        BoxLayout.encloseX(lModifier, lSupprimer, lModifie)));

                ScaleImageLabel imag = new ScaleImageLabel(urlim);

                Container containerImg = new Container();

                imag.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                produitsContainer.add(oneCont);
            }
            revalidate();
        });
*/
}

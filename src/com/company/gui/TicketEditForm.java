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
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Ticket;
import com.mycompany.services.ServiceTicket;

/**
 *
 * @author Salima
 */
public class TicketEditForm extends Form{
    
    public TicketEditForm(Ticket t){
        
        Container cn=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container CnBtn=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button btnModifier=new Button("Modifier");
        Button btnAnnuler = new Button("Annuler");
        
        TextField Desc = new TextField(t.getDescription() , "Description Ticket" , 20 , TextField.ANY);

        cn.add(Desc);
        CnBtn.add(btnModifier);
        CnBtn.add(btnAnnuler);
        
btnModifier.addPointerPressedListener(l ->   { 
    t.setDescription(Desc.getText());
     if(ServiceTicket.getInstance().ModifierTicket(t)) { // if true
        new TicketViewForm().show();
    }
});  

btnAnnuler.addPointerPressedListener(l ->   { 
        new TicketViewForm().show();
    
}); 
       add(cn);
       add(CnBtn);       
    }
}
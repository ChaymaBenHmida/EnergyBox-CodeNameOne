package com.mycompany.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.mycompany.entities.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Eya Krichene
 */
public class CommentServices {
     public ArrayList<Comment> comments;
    
      public ArrayList<Comment> parseCom(String jsonText) {
    try {
        comments = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> tasksListJson =
                j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        ArrayList<Map<String, Object>> list = null;
        if (tasksListJson.containsKey("root")) {
            Object root = tasksListJson.get("root");
            if (root instanceof ArrayList) {
                list = (ArrayList<Map<String, Object>>) root;
            }
        }

        if (list != null) {
            for (Map<String, Object> obj : list) {
                Comment t = new Comment();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setDescription(obj.get("description").toString());
                t.setDateComment(obj.get("dateCom").toString());
                
                comments.add(t);

            }
        } else {
            System.out.println("Invalid JSON format or missing 'root' array.");
        }

    } catch (IOException | NumberFormatException ex) {
        System.out.println("Error while parsing JSON: " + ex.getMessage());
    }

    return comments;
}
}

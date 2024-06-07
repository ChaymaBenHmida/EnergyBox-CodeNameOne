package com.mycompany.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.mycompany.entities.Post;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Eya Krichene
 */
public class PostServices {
    
     public ArrayList<Post> posts;
    
      public ArrayList<Post> parsePost(String jsonText) {
    try {
        posts = new ArrayList<>();
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
                Post t = new Post();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setTitle(obj.get("title").toString());
                t.setDetails(obj.get("details").toString());
                t.setImage(obj.get("image").toString());
                t.setDatePost(obj.get("datePost").toString());              

                posts.add(t);

            }
        } else {
            System.out.println("Invalid JSON format or missing 'root' array.");
        }

    } catch (IOException | NumberFormatException ex) {
        System.out.println("Error while parsing JSON: " + ex.getMessage());
    }

    return posts;
}
}

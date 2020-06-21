package com.example.hello;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class FirebasePost {
    public int amount;
    public String category;
    public int id;
    public String ingredients;
    public String name;
    public String FirebasePost;

    /**
     * @param ID
     * @param name
     * @param id
     * @param amount
     * @param category
     * @param ingredients
     */
    public FirebasePost(String ID, String name, String id, int amount, String category, String ingredients){
        // Default constructor required for calls to DataSnapshot.getValue(FirebasePost.class)
    }

    public FirebasePost(String category) {
        this.amount = amount;
        this.category = category;
        this.id = id;
        this.ingredients = ingredients;
        this.name = name;
    }

    public FirebasePost(int id, String name, String ingredients, int amount, String category, String sort) {

    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>(); // hashmap:데이터를 담는 그릇 역할
        result.put("amount", amount);
        result.put("category", category);
        result.put("id", id);
        result.put("ingredients", ingredients);
        result.put("name", name);
        return result;
    }
}
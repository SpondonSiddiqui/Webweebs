/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.utils;

/**
 *
 * @author jespe
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import model.entity.*;
import com.fasterxml.jackson.databind.*;
import java.util.Iterator;

public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
  
  public static List<Actor> getActorsFromMovieCreditsUrl(String url) throws IOException, JSONException{
      JSONObject json = readJsonFromUrl(url);
      List<Actor> actors = new ArrayList<>();
      
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode tree = objectMapper.readTree(json.toString());
      JsonNode paths = tree.get("results");
      
      Iterator<JsonNode> fields = paths.elements();
      while(fields.hasNext()){
          JsonNode field = fields.next();
          if(!field.has("character")) continue; //Checks if the person is an actor in the movie or not
          
          actors.add(getActorFromNode(field));
      }
      return actors;
  }
    /**
     * Reads an url and returns the actor (or any person) given by the url
     * @param url 
     * @return
     * @throws IOException
     * @throws JSONException 
     */
    public static Actor getActorFromUrl(String url) throws IOException, JSONException{
        JSONObject json = readJsonFromUrl(url);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode field = objectMapper.readTree(json.toString());
        
        return getActorFromNode(field);
  }
  
    public static Actor getDirectorFromUrl (String url) throws IOException, JSONException {
      JSONObject json = readJsonFromUrl(url);
      Actor emptyActor = new Actor("COuld not find Director","","","","","");
      
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode tree = objectMapper.readTree(json.toString());
      JsonNode paths = tree.get("results");
      
      Iterator<JsonNode> fields = paths.elements();
      while(fields.hasNext()){
          JsonNode field = fields.next();
          if(!field.has("department") || !field.findValue("department").asText().equals("Directing")) continue; //Checks if the person is a director or not. If not, continue
          
          return getActorFromNode(field);
      }
      return emptyActor;
    }
  
  public static List<Movie> getMoviesFromUrl(String url) throws IOException, JSONException {
      JSONObject json = readJsonFromUrl(url);
      List<Movie> movies = new ArrayList<>();
      
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode tree = objectMapper.readTree(json.toString());
      JsonNode paths = tree.get("results");
      
      Iterator<JsonNode> fields = paths.elements();
      while(fields.hasNext()){
           
            JsonNode field = fields.next();
            
            movies.add(getMovieFromNode(field));      
      }
      return movies;
      
  }
  
  public static Movie getMovieFromUrl(String url) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl(url);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode field = objectMapper.readTree(json.toString());

        return getMovieFromNode(field);
  }
  
  private static Movie getMovieFromNode(JsonNode field){
      
        String title;
        String avg_rating;
        String overview;
        String release_date;
        String poster_path;
        String id;

        if(field.has("title")){
            title = field.findValue("title").asText();
        } else{
            title = "Could not find title";
        }
        if(field.has("vote_average")){
             avg_rating = field.findValue("vote_average").asText();
        } else{
             avg_rating = "Could not find vote_average";
        }
        if(field.has("overview")){
             overview = field.findValue("overview").asText();
        } else{
             overview = "Could not find overview";
        }
        if(field.has("release_date")){
             release_date = field.findValue("release_date").asText();
        } else{
             release_date = "Could not find release_date";
        }
        if(field.has("poster_path")){
             poster_path = field.findValue("poster_path").asText();
        } else{
             poster_path = "Could not find poster_path";
        }
        if(field.has("id")){
             id = field.findValue("id").asText();
        } else{
             id = "Could not find id";
        }                  

        Movie movie = new Movie(
                title
                ,avg_rating
                ,overview
                ,release_date
                ,poster_path
                ,id
        );
        
        return movie;
  }
  
  private static Actor getActorFromNode(JsonNode field){
      
        String name;
        String birthday;
        String deathday;
        String bio;
        String id;
        String pic_path;

        if(field.has("name")){
            name = field.findValue("name").asText();
        } else{
            name = "Could not find name";
        }
        if(field.has("birthday")){
            birthday = field.findValue("birthday").asText();
        } else {
            birthday = "Could not find birthday";
        }
        if(field.has("deathday")){
            deathday = field.findValue("deathday").asText();
        } else {
            deathday = "Could not find deathday";
        }
        if(field.has("biography")){
            bio = field.findValue("biography").asText();
        } else {
            bio = "Could not find biography";
        }
        if(field.has("id")){
            id = field.findValue("id").asText();
        } else{
            id = "Could not find id";
        }
        if(field.has("profile_path")){
            pic_path = field.findValue("profile_path").asText();
        } else {
            pic_path = "Could not find profile picture";
        }

        Actor actor = new Actor(
              name
              ,birthday
              ,deathday
              ,bio
              ,id
              ,pic_path
          );
        
        return actor;
  }
  
  private static List<String> getIdFromNode(JsonNode movieField){
      List<String> ids = new ArrayList<>();
      return ids;
  }
 

}

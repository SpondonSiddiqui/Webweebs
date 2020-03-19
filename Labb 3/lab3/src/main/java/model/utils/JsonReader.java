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
  
  /**
   * Get a list of all actors featured in a movie (chosen by id).
   * @param url Url to list of all people who worked on a movie
   * @return List of all featured actors
   * @throws IOException
   * @throws JSONException 
   */
  public static List<Actor> getActorsFromMovieCreditsUrl(String url) throws IOException, JSONException{
      JSONObject json = readJsonFromUrl(url);
      List<Actor> actors = new ArrayList<>();
      
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode tree = objectMapper.readTree(json.toString());
      JsonNode paths = tree.get("cast");
      
      Iterator<JsonNode> fields = paths.elements();
      while(fields.hasNext()){
          JsonNode field = fields.next();
          actors.add(getActorFromUrl("https://api.themoviedb.org/3/person/"+field.get("id").asText()+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US"));
      }
      return actors;
  }
  
  /**
   * Get actors from url (Such as search results)
   * @param url Url to list of actors
   * @return List of actors
   * @throws IOException
   * @throws JSONException 
   */
  public static List<Actor> getActorsFromUrl(String url)  throws IOException, JSONException{
        JSONObject json = readJsonFromUrl(url);
      List<Actor> actors = new ArrayList<>();
      
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode tree = objectMapper.readTree(json.toString());
      JsonNode paths = tree.get("results");
      
      Iterator<JsonNode> fields = paths.elements();
      while(fields.hasNext()){
          JsonNode field = fields.next();
          
          actors.add(getActorFromNode(field));
      }
      return actors;
  }
  
  /**
   * Get movies in which the actor (or general person) has starred in/been involved with.
   * @param url Url to Actor
   * @return List of movies
   * @throws IOException
   * @throws JSONException 
   */
  public static List<Movie> getMoviesFromActorUrl(String url, String id) throws IOException, JSONException{
        JSONObject json = readJsonFromUrl(url);
      List<Movie> movies = new ArrayList<>();
      
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode tree = objectMapper.readTree(json.toString());
      JsonNode paths = tree.get("results");
      
      Iterator<JsonNode> fields = paths.elements();
      while(fields.hasNext()){
           
            JsonNode field = fields.next();
            if(field.get("id").asText().equals(id)){
                    JsonNode newPaths = field.get("known_for");
      
                    Iterator<JsonNode> newFields = newPaths.elements();
                    while(newFields.hasNext()){
                        JsonNode movieField = newFields.next();
                        movies.add(getMovieFromNode(field)); 
                    }
                    return movies;
            }  
      }
      return movies;
  }
  
    /**
     * Reads an url and returns the actor (or any person) (chosen by id) given by the url
     * @param url Url with actor-id
     * @return The actor
     * @throws IOException
     * @throws JSONException 
     */
    public static Actor getActorFromUrl(String url) throws IOException, JSONException{
        JSONObject json = readJsonFromUrl(url);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode field = objectMapper.readTree(json.toString());
        
        return getActorFromNode(field);
  }
  /**
   * Get the director(s) of a movie (chosen by id)
   * @param url Url to list of all people who worked on a movie
   * @return The director as a actor
   * @throws IOException
   * @throws JSONException 
   */
    public static Actor getDirectorFromUrl (String url) throws IOException, JSONException {
      JSONObject json = readJsonFromUrl(url);
      Actor emptyActor = new Actor("Could not find Director","","","","","");
      
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode tree = objectMapper.readTree(json.toString());
      JsonNode paths = tree.get("crew");
      
      Iterator<JsonNode> fields = paths.elements();
      while(fields.hasNext()){
          JsonNode field = fields.next();
          if(!field.get("department").asText().equals("Directing")) continue; //Checks if the person is a director or not. If not, continue
       
          return getActorFromUrl("https://api.themoviedb.org/3/person/"+field.get("id").asText()+"?api_key=10dfedc564f5b41f3c803582d1d3a5fa&language=en-US");
      }
      return emptyActor;
    }
  
    /**
     * Get list of movies from an url. For example: search results, most popular movies etc.
     * @param url Url to list of movies
     * @return List of movies
     * @throws IOException
     * @throws JSONException 
     */
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
  
  /**
   * Get a singular movie (chosen by id) from an url.
   * @param url Url with movie-id 
   * @return The movie
   * @throws IOException
   * @throws JSONException 
   */
  public static Movie getMovieFromUrl(String url) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl(url);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode field = objectMapper.readTree(json.toString());

        return getMovieFromNode(field);
  }
  
  /**
   * Get genre tied to id
   * @param url Url with genres 
     * @param id 
   * @return The genre
   * @throws IOException
   * @throws JSONException 
   */
  public static String getGenreFromUrl(String url, String id) throws IOException, JSONException{
      JSONObject json = readJsonFromUrl(url);

      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode tree = objectMapper.readTree(json.toString());
      JsonNode paths = tree.get("genres");
      
      Iterator<JsonNode> fields = paths.elements();
      while(fields.hasNext()){
           
            JsonNode field = fields.next();
            if(field.get("id").asText().equals(id))
                return field.get("name").asText();
                
      }
      return "";
  }
  
  private static Movie getMovieFromNode(JsonNode field){
      
        String title;
        String avg_rating;
        String overview;
        String release_date;
        String poster_path;
        String id;
        String director;
        List<String> starring; 
        List<String> genres = new ArrayList<>();

        if(field.has("title")){
            title = field.get("title").asText();
        } else{
            title = "Could not find title";
        }
        if(field.has("vote_average")){
             avg_rating = field.get("vote_average").asText();
        } else{
             avg_rating = "Could not find vote_average";
        }
        if(field.has("overview")){
             overview = field.get("overview").asText();
        } else{
             overview = "Could not find overview";
        }
        if(field.has("release_date")){
             release_date = field.get("release_date").asText();
        } else{
             release_date = "Could not find release_date";
        }
        if(field.has("poster_path")){
             poster_path = field.get("poster_path").asText();
        } else{
             poster_path = "Could not find poster_path";
        }
        if(field.has("id")){
             id = field.get("id").asText();
        } else{
             id = "Could not find id";
        }      
        if(field.has("genres")){
            genres = getGenresFromGenreNode(field.get("genres"));
        } else if(field.has("genre_ids")){
            genres = getGenresFromGenreNode(field.get("genre_ids"));
        }
        
        Movie movie = new Movie(
                title
                ,avg_rating
                ,overview
                ,release_date
                ,poster_path
                ,id
                ,genres
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

        if(field.has("profile_path")){
            pic_path = "https://image.tmdb.org/t/p/w500" + field.findValue("profile_path").asText();
        } else {
            pic_path = "Could not find profile picture";
        }
        if(field.has("name")){
            name = field.get("name").asText();
        } else{
            name = "Could not find name";
        }
        if(field.has("birthday")){
            birthday = field.get("birthday").asText();
        } else {
            birthday = "Could not find birthday";
        }
        if(field.has("deathday")){
            deathday = field.get("deathday").asText();
        } else {
            deathday = "Could not find deathday";
        }
        if(field.has("biography")){
            bio = field.get("biography").asText();
        } else {
            bio = "Could not find biography";
        }
        if(field.has("id")){
            id = field.get("id").asText();
        } else{
            id = "Could not find id";
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
  
  private static List<String> getGenresFromGenreNode(JsonNode genreField){
      List<String> ids = new ArrayList<>();
      Iterator<JsonNode> fields = genreField.elements();
      while(fields.hasNext()){
           
            JsonNode field = fields.next();
            if(field.has("id")){
                ids.add(field.get("id").asText());
            } else{
                ids.add(field.asText());
            }
      }
      return ids;
  }
  
 

}

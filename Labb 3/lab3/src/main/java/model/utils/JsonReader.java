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
  
  public static List<Movie> getMoviesFromUrl(String url) throws IOException, JSONException {
      JSONObject json = readJsonFromUrl(url);
      List<Movie> movies = new ArrayList<>();
      
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode tree = objectMapper.readTree(json.toString());
      JsonNode paths = tree.get("results");
      
      Iterator<JsonNode> fields = paths.elements();
      while(fields.hasNext()){
           
            JsonNode field = fields.next();
            String title;
            String avg_rating;
            String overview;
            String release_date;
            String poster_path;
                    
                    
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
                                    
            
            
            /*String avg_rating = field.findValue("vote_average").asText();
            String overview = field.findValue("overview").asText();
            String release_date = field.findValue("release_date").asText();
            String poster_path = field.findValue("poster_path").asText();*/
            
            Movie movie = new Movie(
                    title
                    ,avg_rating
                    ,overview
                    ,release_date
                    ,poster_path
            );
            
            movies.add(movie);      
      }
      return movies;
      
  }
 

}

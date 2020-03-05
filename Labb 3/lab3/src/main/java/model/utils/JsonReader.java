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
import javax.json.bind.*;
import model.entity.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
      Jsonb jsonb = JsonbBuilder.create();
      JSONObject json = readJsonFromUrl(url);
      List<Movie> movies = new ArrayList<>();
      
      ObjectMapper objectMapper = new ObjectMapper();
      List<JsonNode> result = new ArrayList<>();
      JsonNode tree = objectMapper.readTree(url);
      JsonNode paths = tree.get("results");
      
      Iterator<String> fieldNames = paths.fieldNames();
      while(fieldNames.hasNext()){
           String fieldName = fieldNames.next();
            JsonNode path = paths.get(fieldName);

            // Create a copy of the tree
            JsonNode copyOfTree = objectMapper.valueToTree(tree);

            // Remove all the children from the "paths" node; add a single child to "paths"
            ((ObjectNode) copyOfTree.get("paths")).removeAll().set(fieldName, path);

            // Add the modified tree to the result list
            result.add(copyOfTree);
      }
      
      for(JsonNode node : result){
          
          Movie movie = jsonb.fromJson(node.toString(), Movie.class);
          movies.add(movie);
      }
      return movies;
      
  }
 

}

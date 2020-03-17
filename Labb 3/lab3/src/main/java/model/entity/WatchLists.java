/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.List;
import lombok.NonNull;

/**
 *
 * @author Spondon
 */
public class WatchLists {
    
    @NonNull String name;
    @NonNull List<List<Movie>> watchLists;
    
}

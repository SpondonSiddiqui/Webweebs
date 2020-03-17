/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Spondon
 */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class WatchList implements Serializable{
    
    @Id
    @NonNull List<Movie> watchList;
    
}

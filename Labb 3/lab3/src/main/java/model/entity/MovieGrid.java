/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;


import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jespe
 */
@Data
@Named
@NoArgsConstructor
@RequiredArgsConstructor
public class MovieGrid implements Serializable{
    
    @Getter
    @Setter
    private List<Movie> movies;
    
    @Getter
    @Setter
    private Movie selectedMovie;
    
    
}

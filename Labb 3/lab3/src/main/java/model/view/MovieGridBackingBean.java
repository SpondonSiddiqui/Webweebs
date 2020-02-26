/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import lombok.Data;

/**
 *
 * @author Spondon
 */
@Data
@Named
@ViewScoped
public class MovieGridBackingBean implements Serializable {
    
    @EJB
    private MovieGrid movieGrid;
    
    private List<Movie> movies;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.MovieDAO;
import model.entity.Movie;
import org.omnifaces.cdi.Param;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author User
 */
@Data
@Named
@ViewScoped
public class ShowMovieBackingBean implements Serializable {

    @Inject
    @Param(name = "name")
    private String name;
    @EJB
    private MovieDAO movieDAO;

    public List<Movie> getMovie() {
        return movieDAO.findMoviesByName(name);
    }
}

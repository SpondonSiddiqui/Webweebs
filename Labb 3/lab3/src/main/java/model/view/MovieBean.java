package model.view;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;
import model.entity.Movie;

@Named
@SessionScoped
@Data
public class MovieBean implements Serializable {
    private Movie movie;
    
    public boolean hasMovie() {
        return movie != null;
    }
}

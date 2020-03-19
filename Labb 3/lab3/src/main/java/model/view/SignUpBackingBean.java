package model.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.UserDAO;
import model.entity.WebUser;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import model.dao.WatchListDAO;
import model.entity.WatchList;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 *
 * @author makka
 */
@Data
@Named
@ViewScoped
public class SignUpBackingBean implements Serializable {

    //only accept letters, numbers and underscore in the username, aswell as 5-25 characters
    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Username contains weird characters!")
    @Size(min = 5, max = 25, message = "Username too short or too long!")
    private String username;
    private String password;

    private List<WebUser> users;

    @EJB
    private UserDAO userDAO;
    
    @EJB
    private WatchListDAO watchListDAO;

    @PostConstruct
    private void init() { 
        users = userDAO.findAll(); 
    }

    /** validateNewUser()
     * checks if user already exists/bad username
     * @return string depending on successful signup
     */
    public String validateNewUser() { 

        WebUser wu = new WebUser(username, password); //create new user
        if (!userDAO.contains(wu.getUsername())) {
            userDAO.create(wu);
            watchListDAO.create(new WatchList(username + "'s watchlist", null, wu));
            Messages.addGlobalWarn("Created user " + username + "!", null);
            Faces.validationFailed(); 
            return "signupsuccesful"; //return to index
        } else {
            Messages.addGlobalWarn("Username " + username + " already exists!", null);
            Faces.validationFailed();
            return "false";
        }
    }
}

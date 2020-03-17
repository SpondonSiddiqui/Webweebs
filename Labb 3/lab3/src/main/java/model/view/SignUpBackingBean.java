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

    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Username contains weird characters!")
    @Size(min = 5, max = 25, message = "Username too short or too long!")
    private String username;
    private String password;

    private List<WebUser> users;

    @EJB
    private UserDAO userDAO;

    @PostConstruct
    private void init() {
        users = userDAO.findAll();
    }

    /** validateNewUser()
     * checks if user already exists/bad username
     * @return string depending on successful signup
     */
    public String validateNewUser() {

        WebUser wu = new WebUser(username, password, null);
        if (!userDAO.contains(wu.getUsername())) {
            userDAO.create(wu);
            Messages.addGlobalWarn("Created user " + username + "!", null);
            Faces.validationFailed();
            return "signupsuccesful";
        } else {
            Messages.addGlobalWarn("Username " + username + " already exists!", null);
            Faces.validationFailed();
            return "false";
        }
    }
}

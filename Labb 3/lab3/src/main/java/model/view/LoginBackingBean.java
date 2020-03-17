/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.UserDAO;
import model.entity.WebUser;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 *
 * @author Spondon
 */
@Data
@Named
@ViewScoped
public class LoginBackingBean implements Serializable {

    //@Pattern(regexp = "?^abc.*+ef")
    private String username;
    private String password;
 
    @EJB
    private UserDAO userDAO;
    
    @Inject
    private UserBean userBean;

    public String onLogin() {

        WebUser user = userDAO.find(username);
        
        if(user != null && user.getPassword().equals(password)) {
            userBean.setUser(user);
            return "loginsuccesful";

            /*if (password.equals(wb.getPassword())) {
                return "loginsuccesful";
            } else {
                Messages.addGlobalWarn("Wrong password");
                Faces.validationFailed();
                return "wrongpassword";
            }*/
        } else {
            Messages.addGlobalWarn("Wrong username or password");
            Faces.validationFailed();
            return "false";
        }
    }
    
    public void onLogout() {
        userBean.setUser(null);
        Faces.invalidateSession();
        Faces.redirect("index.xhtml");
    }
}

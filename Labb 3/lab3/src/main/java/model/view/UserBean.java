package model.view;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;
import model.entity.WebUser;

@Named
@ManagedBean
@SessionScoped
@Data
public class UserBean implements Serializable {
    private WebUser user;
    
    public boolean isLoggedIn() {
        return user != null;
    }
    
}

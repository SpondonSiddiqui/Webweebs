/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.view;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.Data;

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

    public String validateLoginDetails() {

        if (this.username.equals("webweebs") && this.password.equals("webweebs")) {
            return "loginsuccesful";
        } else {
            return "false";   
        }
        

        /*if (this.username == null || this.username.equals("webweebs")) {
            isUsernameValid = false;
        } else {
            isUsernameValid = true;
        }
        if (this.password == null || this.password.equals("webweebs")) {
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
        }
        validationComplete = true;*/
        //return "loginsuccessful";
    }
}

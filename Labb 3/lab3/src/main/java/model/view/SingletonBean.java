/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.view;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;


/**
 *
 * @author Lamme
 */
@Singleton
@Startup
public class SingletonBean {
    
    @PostConstruct
    public void init(){
        
    }
}

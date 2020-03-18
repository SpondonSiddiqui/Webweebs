/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UserWatchList implements Serializable {
    @Id
    @NonNull
    @JoinColumn(name = "username")
    //Ska det fortfarande vara Many to One om vi kör med att en användare endast har en watchlist?
    @ManyToOne private WebUser webUser;
    
    //Samma sak här, om vi kör med en watchlist per användare, ska det verkligen vara Many to Many?
    @ManyToMany(mappedBy = "watchlists") private List<Movie> movies;
}

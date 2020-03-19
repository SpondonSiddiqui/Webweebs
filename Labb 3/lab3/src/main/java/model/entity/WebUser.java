package model.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class WebUser implements Serializable {

    @Id
    @NonNull private String username;
    @NonNull private String password;
    
    //Ska det vara One to Many om vi har en watchlist per användare?
    @OneToMany(mappedBy = "webUser") 
    private List<UserWatchList> watchlist;
}

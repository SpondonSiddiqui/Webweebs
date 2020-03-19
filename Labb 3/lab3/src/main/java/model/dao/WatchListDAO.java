package model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.Getter;
import model.entity.WatchList;
import model.entity.WebUser;

@Stateless
public class WatchListDAO extends AbstractDAO<WatchList, String> {
    
    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public WatchListDAO() {
        super(WatchList.class);
    }

    public List<WatchList> getWatchListBelongingToUser(WebUser user) {
        Query query = entityManager.createQuery("SELECT w FROM WatchList w "
                + "WHERE w.user = :user");
        query.setParameter("user", user);
        
        return query.getResultList();
    }
}

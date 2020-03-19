package model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}

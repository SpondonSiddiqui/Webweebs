package model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.Getter;
import model.entity.WebUser;

@Stateless
public class UserDAO extends AbstractDAO<WebUser, String> {

    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public UserDAO() {
        super(WebUser.class);
    }
    
    public List<WebUser> getUserByName(String username) {
        Query query = entityManager.createQuery("SELECT w FROM WebUser w "
                + "WHERE w.username = :username");
        query.setParameter("username", username);
        return query.getResultList();
    }
}

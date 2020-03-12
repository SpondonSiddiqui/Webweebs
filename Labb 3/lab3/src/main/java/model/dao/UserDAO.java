package model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}

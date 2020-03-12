package model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.Getter;
import model.entity.Actor;

@Stateless
public class ActorDAO extends AbstractDAO<Actor,String> {

    @Getter
    @PersistenceContext(unitName = "academy")
    private EntityManager entityManager;

    public ActorDAO() {
        super(Actor.class);
    }
    
    public List<Actor> findActorsByName(String name) {
        Query query = entityManager.createQuery("SELECT m FROM Actor m "
                + "WHERE m.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }
}

package model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}

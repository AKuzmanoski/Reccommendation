package mk.ukim.finki.iis.persistance;

import mk.ukim.finki.iis.model.BaseEntity;
import mk.ukim.finki.iis.persistance.helper.PredicateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by User on 11/30/2015.
 */
@Repository
public class BaseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public <T extends BaseEntity> T getById(Class<T> type, Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        final Root<T> root = criteriaQuery.from(type);

        Predicate byId = criteriaBuilder.equal(root.get("entityId"), id);
        criteriaQuery.where(byId);

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    public <T> List<T> find(Class<T> type, PredicateBuilder<T> predicateBuilder) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        final Root<T> root = criteriaQuery.from(type);

        // add here security predicate (add it to the WHERE clause).

        if (predicateBuilder != null)
            criteriaQuery.where(predicateBuilder.toPredicate(criteriaBuilder, criteriaQuery, root));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public <T> List<T> find(Class<T> type, PredicateBuilder<T> predicateBuilder, Integer maxResults) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        final Root<T> root = criteriaQuery.from(type);

        // add here security predicate (add it to the WHERE clause).

        if (predicateBuilder != null)
            criteriaQuery.where(predicateBuilder.toPredicate(criteriaBuilder, criteriaQuery, root));

        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        query.setMaxResults(maxResults);

        return query.getResultList();
    }

    public <T> Long count(Class<T> type, PredicateBuilder<T> predicateBuilder) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(criteriaBuilder.count(root));
        if (predicateBuilder != null)
            criteriaQuery.where(predicateBuilder.toPredicate(criteriaBuilder, null, root));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    private <T> Predicate getSecurityPredicate(Class<T> type) {
        return null;
    }

    @Transactional
    public <T extends BaseEntity> T saveOrUpdate(T entity) {
        if (entity.getEntityId() != null && !entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }

        entityManager.flush();
        return entity;
    }

    @Transactional
    public void executeQuery(String queryString) {
        Query query = entityManager.createNativeQuery(queryString);
        query.getSingleResult();
    }

    @Transactional
    public <T extends BaseEntity> void insert(List<T> entities) {

    }

    @Transactional
    public <T> int delete(Class<T> type, Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(type);
        final Root<T> root = criteriaDelete.from(type);

        Predicate byId = criteriaBuilder.equal(root.get("entityId"), id);
        criteriaDelete.where(byId);

        Query query = entityManager.createQuery(criteriaDelete);
        int changes = query.executeUpdate();
        entityManager.flush();

        return changes;
    }
}

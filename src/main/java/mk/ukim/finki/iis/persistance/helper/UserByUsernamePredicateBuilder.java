package mk.ukim.finki.iis.persistance.helper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by User on 12/2/2015.
 */
public class UserByUsernamePredicateBuilder<T> implements PredicateBuilder<T> {
    private String name;

    public UserByUsernamePredicateBuilder(String name) {
        this.name = name;
    }

    public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<T> cq, Root<T> root) {
        return cb.equal(root.get("name"), name);
    }
}

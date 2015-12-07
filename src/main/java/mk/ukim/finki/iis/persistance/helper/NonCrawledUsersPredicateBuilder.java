package mk.ukim.finki.iis.persistance.helper;

import mk.ukim.finki.iis.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by User on 12/7/2015.
 */
public class NonCrawledUsersPredicateBuilder implements PredicateBuilder<User> {
    public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<User> cq, Root<User> root) {
        return cb.equal(root.get("friendListCrawled"), false);
    }
}

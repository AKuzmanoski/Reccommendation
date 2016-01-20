package mk.ukim.finki.iis.persistance.jpa.specifications;

import mk.ukim.finki.iis.model.User;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by User on 1/20/2016.
 */
public class UserSpecifications {

    public static Specification<User> userWithUsername(String username) {
        return (root, query, cb) -> cb.equal(root.get("name"), username);
    }

    public static Specification<User> userWithFriendListCrawled() {
        return (root, query, cb) -> cb.equal(root.get("friendListCrawled"), false);
    }
}

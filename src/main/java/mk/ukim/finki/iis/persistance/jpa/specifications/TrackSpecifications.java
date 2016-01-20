package mk.ukim.finki.iis.persistance.jpa.specifications;

import mk.ukim.finki.iis.model.Track;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by User on 1/20/2016.
 */
public class TrackSpecifications {
    public static Specification<Track> trackWithUrl(String url) {
        return (root, query, cb) -> cb.equal(root.get("url"), url);
    }
}

package mk.ukim.finki.iis.persistance.helper;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.CountryHasTack;
import mk.ukim.finki.iis.model.Track;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by User on 12/6/2015.
 */
public class CountryHasTrackPredicateBuilder implements PredicateBuilder<CountryHasTack> {
    private Country country;
    private Track track;

    public CountryHasTrackPredicateBuilder(Country country, Track track) {
        this.country = country;
        this.track = track;
    }

    public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<CountryHasTack> cq, Root<CountryHasTack> root) {
        Predicate countryId = cb.equal(root.get("country"), country);
        Predicate trackId = cb.equal(root.get("track"), track);
        Predicate where = cb.and(countryId, trackId);
        return where;
    }
}

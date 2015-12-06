package mk.ukim.finki.iis.persistance;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.CountryHasTack;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.model.User;

/**
 * Created by User on 12/1/2015.
 */
public interface CountryRepository {
    Country getCountryById(Long id);

    Country insertCountry(Country country);

    CountryHasTack userListens(Country country, Track track, Long playCount);

    Country getCountryByName(String name);
}

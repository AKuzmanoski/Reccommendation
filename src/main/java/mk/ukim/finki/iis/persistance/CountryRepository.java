package mk.ukim.finki.iis.persistance;

import mk.ukim.finki.iis.model.Country;

/**
 * Created by User on 12/1/2015.
 */
public interface CountryRepository {
    Country getCountryById(Long id);

    Country insertCountry(Country country);
}

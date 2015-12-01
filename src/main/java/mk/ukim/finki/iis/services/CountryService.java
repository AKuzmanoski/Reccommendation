package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Country;

/**
 * Created by User on 12/1/2015.
 */
public interface CountryService {
    Country getCountryById(Long id);

    Country insertCountry(Country country);
}

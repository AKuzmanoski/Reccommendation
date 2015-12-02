package mk.ukim.finki.iis.services;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.Track;

/**
 * Created by User on 12/1/2015.
 */
public interface CountryService {
    Country getCountryById(Long id);

    Country insertCountry(Country country);

    void userListened(Country country, Track track, Long playCount);
}

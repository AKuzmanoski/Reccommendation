package mk.ukim.finki.iis.services.impl;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.persistance.CountryRepository;
import mk.ukim.finki.iis.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User on 12/1/2015.
 */
@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public Country getCountryById(Long id) {
        return countryRepository.getCountryById(id);
    }

    public Country insertCountry(Country country) {
        return countryRepository.insertCountry(country);
    }

    public void userListened(Country country, Track track, Long playCount) {
        countryRepository.userListens(country, track, playCount);
    }
}

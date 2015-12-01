package mk.ukim.finki.iis.persistance.impl;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.persistance.BaseRepository;
import mk.ukim.finki.iis.persistance.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by User on 12/1/2015.
 */
@Repository
public class CountryRepositoryImpl implements CountryRepository {
    @Autowired
    BaseRepository baseRepository;

    public Country getCountryById(Long id) {
        return baseRepository.getById(Country.class, id);
    }

    public Country insertCountry(Country country) {
        return baseRepository.saveOrUpdate(country);
    }
}

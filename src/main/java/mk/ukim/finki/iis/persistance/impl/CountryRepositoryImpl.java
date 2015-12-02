package mk.ukim.finki.iis.persistance.impl;

import mk.ukim.finki.iis.model.Country;
import mk.ukim.finki.iis.model.CountryHasTack;
import mk.ukim.finki.iis.model.Track;
import mk.ukim.finki.iis.persistance.BaseRepository;
import mk.ukim.finki.iis.persistance.CountryRepository;
import mk.ukim.finki.iis.persistance.helper.CountryHasTrackPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public CountryHasTack userListens(Country country, Track track, Long playCount) {
        CountryHasTack countryHasTack = getCountryHasTrack(country, track);
        if (countryHasTack == null) {
            countryHasTack = new CountryHasTack(country, 0L, track);
        }
        countryHasTack.addPlayCount(playCount);
        return baseRepository.saveOrUpdate(countryHasTack);
    }

    private CountryHasTack getCountryHasTrack(Country country, Track track) {
        List<CountryHasTack> list =
                baseRepository.find(CountryHasTack.class,
                        new CountryHasTrackPredicateBuilder<CountryHasTack>(country, track));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
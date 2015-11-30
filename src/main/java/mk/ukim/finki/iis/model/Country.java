package mk.ukim.finki.iis.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {
    @Column(name = "country_code")
    private String countryCode;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<CountryHasTack> listenedSongs;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<User> users;

    public Country() {

    }

    public Country(String countryCode, String name) {
        super();
        this.countryCode = countryCode;
        this.name = name;
    }

    public List<CountryHasTack> getListenedSongs() {
        return listenedSongs;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

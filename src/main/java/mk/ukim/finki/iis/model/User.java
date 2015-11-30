package mk.ukim.finki.iis.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String name;
    private String url;
    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserListensTrack> listenedSongs;

    public User() {

    }

    public User(String name, String url, String country) {
        super();
        this.name = name;
        this.url = url;
        this.country = new Country();
        setCountry(country);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country.getName();
    }

    public void setCountry(String country) {
        this.country = new Country(country, country);
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", url=" + url + ", country=" + country
                + "]";
    }


}

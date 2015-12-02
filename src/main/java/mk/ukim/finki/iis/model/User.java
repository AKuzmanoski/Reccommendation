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
    @Column(name = "lastfm_id")
    private Long lastFMId;

    public User() {

    }

    public User(String name, String url, Country country, Long lastFMId) {
        super();
        this.name = name;
        this.url = url;
        this.country = new Country();
        this.country = country;
        this.lastFMId = lastFMId;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", url=" + url + ", country=" + country
                + "]";
    }

    public Long getLastFMId() {
        return lastFMId;
    }

    public void setLastFMId(Long lastFMId) {
        this.lastFMId = lastFMId;
    }
}

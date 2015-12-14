package mk.ukim.finki.iis.model;

import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(unique = true)
    private String name;
    private String url;
    private String gender;
    private String country;
    @Column(name = "lastfm_id", unique = true)
    private Long lastFMId;
    private Boolean friendListCrawled;

    public User() {
        friendListCrawled = false;
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String url, String country, Long lastFMId) {
        super();
        this.name = name;
        this.url = url;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFriendListCrawled(Boolean friendListCrawled) {
        this.friendListCrawled = friendListCrawled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        return getName().equals(user.getName());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}

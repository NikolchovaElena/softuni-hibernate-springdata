package userapp.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    private Integer id;
    private String name;
    private Country country;
    private Set<User> born;
    private Set<User> currentlyLivingIn;

    public Town() {
        this.born = new HashSet<>();
        this.currentlyLivingIn = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(targetEntity = Country.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "bornTown")
    public Set<User> getBorn() {
        return born;
    }

    public void setBorn(Set<User> born) {
        this.born = born;
    }

    @OneToMany(mappedBy = "currentlyLivingInTown")
    public Set<User> getCurrentlyLivingIn() {
        return currentlyLivingIn;
    }

    public void setCurrentlyLivingIn(Set<User> currentlyLivingIn) {
        this.currentlyLivingIn = currentlyLivingIn;
    }
}

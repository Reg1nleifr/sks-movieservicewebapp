package entity;

import xml.helpers.DateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.util.List;

/**
 * Created by Flo on 06/10/16.
 */
@XmlRootElement
@Entity
@Table(name = "Actor")
@NamedQueries({
        @NamedQuery(name="Actor.getAll", query = "select a from Actor a"),
        @NamedQuery(name="Actor.getByName",
                query = "select a from Actor a " +
                            "where a.firstname like concat('%', :name, '%') " +
                                "or a.lastname like concat('%', :name, '%')"),
        @NamedQuery(name="Actor.getActorCount",
                query = "select count(a) from Actor a " +
                            "where a.lastname like :lastname " +
                                "and a.firstname like :firstname " +
                                "and a.birthdate = :birthdate " +
                                "and a.sex like :sex")
})
public class Actor {
    private int id;
    private String firstname;
    private String lastname;
    private String sex;
    private Date birthdate;
    private List<Movie> movies;

    @XmlTransient
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute
    @Column(name = "FIRSTNAME", nullable = true, length = 50)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @XmlAttribute
    @Column(name = "LASTNAME", nullable = true, length = 50)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @XmlAttribute
    @Column(name = "SEX", nullable = true, length = 1)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @XmlJavaTypeAdapter(value = DateAdapter.class, type = Date.class)
    @XmlAttribute
    @Column(name = "BIRTHDATE", nullable = true)
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @XmlTransient
    @ManyToMany
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

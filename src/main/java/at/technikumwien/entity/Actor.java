package at.technikumwien.entity;

import at.technikumwien.services.helpers.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Flo & Stefan
 * Actor Class
 * Named Queries in JPQL
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Table(name = "Actor")
@NamedQueries({
        @NamedQuery(name="Actor.getAll", query = "select a from Actor a"),
        @NamedQuery(name="Actor.getByName",
                query = "select a from Actor a " +
                            "where a.firstname like concat('%', :name, '%') " +
                                "or a.lastname like concat('%', :name, '%')"),
        @NamedQuery(name = "Actor.getById",
                query = "select a from Actor a " +
                        "where a.id = :id"),
        @NamedQuery(name = "Actor.deleteById",
                query = "delete from Actor a " +
                        "where a.id = :id"),
        @NamedQuery(name="Actor.getActor",
                query = "select a from Actor a " +
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
    private LocalDate birthdate;
    private List<Movie> movies;

    @XmlAttribute
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

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class, type = LocalDate.class)
    @XmlAttribute
    @Column(name = "BIRTHDATE", nullable = true)
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
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

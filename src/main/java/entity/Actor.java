package entity;

import javax.inject.Named;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Flo on 06/10/16.
 */
@XmlRootElement
@Entity
@Table(name = "Actor")
@NamedQueries({
        @NamedQuery(name="Actor.getAll", query = "select a from Actor a"),
        @NamedQuery(name="Actor.getFemales", query = "select a from Actor a where a.sex='F'"),
        @NamedQuery(name="Actor.getMales", query = "select a from Actor a where a.sex='M'"),
        @NamedQuery(name="Actor.getByName",
                query = "select a from Actor a where a.firstname like :name OR a.lastname like :name")
})
public class Actor {
    private int id;
    private String firstname;
    private String lastname;
    private String sex;
    private Date birthdate;
    private Collection<Movie> movies;

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
    @Basic
    @Column(name = "FIRSTNAME", nullable = true, length = 50)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @XmlAttribute
    @Basic
    @Column(name = "LASTNAME", nullable = true, length = 50)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @XmlAttribute
    @Basic
    @Column(name = "SEX", nullable = true, length = 1)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @XmlTransient //TODO: Muss ein eigenen Compiler machen!
    @Basic
    @Column(name = "BIRTHDATE", nullable = true)
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @XmlTransient
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    public Collection<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (id != actor.id) return false;
        if (firstname != null ? !firstname.equals(actor.firstname) : actor.firstname != null) return false;
        if (lastname != null ? !lastname.equals(actor.lastname) : actor.lastname != null) return false;
        if (sex != null ? !sex.equals(actor.sex) : actor.sex != null) return false;
        if (birthdate != null ? !birthdate.equals(actor.birthdate) : actor.birthdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        return result;
    }
}

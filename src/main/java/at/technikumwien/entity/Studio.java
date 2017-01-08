package at.technikumwien.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Created by Flo & Stefan
 * Studio Class
 * Named Queries in JPQL
 */
@XmlRootElement
@Entity
@Table(name = "Studio")
@NamedQueries({
        @NamedQuery(name="Studio.getAll", query = "select s from Studio s"),
        @NamedQuery(name="Studio.getByName",
                query = "select s from Studio s " +
                            "where s.name like concat('%',:name,'%')"),
        @NamedQuery(name = "Studio.getById",
                query = "select s from Studio s " +
                        "where s.id = :id"),
        @NamedQuery(name = "Studio.deleteById",
                query = "delete from Studio s " +
                        "where s.id = :id"),
        @NamedQuery(name="Studio.getStudio",
                query = "select s from Studio s " +
                            "where s.name = :name " +
                                "and s.countrycode = :countrycode " +
                                "and s.postcode = :postcode")
})
public class Studio {
    private int id;
    private String name;
    private String countrycode;
    private String postcode;
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
    @Column(name = "NAME", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    @Column(name = "COUNTRYCODE", nullable = true, length = 3)
    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    @XmlAttribute
    @Column(name = "POSTCODE", nullable = true)
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @XmlTransient
    @OneToMany (mappedBy = "studio")
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

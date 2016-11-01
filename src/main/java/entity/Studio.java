package entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Flo on 06/10/16.
 */
@XmlRootElement
@Entity
@Table(name = "Studio")
@NamedQueries({
        @NamedQuery(name="Studio.getAll", query = "select s from Studio s"),
        @NamedQuery(name="Studio.getByName",
                query = "select s from Studio s " +
                            "where s.name like concat('%',:name,'%')"),
        @NamedQuery(name="Studio.getStudioCount",
                query = "select count(s) from Studio s " +
                            "where s.name = :name " +
                                "and s.countrycode = :countrycode " +
                                "and s.postcode = :postcode")
})
public class Studio {
    private int id;
    private String name;
    private String countrycode;
    private Integer postcode;
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
    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
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

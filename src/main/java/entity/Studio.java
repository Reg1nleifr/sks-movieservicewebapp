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
        @NamedQuery(name="Studio.getAll", query = "select a from Studio a"),
        @NamedQuery(name="Studio.getByName",
                query = "select a from Studio a where a.name like concat('%',:name,'%')")
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
    @Basic
    @Column(name = "NAME", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    @Basic
    @Column(name = "COUNTRYCODE", nullable = true, length = 3)
    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    @XmlAttribute
    @Basic
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Studio studio = (Studio) o;

        if (id != studio.id) return false;
        if (name != null ? !name.equals(studio.name) : studio.name != null) return false;
        if (countrycode != null ? !countrycode.equals(studio.countrycode) : studio.countrycode != null) return false;
        if (postcode != null ? !postcode.equals(studio.postcode) : studio.postcode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (countrycode != null ? countrycode.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        return result;
    }
}

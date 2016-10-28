package entity;

import xmlAdapter.SqlDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Flo on 06/10/16.
 */
@XmlRootElement
@Entity
@Table(name = "Movie")
@NamedQueries({
        @NamedQuery(name="Movie.getAll", query = "select a from Movie a"),
        @NamedQuery(name="Movie.getByTitle",
                query = "select a from Movie a where a.title like lower(:name)")
})
public class Movie {

    private int id;
    private String title;
    private String description;
    private String genre;
    private Integer length;
    private Date realease;
    private Studio studio;
    private Collection<Actor> actors;

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
    @Column(name = "TITLE", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute
    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlAttribute
    @Basic
    @Column(name = "GENRE", nullable = true, length = 50)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlAttribute
    @Basic
    @Column(name = "LENGTH", nullable = true)
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @XmlJavaTypeAdapter(SqlDateAdapter.class) // Eventuell Fragen wegen Datenbank
    @Basic
    @Column(name = "REALEASE", nullable = true)
    public Date getRealease() {
        return realease;
    }

    public void setRealease(Date realease) {
        this.realease = realease;
    }
    @XmlElement(name = "movie")
    @OneToOne(mappedBy = "movie")
    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    @XmlElementWrapper(name = "actors")
    @XmlElement(name = "actor")
    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "movies", fetch = FetchType.EAGER)
    public Collection<Actor> getActors() {
        return actors;
    }

    public void setActors(Collection<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (description != null ? !description.equals(movie.description) : movie.description != null) return false;
        if (genre != null ? !genre.equals(movie.genre) : movie.genre != null) return false;
        if (length != null ? !length.equals(movie.length) : movie.length != null) return false;
        if (realease != null ? !realease.equals(movie.realease) : movie.realease != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (realease != null ? realease.hashCode() : 0);
        return result;
    }
}

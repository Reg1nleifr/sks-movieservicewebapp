package entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Flo on 06/10/16.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Movie")
public class Movie {
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String title;
    @XmlAttribute
    private String description;
    @XmlAttribute
    private String genre;
    @XmlAttribute
    private Integer length;
    @XmlAttribute
    private Date realease;

    private Collection<Studio> studios;
    private Collection<Actor> actors;


    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TITLE", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "GENRE", nullable = true, length = 50)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "LENGTH", nullable = true)
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Basic
    @Column(name = "REALEASE", nullable = true)
    public Date getRealease() {
        return realease;
    }

    public void setRealease(Date realease) {
        this.realease = realease;
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


    @OneToMany(mappedBy = "movie")
    public Collection<Studio> getStudios() {
        return studios;
    }

    public void setStudios(Collection<Studio> studios) {
        this.studios = studios;
    }

    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "movies", fetch = FetchType.LAZY)
    public Collection<Actor> getActors() {
        return actors;
    }

    public void setActors(Collection<Actor> actors) {
        this.actors = actors;
    }
}

package at.technikumwien.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Flo on 06/10/16.
 */
@XmlRootElement
@XmlType(propOrder = {"title", "genre", "length", "releaseYear","actors" ,"studio" , "description"})
@Entity
@Table(name = "Movie")
@NamedQueries({
        @NamedQuery(name="Movie.getAll", query = "select m from Movie m"),
        @NamedQuery(name="Movie.getByTitle",
                query = "select m from Movie m " +
                            "where m.title like concat('%',lower(:name),'%') "),
        @NamedQuery(name="Movie.getMovieCount",
                query = "select m from Movie m " +
                        "where m.title = :title " +
                            "and m.description = :description " +
                            "and m.genre = :genre " +
                            "and m.releaseYear = :releaseyear " +
                            "and m.length = :length")
})
public class Movie {

    private int id;
    private String title;
    private String description;
    private String genre;
    private Integer length;
    private int releaseYear;
    private Studio studio;
    private List<Actor> actors;

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
    @Column(name = "TITLE", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute
    @Column(name = "DESCRIPTION", nullable = true, length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlAttribute
    @Column(name = "GENRE", nullable = true, length = 50)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlAttribute
    @Column(name = "LENGTH", nullable = true)
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @XmlAttribute
    @Column(name = "RELEASEYEAR", nullable = true)
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int release) {
        this.releaseYear = release;
    }

    @XmlElement(name = "studio")
    @ManyToOne
    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    @XmlElementWrapper(name = "actors")
    @XmlElement(name = "actor")
    @ManyToMany(mappedBy = "movies", fetch = FetchType.EAGER)
    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
